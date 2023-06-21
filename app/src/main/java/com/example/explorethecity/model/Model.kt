package com.example.explorethecity.model

import com.squareup.moshi.Json

data class photo(
    val id: String,
    @Json(name = "img_src")
    val img_src: String,
)
data class Event(
    val name: Name,
    val capital: Any,
  //  val language: Any
    /*
    val id: String,
    val url: String,
    val locale: String,
    val images: List<Image>,
    val dates: Date
     */
)
data class Name(
    val common:Any,
    val official:Any,
    val nativeName:Any
)
data class Date(
    val start: Start

)

data class Start (
    val localDate: String,
    val localTime: String
)

data class Image (
    val ratio: String,
    val url: String
)

data class Place(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val location: String,
    val category: String
)
data class User(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val city: String

)