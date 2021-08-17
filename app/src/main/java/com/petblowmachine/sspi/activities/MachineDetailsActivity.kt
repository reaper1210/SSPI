package com.petblowmachine.sspi.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SnapshotMetadata
import com.google.firestore.v1.Value
import com.petblowmachine.sspi.R
import com.petblowmachine.sspi.adapter.MachineDetailsAdapter
import com.petblowmachine.sspi.modal.Applic
import java.security.Key

class MachineDetailsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MachineDetailsAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var txtMachineName:TextView
    private lateinit var db:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_machine_details)

        db = FirebaseFirestore.getInstance()

        txtMachineName = findViewById(R.id.machineDetailsMachineName)
        recyclerView = findViewById(R.id.machineDetailsRecyclerView)

        txtMachineName.text = Applic.machineName

        db.collection("categories").document(Applic.categoryName).collection("Machines")
            .document(Applic.machineName).collection("details").document("details").get()
            .addOnCompleteListener {
                if(it.isSuccessful){
                    val map: Map<String,String> = (it.result?.data as Map<String, String>?)!!

                    val keySet: Set<String> = map.keys

                    val listOfKeys = ArrayList(keySet)

                    val values: Collection<String> = map.values

                    val listOfValues = ArrayList(values)

                    println("Key: ${listOfKeys}")
                    println("Values: $listOfValues")

                    adapter = MachineDetailsAdapter(this,listOfKeys,listOfValues)
                    layoutManager = LinearLayoutManager(this)
                    val dividerItemDecoration = DividerItemDecoration(recyclerView.context,layoutManager.orientation)
                    recyclerView.addItemDecoration(dividerItemDecoration)
                    recyclerView.layoutManager = layoutManager
                    recyclerView.adapter = adapter
                }
            }

    }
}