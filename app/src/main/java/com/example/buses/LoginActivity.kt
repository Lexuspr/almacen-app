package com.example.buses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.buses.databinding.ActivityLoginBinding
import com.example.buses.util.LoadingView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loader: LoadingView
    private val viewModel by viewModels<AuthenticationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loader = LoadingView(this)

        setClickListeners()
        viewModel.loginResult.observe(this) {
            if (it != null) {
                if (it) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.loading.observe(this) {
            if (it) {
                loader.show()
            } else {
                loader.dismiss()
            }
        }
    }

    private fun setClickListeners() {
        binding.btnLogin.setOnClickListener {
            viewModel.login(
                email = binding.inputEmail.text.toString(),
                password = binding.inputPassword.text.toString()
            )
        }
        binding.lblRegisterMe.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}