package com.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_tabs.view.*


class ItemTabsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_tabs, container, false)

        val adapter = context?.let { it -> TabsPagerAdapter(childFragmentManager, it, 3) }
        rootView.viewpager.adapter = adapter
        rootView.tab_layout.setupWithViewPager(rootView.viewpager)

        for (i in 0 until rootView.tab_layout.tabCount) {
            when (i) {
                0 -> rootView.tab_layout.getTabAt(i)!!.text = "TAB 1"
                1 -> rootView.tab_layout.getTabAt(i)!!.text = "TAB 2"
                2 -> rootView.tab_layout.getTabAt(i)!!.text = "TAB 3"
            }
        }

        return rootView
    }
}
