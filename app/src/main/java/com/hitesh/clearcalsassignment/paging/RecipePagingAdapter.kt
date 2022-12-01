package com.hitesh.clearcalsassignment.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hitesh.clearcalsassignment.R
import com.hitesh.clearcalsassignment.models.Result
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class RecipePagingAdapter: PagingDataAdapter<Result, RecipePagingAdapter.RecipeViewHolder>(COMPARATOR) {

    class RecipeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val recipeName: TextView = itemView.findViewById(R.id.recipe_name)
        val recipeThumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
        val recipeCreatedDate: TextView = itemView.findViewById(R.id.created_date)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.recipeName.text = item.name
            val date = Date(item.created_at)
            val format = SimpleDateFormat("dd-MM-yyyy", Locale.US)
            holder.recipeCreatedDate.text = format.format(date)
            Picasso.get().load(item.thumbnail_url).into(holder.recipeThumbnail)
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