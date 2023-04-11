package com.example.expensemanager.allactivity.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.expensemanager.databinding.ActivityExpenseManager1Binding

class ExpenseMANAgerActivity1 : AppCompatActivity() {
    lateinit var binding: ActivityExpenseManager1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityExpenseManager1Binding.inflate(layoutInflater)
        setContentView(binding.root)


        intents()
    }

    private fun intents() {
        val title_income="Add Income"
        binding.cdIncome.setOnClickListener {
            val i=Intent(this@ExpenseMANAgerActivity1, AddIncomeActivity::class.java)
            i.putExtra("page","i")
            i.putExtra("title",title_income)
            startActivity(i)
        }
        val title_expense="Add Expense"
        binding.cdExpenses.setOnClickListener {
            val i=Intent(this@ExpenseMANAgerActivity1, AddIncomeActivity::class.java)
            i.putExtra("page","i")
            i.putExtra("title",title_expense)
            startActivity(i)
        }
        binding.cdCategory.setOnClickListener {
            val i=Intent(this@ExpenseMANAgerActivity1, CategoryActivity::class.java)
            startActivity(i)
        }
        binding.cdTransactions.setOnClickListener {
            val i=Intent(this,TransactionActivity::class.java)
            startActivity(i)
        }
    }

}