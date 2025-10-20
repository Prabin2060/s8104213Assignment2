package com.example.s8104213assignment2.models


import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
data class LoginRequest(
    val username: String,
    val password: String
)

@JsonClass(generateAdapter = true)
data class LoginResponse(
    val keypass: String
)

@Parcelize
@JsonClass(generateAdapter = true)
data class Entity(
    val title: String?,       // This was property1
    val director: String?,   // This was property2
    val genre: String?,      // This is new
    val releaseYear: Int?,  // This is new (and an Int, not a String)
    val description: String?
) : Parcelable

@JsonClass(generateAdapter = true)
data class DashboardResponse(
    val entities: List<Entity>,
    val entityTotal: Int
)
