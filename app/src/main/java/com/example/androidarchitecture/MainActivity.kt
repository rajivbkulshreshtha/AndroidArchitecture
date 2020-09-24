package com.example.androidarchitecture

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidarchitecture.mvc.MvcActivity
import com.example.androidarchitecture.mvp.MvpActivity
import com.example.androidarchitecture.mvvm.MvvmActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		clickHandler(this)
	}

	private fun clickHandler(context: Context) {
		btnMVC.setOnClickListener {
			startActivity(Intent(context, MvcActivity::class.java))
		}
		btnMVP.setOnClickListener {
			startActivity(Intent(context, MvpActivity::class.java))
		}
		btnMVVM.setOnClickListener {
			startActivity(Intent(context, MvvmActivity::class.java))
		}
	}
}
