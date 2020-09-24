package com.example.androidarchitecture.mvvm

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidarchitecture.R
import com.example.androidarchitecture.util.hide
import com.example.androidarchitecture.util.show
import kotlinx.android.synthetic.main.activity_mvp.*

class MvvmActivity : AppCompatActivity() {
	private val values = arrayListOf<String>()
	private lateinit var arrayAdapter: ArrayAdapter<String>
	private lateinit var viewModel: CountriesViewModel
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_mvvm)
		viewModel = ViewModelProviders.of(this).get(CountriesViewModel::class.java)
		setUp(this)
		clickHandler()
		observeViewModel()
	}

	fun observeViewModel() {
		viewModel.getCountries().observe(this, Observer { countries ->
			if (countries.isNullOrEmpty()) {
				listView.hide()
			} else {
				values.clear()
				values.addAll(countries)
				listView.show()
				arrayAdapter.notifyDataSetChanged()
			}
		})

		viewModel.getError().observe(this, Observer { error ->
			pb.hide()
			if (error) {
				btnRetry.show()
			} else {
				btnRetry.hide()
			}
		})
	}

	private fun clickHandler() {
		btnRetry.setOnClickListener { onRetry() }
	}

	private fun setUp(context: Context) {
		arrayAdapter = ArrayAdapter(context, R.layout.adapter_country, R.id.tvCountry, values)
		listView.adapter = arrayAdapter
		listView.setOnItemClickListener { _, _, position, _ ->
			Toast.makeText(context, "Country: ${values[position]}", Toast.LENGTH_SHORT).show()
		}
	}

	private fun onRetry() {
		listView.hide()
		btnRetry.hide()
		pb.show()
	}
}
