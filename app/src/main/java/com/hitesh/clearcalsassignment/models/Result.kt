package com.hitesh.clearcalsassignment.models

data class Result(
    val created_at: Long,
    val description: String,
    val id: Int,
    val name: String,
    val thumbnail_alt_text: String,
    val thumbnail_url: String,
    val country: String
)