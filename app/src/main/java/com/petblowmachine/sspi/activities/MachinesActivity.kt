package com.petblowmachine.sspi.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.petblowmachine.sspi.R
import com.petblowmachine.sspi.adapter.MachineAdapter
import com.petblowmachine.sspi.modal.Applic
import com.petblowmachine.sspi.modal.MachineInfo

class MachinesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var machinesAdapter: MachineAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_machines)

        db = FirebaseFirestore.getInstance()

        recyclerView = findViewById(R.id.machinesRecyclerView)
        linearLayout = LinearLayoutManager(this)

        val arrList = ArrayList<MachineInfo>()

        db.collection("categories").document(Applic.categoryName).collection("Machines")
            .get()
            .addOnSuccessListener {
                for(document in it){
                    arrList.add(MachineInfo(document.id,document["machineImg"].toString(),
                        document["capacity"].toString(),document["type"].toString(),document["mouldCavity"].toString()))
                    machinesAdapter = MachineAdapter(this, arrList)
                    recyclerView.layoutManager = linearLayout
                    recyclerView.adapter = machinesAdapter

                }
            }
    }
}