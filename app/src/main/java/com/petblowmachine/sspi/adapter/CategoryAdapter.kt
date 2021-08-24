package com.petblowmachine.sspi.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.petblowmachine.sspi.activities.MachinesActivity
import com.petblowmachine.sspi.R
import com.petblowmachine.sspi.modal.Applic
import com.petblowmachine.sspi.modal.Category
import com.petblowmachine.sspi.modal.MachineInfo

class CategoryAdapter(private var arrayList:ArrayList<Category>, private val context: Context):
    RecyclerView.Adapter<CategoryAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.category_single_row,parent,false)
        return ItemHolder(itemHolder)
    }

    override fun onBindViewHolder(holder:ItemHolder, position: Int) {
        val category = arrayList[position]
        holder.button.setOnClickListener {
            val intent = Intent(context, MachinesActivity::class.java)
            context.startActivity(intent)
        }
        Glide.with(context).load(category.categoryImg).centerCrop().error(R.drawable.no_img).into(holder.categoryImg)
        holder.categoryName.text = category.categoryName

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MachinesActivity::class.java)
            Applic.categoryName = category.categoryName
            startActivity(context,intent,null)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun updateList(list: ArrayList<Category>) {
        arrayList = list
        notifyDataSetChanged()
    }

    class ItemHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var categoryImg:ImageView = itemView.findViewById(R.id.imgCategory)
        var categoryName: TextView = itemView.findViewById(R.id.txtCategoryName)
        var button:ImageView = itemView.findViewById(R.id.btnViewAll)
    }
}