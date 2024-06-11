package com.example.producthub.views.fragments.welcome

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.producthub.databinding.FragmentWelcomeBinding
import com.example.producthub.views.fragments.base.BaseFragment
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