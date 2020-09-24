package com.example.androidarchitecture.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidarchitecture.model.NetworkService
import com.example.androidarchitecture.util.plus
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CountriesViewModel : ViewModel() {

	private var networkService: NetworkService
	private var bag = CompositeDisposable()

	private val _countries = MutableLiveData<List<String>>()
	private val _error = MutableLiveData<Boolean>()

	init {
		networkService = NetworkService()
		fetchCountries()
	}

	fun getCountries(): LiveData<List<String>> = _countries
	fun getError(): LiveData<Boolean> = _error

	private fun fetchCountries() {
		bag + networkService.getCounties()
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe({
				val values = it.mapNotNull { country -> country.name }
				_countries.value = values
				_error.value = false
			}, {
				_error.value = true
			})
	}

	override fun onCleared() {
		bag.clear()
		super.onCleared()
	}
}
