package com.example.explorethecity.network

import com.example.explorethecity.model.Event
import com.example.explorethecity.model.photo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://restcountries.com/v3.1/" //"search?engine=google_events" //b1a9bcd3fc54906cf7655a35b126cc9108fa1a9c61580c7f07c7021862968b4a  //"https://app.ticketmaster.com/discovery/v2/" //events.json?gF180bhXApFnXWMa00CmvqpMAXKD8XgY
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

object eventAPI {
    val retrofitService : EventAPIService by lazy {
        retrofit.create(EventAPIService::class.java) }
}

interface EventAPIService{
    @GET("lang/spanish?fields=name,capital")
    suspend fun getEvents(): List<Event>

}