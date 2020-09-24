package com.example.androidarchitecture.model

import io.reactivex.Single
import retrofit2.http.GET

interface Routes {
	@GET("all")
	fun getCountries(): Single<List<Country>>
}
