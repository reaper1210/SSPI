package com.petblowmachine.sspi.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.petblowmachine.sspi.R
import com.petblowmachine.sspi.adapter.MachineDetailsAdapter

class MachineDetailsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MachineDetailsAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_machine_details)

        recyclerView = findViewById(R.id.machineDetailsRecyclerView)
        val arrList = ArrayList<String>()
        arrList.add("One")
        arrList.add("Two")
        arrList.add("Three")
        arrList.add("Four")
        arrList.add("Four")
        arrList.add("Four")
        arrList.add("Four")
        arrList.add("Four")
        arrList.add("Four")
        arrList.add("Four")
        arrList.add("Four")
        arrList.add("Four")
        arrList.add("Four")
        arrList.add("Four")
        arrList.add("Four")

        adapter = MachineDetailsAdapter(this,arrList)
        layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

    }
}