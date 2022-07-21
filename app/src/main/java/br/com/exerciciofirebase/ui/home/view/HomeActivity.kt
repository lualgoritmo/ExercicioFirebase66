package br.com.exerciciofirebase.ui.home.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.exerciciofirebase.databinding.ActivityHomeBinding
import br.com.exerciciofirebase.ui.home.viewmodel.HomeViewModel
import br.com.exerciciofirebase.ui.login.view.LoginActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var listMsg = mutableListOf<String>()
    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }
    private val adapterMsg: AdapterMsg by lazy {
        AdapterMsg(arrayListOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayUser()
        viewModel.getListAllMsg()
        showRecyclerView()
        binding.btnLoginExit.setOnClickListener { exitLogin() }
        binding.saveMsg.setOnClickListener {
            viewModel.saveDatabaseMsg(getMsgUser())
        }
        initObserve()
    }

    private fun displayUser() {
        val name = viewModel.getUserName()
        val email = viewModel.getUserEmail()
        binding.nameUserHome.text = "Nome: $name"
        binding.userEmailHome.text = "Email: $email"
    }

    private fun getMsgUser(): String {
        var msg = binding.etMsg.text.toString()
        listMsg.add(msg)
        return msg
    }

    private fun showRecyclerView() {
        binding.recyclerMsg.adapter = adapterMsg
        binding.recyclerMsg.layoutManager = LinearLayoutManager(this)
    }

    fun initObserve() {
        viewModel.listMsg.observe(this) {
            adapterMsg.updateMsgList(it.toMutableList())
        }
    }

    private fun exitLogin() {
        viewModel.logout()
        this.finish()
        startActivity(Intent(this, LoginActivity::class.java))
    }

}