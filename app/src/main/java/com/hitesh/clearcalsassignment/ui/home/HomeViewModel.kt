package com.hitesh.clearcalsassignment.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hitesh.clearcalsassignment.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val recipeRepository: RecipeRepository) : ViewModel() {
    val list = recipeRepository.getRecipe().cachedIn(viewModelScope)
}