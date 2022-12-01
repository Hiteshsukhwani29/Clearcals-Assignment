package com.hitesh.clearcalsassignment.ui.home

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.hitesh.clearcalsassignment.databinding.FragmentHomeBinding
import com.hitesh.clearcalsassignment.paging.LoaderAdapter
import com.hitesh.clearcalsassignment.paging.RecipePagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel

    private lateinit var adapter: RecipePagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        adapter = RecipePagingAdapter()

        binding.rvRecipe.layoutManager = LinearLayoutManager(activity)
        binding.rvRecipe.hasFixedSize()
        binding.rvRecipe.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

        binding.etSearch.setOnEditorActionListener { _, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE ||
                keyEvent == null ||
                keyEvent.keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                viewModel.getRecipes(binding.etSearch.text.toString()).observe(viewLifecycleOwner) {
                    adapter.submitData(lifecycle, it)
                }
            }
            false
        }

        adapter.addLoadStateListener {
            if (it.prepend is LoadState.NotLoading && it.prepend.endOfPaginationReached) {
                binding.mainProgressBar.visibility = View.GONE
            }
            if (it.append is LoadState.NotLoading && it.append.endOfPaginationReached) {
                binding.mainProgressBar.isVisible = adapter.itemCount < 1
            }
        }

        viewModel.getRecipes("").observe(viewLifecycleOwner) {
            Log.d("Observer", it.toString())
            adapter.submitData(lifecycle, it)
        }

        return root
    }

}