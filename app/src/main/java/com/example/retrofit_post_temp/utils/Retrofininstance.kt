package com.example.retrofit_post_temp.utils

import com.example.retrofit_post_temp.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofininstance {

    val api: ApiInterface by lazy{
        Retrofit.Builder()
            .baseUrl(Util.baseUrlDog)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

    }

}