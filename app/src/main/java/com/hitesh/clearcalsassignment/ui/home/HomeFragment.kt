package com.hitesh.clearcalsassignment.ui.home

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.hitesh.clearcalsassignment.R
import com.hitesh.clearcalsassignment.paging.LoaderAdapter
import com.hitesh.clearcalsassignment.paging.RecipePagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object;

    private lateinit var viewModel: HomeViewModel

    private lateinit var adapter: RecipePagingAdapter

    private lateinit var recyclerView: RecyclerView

    private lateinit var mainProgressBar: LottieAnimationView

    private lateinit var etSearch: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        viewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        recyclerView = v.findViewById(R.id.rv_recipe)
        mainProgressBar = v.findViewById(R.id.main_progress_bar)
        etSearch = v.findViewById(R.id.et_search)
        adapter = RecipePagingAdapter()

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.hasFixedSize()
        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

        etSearch.setOnEditorActionListener { _, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE ||
                keyEvent == null ||
                keyEvent.keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                viewModel.getRecipes(etSearch.text.toString()).observe(viewLifecycleOwner) {
                    adapter.submitData(lifecycle, it)
                }
            }
            false
        }

        adapter.addLoadStateListener {
            if (it.prepend is LoadState.NotLoading && it.prepend.endOfPaginationReached) {
                mainProgressBar.visibility = View.GONE
            }
            if (it.append is LoadState.NotLoading && it.append.endOfPaginationReached) {
                mainProgressBar.isVisible = adapter.itemCount < 1
            }
        }

        viewModel.getRecipes("").observe(viewLifecycleOwner) {
            Log.d("Observer", it.toString())
            adapter.submitData(lifecycle, it)
        }

        return v
    }

}