package com.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment


class MasterDetailHostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_master_detail_host, container, false)

        val master = childFragmentManager.findFragmentById(R.id.master_nav_fragment) as NavHostFragment?
        if(master != null){
            val navController = master.navController
            val navInflater = navController.navInflater
            val graph = navInflater.inflate(R.navigation.master)

            master.navController.setGraph(graph, arguments)
        }

        val detail = childFragmentManager.findFragmentById(R.id.detail_nav_fragment) as NavHostFragment?
        if(detail != null){
            val navController = detail.navController
            val navInflater = navController.navInflater
            val graph = navInflater.inflate(R.navigation.detail)

            detail.navController.setGraph(graph, arguments)
        }

        return rootView
    }


    companion object {
        val TAB_NUMBER = "tab_number"
        val SOME_EXTRA_INFO  = "some_extra_info"

        fun newInstance(tabNumber: Int): MasterDetailHostFragment {
            val fragment = MasterDetailHostFragment()
            val bundle = Bundle()
            bundle.putInt(TAB_NUMBER, tabNumber)
            bundle.putString(SOME_EXTRA_INFO, "I am probably on a tablet")
            fragment.arguments = bundle

            return fragment
        }
    }
}
