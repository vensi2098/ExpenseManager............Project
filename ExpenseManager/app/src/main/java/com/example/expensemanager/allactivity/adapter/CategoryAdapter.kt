package com.example.expensemanager.allactivity.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.R
import com.example.expensemanager.allactivity.model.CategoryModel

class CategoryAdapter(var list: ArrayList<CategoryModel>,var n:((String)->Unit)) :
    RecyclerView.Adapter<CategoryAdapter.MyViewholder>() {

    var pos = -1

    class MyViewholder(vv: View) : RecyclerView.ViewHolder(vv) {

//        var name: TextView = vv.findViewById(R.id.txtRadioBtn)
        var btnRb: RadioButton = vv.findViewById(R.id.btnRb)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.dailog_iteam, parent, false)
        var v = MyViewholder(view)
        return v
    }

    override fun onBindViewHolder(holder : MyViewholder, position: Int) {
        holder.btnRb.text = list[position].name
        holder.btnRb.setOnClickListener {

            n.invoke(list[position].name)
            Log.e("TAG", "onBindViewHolder: "+list[position].name )
            pos = position
            notifyDataSetChanged()

        }
        if (position==pos)
        {
            holder.btnRb.isChecked=true
        }
        else
        {
            holder.btnRb.isChecked=false
        }
        holder.btnRb.isChecked = position == pos

    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateData(list: ArrayList<CategoryModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}