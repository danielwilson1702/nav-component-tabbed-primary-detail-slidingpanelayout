package com.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_master.view.*


class MasterFragment : Fragment() {

    val args by navArgs<MasterFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_master, container, false)

        rootView.master_text.text = "Master view, tab number: ${args.tabNumber}"
        rootView.detail_navigate_button.setOnClickListener { openDetail() }
        return rootView
    }

    fun openDetail(){

        val isTablet = context?.resources?.getBoolean(R.bool.isTablet) ?: false
        when{
            //On tablets, replace the detail fragment with a new-arg'd fragment
            //On phones, launch a new detail activity
            isTablet -> swapDetailFragment()
            else -> findNavController().navigate(MasterFragmentDirections.showDetailsFromMaster(args.tabNumber, "I am on a phone"))
        }
    }

    private fun swapDetailFragment() {
        parentFragment?.let {
            val detail = it.fragmentManager?.findFragmentById(R.id.detail_nav_fragment) as NavHostFragment?
            if (detail != null) {
                val navController = detail.navController
                val navInflater = navController.navInflater
                val graph = navInflater.inflate(R.navigation.detail)

                //Override the arguments here with whatever we need to produce this detail fragment
                val forwardedArguments = args.toBundle()
                forwardedArguments.putString("some_extra_info", "I am on a tablet")
                detail.navController.setGraph(graph, forwardedArguments)
            }
        }
    }
}
