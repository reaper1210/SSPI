package com.petblowmachine.sspi.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.petblowmachine.sspi.R
import com.petblowmachine.sspi.adapter.MachineAdapter
import com.petblowmachine.sspi.modal.Applic
import com.petblowmachine.sspi.modal.MachineInfo
import com.google.android.gms.common.data.DataHolder
import com.google.firebase.firestore.DocumentChange
import com.petblowmachine.sspi.modal.Category
import java.util.*
import kotlin.collections.ArrayList


class MachinesActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var linearLayout: LinearLayoutManager
    private lateinit var machinesAdapter: MachineAdapter
    private lateinit var edtTxtSearchBar: EditText
    private lateinit var db: FirebaseFirestore
    private lateinit var txtNoMachines: TextView
    private lateinit var arrList: ArrayList<MachineInfo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_machines)

        db = FirebaseFirestore.getInstance()

        recyclerView = findViewById(R.id.machinesRecyclerView)
        edtTxtSearchBar = findViewById(R.id.searchEdtTxtMachinesAct)
        txtNoMachines = findViewById(R.id.txtNoMachines)
        linearLayout = LinearLayoutManager(this)
        txtNoMachines.visibility = View.GONE

        arrList = ArrayList()

        fetchData()

        edtTxtSearchBar.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString())
            }

        })
    }

    private fun filter(text: String?) {
        val temp: ArrayList<MachineInfo> = ArrayList()
        for (i in arrList) {
            if ((i.machineName.lowercase()).contains(text.toString().lowercase())) {
                temp.add(i)
            }
        }
        if(temp.isEmpty()) {
            txtNoMachines.visibility = View.VISIBLE
        }
        else{
            txtNoMachines.visibility = View.GONE
        }

        machinesAdapter.updateList(temp)
    }

    private fun fetchData(){
        arrList.clear()
        db.collection("categories").document(Applic.categoryName).collection("Machines")
            .get()
            .addOnSuccessListener {
                for(document in it){
                    arrList.add(MachineInfo(document.id,document["machineImg"].toString(),
                        document["detail1"].toString(),document["detail2"].toString(),
                        document["detail3"].toString()))
                }
                recyclerView.layoutManager = linearLayout
                machinesAdapter = MachineAdapter(this, arrList)
                recyclerView.adapter = machinesAdapter

                db.collection("categories").document(Applic.categoryName).collection("Machines").addSnapshotListener { value, error ->
                    val list = ArrayList<MachineInfo>()
                    for(doc in value!!){
                        list.add(
                            MachineInfo(doc.id,doc["machineImg"].toString(),doc["detail1"].toString(),doc["detail2"].toString(),
                                doc["detail3"].toString()))
                    }
                    machinesAdapter.updateList(list)
                }
                if(arrList.isEmpty()){
                    txtNoMachines.visibility = View.VISIBLE
                }
                else{
                    txtNoMachines.visibility = View.GONE
                }
            }
    }

}