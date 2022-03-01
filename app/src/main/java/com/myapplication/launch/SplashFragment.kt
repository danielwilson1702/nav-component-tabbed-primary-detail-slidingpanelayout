package com.myapplication.launch

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.myapplication.R


class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_splash, container, false)

        // Simulate some splash screen loading etc.
        // Custom splash screens aren't recommended any longer (https://developer.android.com/guide/topics/ui/splash-screen/migrate),
        // it's just to show how to load our tabs from another fragment
        Handler().postDelayed({
            findNavController().navigate(SplashFragmentDirections.showTabs())
        }, 300)

        return root
    }

}
