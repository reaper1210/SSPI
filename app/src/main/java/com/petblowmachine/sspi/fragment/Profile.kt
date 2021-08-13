package com.petblowmachine.sspi.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.petblowmachine.sspi.R
import com.skyhope.expandcollapsecardview.ExpandCollapseCard
import com.skyhope.expandcollapsecardview.ExpandCollapseListener

class Profile : Fragment(),ExpandCollapseListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val cardView1 = view.findViewById<ExpandCollapseCard>(R.id.cardView1)
        val cardView2 = view.findViewById<ExpandCollapseCard>(R.id.cardView2)
        val cardView3 = view.findViewById<ExpandCollapseCard>(R.id.cardView3)
        val cardView4 = view.findViewById<ExpandCollapseCard>(R.id.cardView4)
        var isExpand = false
        var flag = 0
        cardView1.initListener(this)
        cardView2.initListener(this)
        cardView3.initListener(this)
        cardView4.initListener(this)

//        cardView1.setOnClickListener {
//            if(isExpand){
//                cardView1.collapse(cardView1.childView)
//                isExpand = false
//            }
//            else{
//                cardView1.expand(cardView1.childView)
//                isExpand = true
//            }
//        }
        return view
    }
    override fun onExpandCollapseListener(p0: Boolean) {

    }
}