package com.example.producthub.views.fragments.auth.login

import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.producthub.R
import com.example.producthub.databinding.FragmentLoginBinding
import com.example.producthub.utilities.gone
import com.example.producthub.utilities.visible
import com.example.producthub.viewmodels.AuthViewModel
import com.example.producthub.views.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        observeData()

        binding.buttonLogIn.setOnClickListener {
            loginUser()
        }

        binding.textView5.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSignupFragment())
        }


        binding.imageViewEye.setOnClickListener {

            showPassword(binding.editText3, binding.imageViewEye)
        }
    }


    private fun showPassword(editText: EditText, imageView: ImageView) {
        val isPasswordVisible =
            editText.inputType != InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        editText.inputType = if (isPasswordVisible) {
            imageView.setImageResource(R.drawable.eye)
            InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            imageView.setImageResource(R.drawable.hidden)
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }

        editText.setSelection(editText.text.length)
    }


    private fun loginUser() {

        lifecycleScope.launch(Dispatchers.Main) {

            val email = binding.editText2.text.toString().trim()
            val password = binding.editText3.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(email, password)
            }

        }

    }


    private fun observeData() {

        viewModel.isLogin.observe(viewLifecycleOwner) {
            if (it) {
                setUserAuth()

                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            } else {
                Toast.makeText(context, "Login Faild", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {

            if(it) {
                binding.progressBarContainer.progressBar2.visible()
                binding.headerConstraintLayout.gone()
            }
            else{
                binding.progressBarContainer.progressBar2.gone()
                binding.headerConstraintLayout.visible()
            }
        }

    }

    private fun setUserAuth() {
        val sp = requireActivity().getSharedPreferences("firebase_local", Context.MODE_PRIVATE)

        sp.edit().putBoolean("isAuth", true).apply()
    }
}