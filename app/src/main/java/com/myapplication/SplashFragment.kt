package com.myapplication

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Simulate some splash screen loading etc.
        Handler().postDelayed({
            findNavController().navigate(SplashFragmentDirections.showTabs())
        }, 500)

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

}
