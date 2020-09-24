package com.example.androidarchitecture.model


import com.google.gson.annotations.SerializedName

data class Country(
	@SerializedName("name")
	var name: String? // India
)
