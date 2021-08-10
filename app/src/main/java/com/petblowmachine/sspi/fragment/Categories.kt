package com.petblowmachine.sspi.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.petblowmachine.sspi.R
import com.petblowmachine.sspi.adapter.CategoryAdapter

class Categories : Fragment() {
    private lateinit var recyclerView:RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var arrayList: ArrayList<String>
    private lateinit var adapter:CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("hello")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        recyclerView = view.findViewById(R.id.categoryRecyclerView)
        if(activity!=null){
            gridLayoutManager = GridLayoutManager(activity as Context,2)
            recyclerView.layoutManager = gridLayoutManager
            arrayList = ArrayList()
            arrayList.add("Category 1")
            arrayList.add("Category 2")
            arrayList.add("Category 3")
            arrayList.add("Category 4")
            arrayList.add("Category 5")
            arrayList.add("Category 6")
            arrayList.add("Category 7")
            arrayList.add("Category 8")
            arrayList.add("Category 9")
            arrayList.add("Category 10")
            adapter = CategoryAdapter(arrayList)
            recyclerView.adapter = adapter
        }
        else{
            println("null")
        }
        return view
    }
}