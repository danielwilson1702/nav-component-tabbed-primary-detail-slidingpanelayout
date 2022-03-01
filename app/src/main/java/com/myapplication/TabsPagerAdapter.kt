package com.myapplication

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabsPagerAdapter(fragment: Fragment, private val numTabs: Int) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment {
        return PrimaryDetailHostFragment.newInstance(getReadableTabPosition(position))
    }

    override fun getItemCount(): Int = numTabs

    private fun getReadableTabPosition(position: Int): Int {
        return position + 1
    }
}
