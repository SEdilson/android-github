package com.sedilson.android_github.models

class Repository(
    val name: String,
    val description: String,
    val stargazers_count: Int,
    val forks_count: Int,
    val owner: User
)