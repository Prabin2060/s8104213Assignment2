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
    val property1: String?,
    val property2: String?,
    val description: String?
) : Parcelable

@JsonClass(generateAdapter = true)
data class DashboardResponse(
    val entities: List<Entity>,
    val entityTotal: Int
)
