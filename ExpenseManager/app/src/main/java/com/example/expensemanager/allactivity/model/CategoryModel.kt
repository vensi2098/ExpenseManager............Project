package com.example.expensemanager.allactivity.model

data class CategoryModel(val name:String)

data class  IncomeExpenceModel(
    val id :Int,
    val type: String,
    val amount: String,
    val category:String,
    val date:String,
    val mode:String,
    val note:String)


