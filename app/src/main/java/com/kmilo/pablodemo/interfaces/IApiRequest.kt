package com.kmilo.pablodemo.interfaces

import com.kmilo.pablodemo.entities.CatJson
import retrofit2.Call
import retrofit2.http.GET

interface IApiRequest {

    @GET("/facts/random")
    fun getCatFacts(): Call<CatJson>
}