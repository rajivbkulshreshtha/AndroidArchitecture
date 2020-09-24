package com.example.androidarchitecture.mvc

import com.example.androidarchitecture.model.NetworkService
import com.example.androidarchitecture.util.plus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CountriesController constructor(val view: MvcActivity) {

	private var networkService: NetworkService

	private var bag = CompositeDisposable()

	init {
		networkService = NetworkService()
		fetchCountries()
	}

	private fun fetchCountries() {
		bag + networkService.getCounties()
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({
				val values = it.mapNotNull { country -> country.name }
				view.setValues(values)
			}, {
				view.onError()
			})
	}

	fun retry() {
		fetchCountries()
	}

	fun clear() {
		bag.clear()
	}
}
