package com.example.androidlesson22.views.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.androidlesson22.R
import com.example.androidlesson22.databinding.FragmentSplashBinding
import com.example.androidlesson22.views.fragments.base.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateScreen()
    }

    private fun navigateScreen() {
        lifecycleScope.launch {
            val isAuth = getAuth()
            delay(3000)
            if (isAuth) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            } else {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToWelcomeFragment())
            }

        }
    }

    private fun getAuth(): Boolean {
        val sp = requireActivity().getSharedPreferences("firebase_local", Context.MODE_PRIVATE)

        return sp.getBoolean("isAuth", false)
    }


}