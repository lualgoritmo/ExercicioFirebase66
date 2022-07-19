package br.com.exerciciofirebase.ui.login.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.com.exerciciofirebase.constants.USER_KEY
import br.com.exerciciofirebase.databinding.ActivityLoginBinding
import br.com.exerciciofirebase.domain.model.User
import br.com.exerciciofirebase.ui.home.view.HomeActivity
import br.com.exerciciofirebase.ui.login.viewmodel.LoginViewModel
import br.com.exerciciofirebase.ui.register.view.RegisterActivity
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.txtToRegister?.setOnClickListener {
            initRegister()
        }
        binding.btnLoginUser?.setOnClickListener {
            val user = getUserData()
            viewModel.validateUserData(user)
        }
        initObserve()
    }

    private fun initRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun getUserData(): User {
        var email = binding.edtEmail.text.toString()
        var password = binding.edtPassword?.text.toString()
        return User(email, password)
    }

    private fun homeGoTo(user: User) {
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra(USER_KEY, user)
        }
        startActivity(intent)
    }

    private fun initObserve() {
        viewModel.userLoginTo.observe(this) {
            homeGoTo(it)
        }
        viewModel.errorState.observe(this) {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }
    }
}