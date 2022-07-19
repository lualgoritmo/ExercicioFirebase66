package br.com.exerciciofirebase.ui.home.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.exerciciofirebase.R
import br.com.exerciciofirebase.databinding.ActivityHomeBinding
import br.com.exerciciofirebase.ui.home.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayUser()
    }
    private fun displayUser(){
        val name = viewModel.getUserEmail()
        binding.nameUserHome.text = "$name"
    }
}