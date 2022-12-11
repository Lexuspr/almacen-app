package com.example.buses

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.buses.databinding.ActivityRegisterBinding
import com.example.buses.util.LoadingView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var loader: LoadingView
    private val viewModel by viewModels<AuthenticationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loader = LoadingView(this)

        setClickListeners()

        viewModel.registerResult.observe(this) {
            if (it != null) {
                if (it) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Ocurrió un error. Intente más tarde.", Toast.LENGTH_SHORT).show()
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
        binding.btnRegister.setOnClickListener {
            if (checkForm()) {
                viewModel.register(
                    name = binding.inputName.text.toString(),
                    email = binding.inputEmail.text.toString(),
                    password = binding.inputPassword.text.toString()
                )
            }
        }

        binding.lblLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun checkForm(): Boolean {
        var errorCount = 0

        binding.inputNameLayout.error = null
        binding.inputEmailLayout.error = null
        binding.inputPasswordLayout.error = null
        binding.inputRepeatPasswordLayout.error = null

        if (binding.inputName.text.toString().isEmpty()) {
            binding.inputNameLayout.error = "El nombre es obligatorio"
            errorCount++
        }

        if (binding.inputEmail.text.toString().isEmpty()) {
            binding.inputEmailLayout.error = "El email es obligatorio"
            errorCount++
        }

        if (binding.inputPassword.text.toString().isEmpty()) {
            binding.inputPasswordLayout.error = "La contraseña es obligatoria"
            errorCount++
        } else {
            if (binding.inputPassword.text.toString() != binding.inputRepeatPassword.text.toString()) {
                binding.inputRepeatPasswordLayout.error = "La contraseña debe coincidir"
                errorCount++
            }
        }

        return errorCount == 0
    }
}