package com.example.retrofit_post_temp


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {



    @GET("woof.json?ref=apilist.fun")
    suspend fun getRandomDog(): Response<ApiData>




}