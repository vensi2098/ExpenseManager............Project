package com.example.expensemanager.allactivity.model

data class CategoryModel(val name:String)

data class  IncomeExpenceModel(
    val id :Int,
    val date:String,
    val amount: String,
    val category:String,
    val mode:String,
    val type: String,
    val note:String)


