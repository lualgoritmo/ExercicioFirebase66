package br.com.exerciciofirebase.ui.home.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.exerciciofirebase.databinding.ActivityHomeBinding
import br.com.exerciciofirebase.ui.home.viewmodel.HomeViewModel
import br.com.exerciciofirebase.ui.login.view.LoginActivity

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
        binding.btnLoginExit.setOnClickListener { exitLogin() }
    }

    private fun displayUser() {
        val name = viewModel.getUserName()
        val email = viewModel.getUserEmail()
        binding.nameUserHome.text = "Nome: $name"
        binding.userEmailHome.text = "Email: $email"
    }

    private fun exitLogin() {
        viewModel.logout()
        this.finish()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}