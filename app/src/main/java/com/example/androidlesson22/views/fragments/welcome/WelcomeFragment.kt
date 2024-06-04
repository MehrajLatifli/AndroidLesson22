package com.example.androidlesson22.views.fragments.welcome

import android.R
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.androidlesson22.databinding.FragmentWelcomeBinding
import com.example.androidlesson22.views.fragments.auth.login.LoginFragmentDirections
import com.example.androidlesson22.views.fragments.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





            binding.textView5.setOnClickListener {
                findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment())
            }

        binding.buttonSignup.setOnClickListener {
            findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToSignupFragment())
        }

    }

}