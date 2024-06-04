package com.example.androidlesson22.views.fragments.auth.signup

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.androidlesson22.R
import com.example.androidlesson22.databinding.FragmentSignupBinding
import com.example.androidlesson22.databinding.FragmentWelcomeBinding
import com.example.androidlesson22.utilities.gone
import com.example.androidlesson22.utilities.loadimagewithidWithoutBindingAdapter
import com.example.androidlesson22.utilities.visible
import com.example.androidlesson22.viewmodels.AuthViewModel
import com.example.androidlesson22.views.fragments.auth.login.LoginFragmentDirections
import com.example.androidlesson22.views.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding>(FragmentSignupBinding::inflate) {

    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()

        binding.buttonSignUp.setOnClickListener {
            registerUser()
        }

        binding.textView5.setOnClickListener {
            findNavController().navigate(SignupFragmentDirections.actionSignupFragmentToLoginFragment())
        }


        binding.imageViewEye.setOnClickListener {

            showPassword(binding.editText3, binding.imageViewEye)
        }

        binding.imageViewEye2.setOnClickListener {


            showPassword(binding.editText4, binding.imageViewEye2)
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



    private fun observeData() {
        viewModel.isLogin.observe(viewLifecycleOwner) {
            if (it) {

                findNavController().navigate(SignupFragmentDirections.actionSignupFragmentToLoginFragment())
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
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

    private fun registerUser() {

        lifecycleScope.launch(Dispatchers.Main) {

            val fullname = binding.editText.text.toString().trim()
            val email = binding.editText2.text.toString().trim()
            val password = binding.editText3.text.toString().trim()
            val password2 = binding.editText4.text.toString().trim()

            if (fullname.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && password2.isNotEmpty()) {

                if (password == password2) {
                    viewModel.register(email, password)
                } else {
                    Toast.makeText(context, "Password is false", Toast.LENGTH_LONG).show()
                }


            }
            else{
                Toast.makeText(context, "Dedect empty field", Toast.LENGTH_LONG).show()
            }
        }
    }
}