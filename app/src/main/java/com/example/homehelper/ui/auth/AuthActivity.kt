package com.example.homehelper.ui.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.homehelper.R
import com.example.homehelper.SessionManager
import com.example.homehelper.databinding.ActivityAuthBinding
import com.example.homehelper.ui.main.MainActivity
import com.example.homehelper.ui.register.RegisterActivity
import com.example.homehelper.util.Constants
import com.example.homehelper.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener{

    private lateinit var binding: ActivityAuthBinding

    private lateinit var prefs: SharedPreferences

    private val PASSWORD = "password"

    private val EMAIL = "email"

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var sessionManager: SessionManager

    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        init()

        prefs = getSharedPreferences("logIn", Context.MODE_PRIVATE)

        val exitMsg = intent.getStringExtra("Stop")
        if (exitMsg != null && exitMsg == "Stop"){
            return
        }

        if(prefs.contains(EMAIL)){
            viewModel.tryLogin(prefs.getString(EMAIL, "err")!!, prefs.getString(PASSWORD, "err")!!)
            showProgressBar(true)
            subscribeLoginObservers()
        }
    }

    override fun onResume() {
        super.onResume()


    }

    private fun init() {
        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
        binding.loginButton.setOnClickListener(this)
        binding.registerButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v == null)
            return

        when(v.id) {
            R.id.login_button ->{
                viewModel.tryLogin(binding.login.text.toString(), binding.password.text.toString())
                showProgressBar(true)
                subscribeLoginObservers()
            }
            R.id.register_button -> register()
        }
    }

    private fun subscribeLoginObservers() {
        viewModel.loginObserver!!.observe(this){
            Log.d(Constants.TAG, "Auth message result: ${it.token}")
            if(it.token.startsWith("err")) return@observe
            sessionManager.cachedToken = it.token
            login()
        }
    }

    private fun showProgressBar(isVisible: Boolean) {
        if(isVisible) {
            binding.progressBar.visibility = View.VISIBLE
        }
        else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun login() {
        val editor = prefs.edit()
        editor.putString(EMAIL, binding.login.text.toString())
        editor.putString(PASSWORD, binding.password.text.toString())
        editor.apply()

        showProgressBar(false)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun register() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

}