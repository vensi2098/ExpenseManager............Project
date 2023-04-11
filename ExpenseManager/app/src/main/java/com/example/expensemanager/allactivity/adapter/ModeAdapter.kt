package com.example.expensemanager.allactivity.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.R

class ModeAdapter(var mode: ArrayList<String>,var n1:((String)->Unit)) : RecyclerView.Adapter<ModeAdapter.MyViewholder>() {
    var poss=-1
    class MyViewholder(v:View):RecyclerView.ViewHolder(v) {

        var rbMode:RadioButton=v.findViewById(R.id.rbMode)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        var View=LayoutInflater.from(parent.context).inflate(R.layout.mode_item,parent,false)
        var v=MyViewholder(View)
        return v
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.rbMode.setText(mode[position])

        holder.rbMode.setOnClickListener {
            n1.invoke(mode[position])
//            Log.e("TAG", "onBindViewHolder: "+mode[position] )
            poss = position
            notifyDataSetChanged()
        }
        if (position == poss) {
            holder.rbMode.isChecked = true
        } else {
            holder.rbMode.isChecked = false
        }

    }

    override fun getItemCount(): Int {
        return mode.size
    }
}