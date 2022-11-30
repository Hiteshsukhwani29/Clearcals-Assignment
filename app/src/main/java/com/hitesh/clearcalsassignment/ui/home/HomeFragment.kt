package com.hitesh.clearcalsassignment.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hitesh.clearcalsassignment.R
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        recyclerView = v.findViewById(R.id.rv_recipe)
        adapter = RecipePagingAdapter()

        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.hasFixedSize()
        recyclerView.adapter = adapter

        viewModel.list.observe(viewLifecycleOwner) {
            adapter.submitData(lifecycle, it)
        }

        return v;
    }

}