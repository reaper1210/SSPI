package com.petblowmachine.sspi

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.petblowmachine.sspi.fragment.Categories
import com.petblowmachine.sspi.fragment.Profile
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    lateinit var viewpager:ViewPager2
    lateinit var searchEdtText:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager = findViewById(R.id.viewpager)
        searchEdtText = findViewById(R.id.searchEdtTxtMainAct)

        val fragmentList = arrayListOf(
            Categories(),
            Profile()
        )
        val adapter = ViewPagerAdapter(
            fragmentList,
            this.supportFragmentManager,
            lifecycle
        )

        viewpager.adapter = adapter

        viewpager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if(position==1){
                    searchEdtText.visibility = View.GONE
                }
                else{
                    searchEdtText.visibility = View.VISIBLE
                }
            }
        })

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            if(position == 0){
                tab.text = "Categories"
            }
            else{
                tab.text = "Profile"
            }
        }.attach()

        for (i in 0 until tabLayout.tabCount) {
            val tab = (tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as MarginLayoutParams
            p.setMargins(0, 0, 20, 0)
            tab.requestLayout()
        }

    }
}