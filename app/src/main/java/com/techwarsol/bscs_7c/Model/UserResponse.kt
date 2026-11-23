package com.techwarsol.bscs_7c.Model

data class UserResponse(
    val users: ArrayList<ApiUser>,
    val total: Int,
    val skip: Int,
    val limit: Int,
)

// 2. Each JSON Object inside "users" JSON Array
data class ApiUser(
    val id: Int,
    val firstName: String,
    val lastName: String, val email: String,
    val image: String,
    val company: Company
)

// 3. Nested "company" JSON Object
data class Company(
    val name: String,
    val title: String
)

