package com.hitesh.clearcalsassignment.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.hitesh.clearcalsassignment.paging.RecipePagingSource
import com.hitesh.clearcalsassignment.retrofit.RecipeAPI
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val recipeAPI: RecipeAPI) {
    fun getRecipe() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = {RecipePagingSource(recipeAPI)}
    ).liveData
}