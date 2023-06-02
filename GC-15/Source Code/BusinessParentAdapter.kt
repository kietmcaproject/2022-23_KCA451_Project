package com.app.pepens.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.pepens.fragment.BusinessProductsFragment
import com.app.pepens.model.ItemCategories.ItemCategoriesData

class BusinessParentAdapter(private var fragment: FragmentActivity, private var itemCategoryData: ArrayList<ItemCategoriesData>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return itemCategoryData.size
    }

    override fun createFragment(position: Int): Fragment {
        if (BusinessProductsFragment.categoryPosition != position) {
            BusinessProductsFragment.subCategoryId = 0
        }
        val fragment: Fragment = BusinessProductsFragment().newInstance()
        val bundle = Bundle()
        bundle.putInt("CategoryId", itemCategoryData[position].itemCategoryId)
        fragment.arguments = bundle
        return fragment
    }

}