package com.example.androidarchitecture.mvc

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidarchitecture.R
import com.example.androidarchitecture.util.hide
import com.example.androidarchitecture.util.show
import kotlinx.android.synthetic.main.activity_mvc.*

class MvcActivity : AppCompatActivity() {
	private val values = arrayListOf<String>()
	private lateinit var arrayAdapter: ArrayAdapter<String>
	private lateinit var controller: CountriesController
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_mvc)
		controller = CountriesController(this)
		setUp(this)
		clickHandler()
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

	fun setValues(_value: List<String>) {
		values.clear()
		values.addAll(_value)
		listView.show()
		btnRetry.hide()
		pb.hide()
		arrayAdapter.notifyDataSetChanged()
	}

	fun onError() {
		btnRetry.show()
		listView.hide()
		pb.hide()
	}

	private fun onRetry() {
		controller.retry()
		listView.hide()
		btnRetry.hide()
		pb.show()
	}

	override fun onDestroy() {
		controller.clear()
		super.onDestroy()
	}
}
