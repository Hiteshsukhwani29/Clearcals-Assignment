package com.hitesh.clearcalsassignment.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hitesh.clearcalsassignment.R
import com.hitesh.clearcalsassignment.models.Result

class RecipePagingAdapter: PagingDataAdapter<Result, RecipePagingAdapter.RecipeViewHolder>(COMPARATOR) {

    class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val recipename = itemView.findViewById<TextView>(R.id.recipe_name)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.recipename.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }

}