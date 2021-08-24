package com.petblowmachine.sspi.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.petblowmachine.sspi.fragment.Categories
import com.petblowmachine.sspi.fragment.Profile
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.content.res.AppCompatResources
import com.petblowmachine.sspi.R
import com.petblowmachine.sspi.adapter.ViewPagerAdapter
import com.petblowmachine.sspi.modal.Applic
import com.petblowmachine.sspi.modal.Category
import com.petblowmachine.sspi.modal.MachineInfo

class MainActivity : AppCompatActivity() {
    private lateinit var viewpager:ViewPager2
    private lateinit var topLayout: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        viewpager = findViewById(R.id.viewpager)
        topLayout = findViewById(R.id.topLayoutMainAct)

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