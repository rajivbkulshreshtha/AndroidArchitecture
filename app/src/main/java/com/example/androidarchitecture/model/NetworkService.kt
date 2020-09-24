package com.example.androidarchitecture.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService {
	var routes: Routes

	init {
		val retrofit =
			Retrofit.Builder()
				.baseUrl(Companion.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.build()
		routes = retrofit.create(Routes::class.java)
	}

	fun getCounties(): Single<List<Country>> {
		return routes.getCountries()
	}

	companion object {
		private const val BASE_URL = "https://restcountries.eu/rest/v2/"
	}
}
