package com.petblowmachine.sspi.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.petblowmachine.sspi.R
import com.petblowmachine.sspi.adapter.CategoryAdapter
import com.petblowmachine.sspi.modal.Applic
import com.petblowmachine.sspi.modal.Category
import java.lang.Appendable


class Categories : Fragment() {
    private lateinit var recyclerView:RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var arrayList: ArrayList<Category>
    private lateinit var adapter:CategoryAdapter
    private lateinit var searchEdtText: EditText
    private lateinit var txtNoCategories: TextView
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        recyclerView = view.findViewById(R.id.categoryRecyclerView)
        searchEdtText = view.findViewById(R.id.searchEdtTxtCatFrag)
        txtNoCategories = view.findViewById(R.id.txtNoCategories)
        db = FirebaseFirestore.getInstance()
        arrayList = ArrayList()

        fetchData()

        searchEdtText.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                filter(p0.toString())
            }

        })

        return view
    }

    private fun fetchData(){
        if(activity!=null){
            db.collection("categories")
                .get()
                .addOnSuccessListener {
                    for(document in it){
                        arrayList.add(Category(document.id,document["categoryImg"].toString()))
                    }
                    Applic.catArray = arrayList
                    adapter = CategoryAdapter(arrayList,requireContext())
                    gridLayoutManager = GridLayoutManager(requireContext(),2)
                    recyclerView.layoutManager = gridLayoutManager
                    recyclerView.adapter = adapter

                    db.collection("categories").addSnapshotListener { value, error ->
                        val list = ArrayList<Category>()
                        for(doc in value!!){
                            list.add(Category(doc.id,doc["categoryImg"].toString()))
                        }
                        adapter.updateList(list)
                    }
                    if(arrayList.isEmpty()){
                        txtNoCategories.visibility = View.VISIBLE
                    }
                    else{
                        txtNoCategories.visibility = View.GONE
                    }
                }
                .addOnFailureListener {
                }
        }
        else{
            println("null")
        }
    }

    fun filter(text: String?) {
        val temp: ArrayList<Category> = ArrayList()
        for (i in Applic.catArray) {
            if ((i.categoryName.lowercase()).contains(text.toString().lowercase())) {
                temp.add(i)
            }
        }
        if(temp.isEmpty()) {
            txtNoCategories.visibility = View.VISIBLE
        }
        else{
            txtNoCategories.visibility = View.GONE
        }
        adapter.updateList(temp)
    }
}