package com.hitesh.clearcalsassignment.retrofit

import com.hitesh.clearcalsassignment.models.RecipeList
import com.hitesh.clearcalsassignment.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeAPI {

    @GET("/recipes/list?rapidapi-key=${API_KEY}")
    suspend fun getRecipes(@Query("page")page:Int): RecipeList

}