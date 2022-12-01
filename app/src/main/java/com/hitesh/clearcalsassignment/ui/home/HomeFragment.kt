package com.hitesh.clearcalsassignment.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.filter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.hitesh.clearcalsassignment.R
import com.hitesh.clearcalsassignment.paging.LoaderAdapter
import com.hitesh.clearcalsassignment.paging.RecipePagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    private lateinit var adapter: RecipePagingAdapter

    private lateinit var recyclerView: RecyclerView

    private lateinit var mainProgressBar: LottieAnimationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        recyclerView = v.findViewById(R.id.rv_recipe)
        mainProgressBar = v.findViewById(R.id.main_progress_bar)
        adapter = RecipePagingAdapter()

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.hasFixedSize()
        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

        adapter.addLoadStateListener {
            if (it.prepend is LoadState.NotLoading && it.prepend.endOfPaginationReached) {
                mainProgressBar.visibility = View.GONE
            }
            if (it.append is LoadState.NotLoading && it.append.endOfPaginationReached) {
                mainProgressBar.isVisible = adapter.itemCount < 1
            }
        }

        viewModel.list.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }

        return v;
    }

}