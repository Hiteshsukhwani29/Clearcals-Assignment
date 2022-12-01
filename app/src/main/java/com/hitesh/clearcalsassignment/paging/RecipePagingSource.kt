package com.hitesh.clearcalsassignment.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hitesh.clearcalsassignment.models.Result
import com.hitesh.clearcalsassignment.retrofit.RecipeAPI

class RecipePagingSource(private val recipeAPI: RecipeAPI): PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        } 
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val position =  params.key?:1
            val response = recipeAPI.getRecipes(position)
            LoadResult.Page(
                data = response.results,
                prevKey = if (position==1) null else position - 10,
                nextKey = if (position==response.count+10) null else position + 10
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}