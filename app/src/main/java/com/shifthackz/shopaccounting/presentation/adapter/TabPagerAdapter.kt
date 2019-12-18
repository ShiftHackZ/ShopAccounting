package com.shifthackz.shopaccounting.presentation.adapter

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.shifthackz.shopaccounting.presentation.fragments.categories.CategoriesFragment
import com.shifthackz.shopaccounting.presentation.fragments.history.HistoryFragment

class TabPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> CategoriesFragment.newInstance()
            1 -> HistoryFragment.newInstance()
            else -> null
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "К А Т Е Г О Р И И"
            1 -> "И С Т О Р И Я"
            else -> null
        }
    }

    override fun getCount(): Int {
        return 2
    }

}
