package com.petblowmachine.sspi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.petblowmachine.sspi.adapter.MachineAdapter

class MachinesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var gridLayout: LinearLayoutManager
    private lateinit var machinesAdapter: MachineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_machines)

        recyclerView = findViewById(R.id.machinesRecyclerView)
        gridLayout = LinearLayoutManager(this)

        val arrList = ArrayList<String>()
        arrList.add("Automatic Blowing")
        arrList.add("Semi Auto Dropping")
        arrList.add("4 Cavity Fully Automatic")
        arrList.add("Bottle Blow Machine")
        arrList.add("Blow Moulding Machine")
        arrList.add("Automatic PET Stretch")
        arrList.add("4 Cavity Pet Blow")
        arrList.add("Semi Automatic Pet Blow")
        arrList.add("Fully Automatic PET")
        arrList.add("Semi Auto Dropping")

        machinesAdapter = MachineAdapter(arrList,this)
        recyclerView.layoutManager = gridLayout
        recyclerView.adapter = machinesAdapter

    }
}