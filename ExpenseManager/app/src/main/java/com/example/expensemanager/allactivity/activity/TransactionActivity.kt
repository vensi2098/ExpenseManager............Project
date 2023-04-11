package com.example.expensemanager.allactivity.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensemanager.allactivity.adapter.TransactionAdapter
import com.example.expensemanager.allactivity.helper.MyDataHelper
import com.example.expensemanager.allactivity.model.IncomeExpenceModel
import com.example.expensemanager.databinding.ActivityTransactionBinding
import com.example.expensemanager.databinding.DilogModeBinding

class TransactionActivity : AppCompatActivity() {
    lateinit var adapter: TransactionAdapter
    lateinit var dh: MyDataHelper

    var incomeExpenselist = ArrayList<IncomeExpenceModel>()

    lateinit var binding: ActivityTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dh = MyDataHelper(this)

        intView()
    }

    private fun intView() {

        incomeExpenselist = dh.displayIncomeExp()


        adapter = TransactionAdapter(incomeExpenselist, {
            var titale = "Update Details"
            var icon = "updateButton"
            val trans = Intent(this, AddIncomeActivity::class.java)
            Log.e("TAG", "intView: " + it.id)
            trans.putExtra("amount", it.amount)
            trans.putExtra("note", it.note)
            trans.putExtra("id", it.id)
            trans.putExtra("title", titale)
            trans.putExtra("iconKey", icon)
            trans.putExtra("UpdateRecord", true)
            startActivity(trans)

        },
            { dId ->


                val d = Dialog(this)
                val ddMode: DilogModeBinding = DilogModeBinding.inflate(layoutInflater)
                d.setContentView(ddMode.root)
                d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                d.window?.setLayout(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                ddMode.btnSetMode.setOnClickListener {

                    dh.deleteRecord(dId)
                    incomeExpenselist = dh.displayIncomeExp()
                    adapter.uapdateData(incomeExpenselist)
                    d.dismiss()
                }
                ddMode.btnCancelMode.setOnClickListener {
                    d.dismiss()
                }
                d.show()
            })
        var layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rcvTransaction.layoutManager = layoutManager
        binding.rcvTransaction.adapter = adapter

        incomeExpenselist = dh.displayIncomeExp()
        adapter.uapdateData(incomeExpenselist)



        binding.imgDonetransaction.setOnClickListener {
            val transactionI = Intent(this@TransactionActivity, ExpenseMANAgerActivity1::class.java)
            startActivity(transactionI)
        }
        binding.imgBackTransaction.setOnClickListener {
            val transactionI = Intent(this@TransactionActivity, ExpenseMANAgerActivity1::class.java)
            startActivity(transactionI)
        }
    }
}