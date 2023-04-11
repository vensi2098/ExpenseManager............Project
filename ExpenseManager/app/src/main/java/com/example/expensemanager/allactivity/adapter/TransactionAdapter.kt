package com.example.expensemanager.allactivity.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.expensemanager.R
import com.example.expensemanager.allactivity.model.IncomeExpenceModel

class TransactionAdapter( var incomeExpenselist: ArrayList<IncomeExpenceModel>,var xyz:((IncomeExpenceModel)->Unit),var delete:((Int)-> Unit)) : RecyclerView.Adapter<TransactionAdapter.MyViewholder>() {


    class MyViewholder (v:View):RecyclerView.ViewHolder(v){
        var type:TextView=v.findViewById(R.id.txtIncomeExp)
        var amount:TextView=v.findViewById(R.id.txtAmount)
        var category:TextView=v.findViewById(R.id.txtCatergory)
        var date:TextView=v.findViewById(R.id.txtDate)
        var mode:TextView=v.findViewById(R.id.txtMode)
        var note:TextView=v.findViewById(R.id.txtNote)
        var editImg:ImageView=v.findViewById(R.id.imgEdit)
        var delete:ImageView=v.findViewById(R.id.imgDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        var View=LayoutInflater.from(parent.context).inflate(R.layout.incom_expense_iteam,parent,false)
        var v=MyViewholder(View)
        return v
    }

    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.type.setText(incomeExpenselist[position].type.toString())
        holder.amount.setText(incomeExpenselist[position].amount.toString())
        holder.category.setText(incomeExpenselist[position].category)
        holder.date.setText(incomeExpenselist[position].date)
        holder.mode.setText(incomeExpenselist[position].mode)
        holder.note.setText(incomeExpenselist[position].note)

        if(holder.type.text.toString()=="1")
        {
            holder.type.setBackgroundColor(Color.GREEN)
            Log.e("TAG", "green: "+holder.type.text.toString() )
            var incomeAmt1=holder.amount.text.toString()
            var incomeAmt=incomeAmt1.toInt()
            Log.e("TAG", "income: "+incomeAmt)
        }
        else
        {
            holder.type.setBackgroundColor(Color.RED)
            Log.e("TAG", "red: "+holder.type.text.toString() )
            var incomeAmt1=holder.amount.text.toString()
            var expenseAmt=incomeAmt1.toInt()
            Log.e("TAG", "income: "+expenseAmt)

        }
        holder.editImg.setOnClickListener {
            xyz.invoke(incomeExpenselist[position])
        }
        holder.delete.setOnClickListener {
            delete.invoke(incomeExpenselist[position].id)
        }

    }

    override fun getItemCount(): Int {
        return incomeExpenselist.size
    }
    fun uapdateData(incomeExpenselist: ArrayList<IncomeExpenceModel>)
    {
        this.incomeExpenselist=ArrayList()
        this.incomeExpenselist.addAll(incomeExpenselist)
        notifyDataSetChanged()
    }



}