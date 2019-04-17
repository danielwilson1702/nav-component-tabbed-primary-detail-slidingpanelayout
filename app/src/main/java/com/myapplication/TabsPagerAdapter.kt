package com.myapplication

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabsPagerAdapter(manager: FragmentManager, private val context: Context, private val numTabs: Int) : FragmentPagerAdapter(manager) {

    override fun getItem(position: Int): Fragment {
        val isTablet = context.resources?.getBoolean(R.bool.isTablet) ?: false
        return if(isTablet)
            MasterDetailHostFragment.newInstance(getReadableTabPosition(position))
        else
            MasterHostFragment.newInstance(getReadableTabPosition(position))
    }

    override fun getCount(): Int {
        return numTabs
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return String.format(context.getString(R.string.cat_tab_item_label), getReadableTabPosition(position))
    }

    private fun getReadableTabPosition(position: Int): Int {
        return position + 1
    }
}
