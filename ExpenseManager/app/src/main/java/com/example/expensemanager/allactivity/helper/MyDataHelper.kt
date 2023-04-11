package com.example.expensemanager.allactivity.helper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.expensemanager.allactivity.model.CategoryModel
import com.example.expensemanager.allactivity.model.IncomeExpenceModel

class MyDataHelper(context: Context) : SQLiteOpenHelper(context, "categoryDB", null, 1) {

    var datalist=ArrayList<CategoryModel>()
    var incomeExpenceList=ArrayList<IncomeExpenceModel>()
    override fun onCreate(db: SQLiteDatabase?) {
        var Table1 = "create table categoryTB(name text)"
        db?.execSQL(Table1)
        val Table2="create table IncomeAndExpenseTB(id_no integer primary key Autoincrement,date text,amount text,category_Name text,mode text,type integer,note text)"
        db?.execSQL(Table2)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    fun settingData(name: String) {
        val db = writableDatabase
        var c = ContentValues()
        c.put("name", name)
        db.insert("categoryTB",null,c)
    }
    fun dataDisplay(): ArrayList<CategoryModel> {
        datalist.clear()
        val db1 = readableDatabase
        val sql = "select * from categoryTB "
        var cursor = db1.rawQuery(sql, null)
        if (cursor.count > 0)
        {
           cursor.moveToFirst()
            do {
                val name=cursor.getString(0)
                var model=CategoryModel(name)
                datalist.add(model)

                Log.e("TAG", "dataDisplay: "+"name" )
            }
            while (cursor.moveToNext())
        }
        else{
            Log.e("TAG", "dataDisplay: "+"No data found" )
    }
        return datalist
    }

    fun incomeExpenseRecord(
        date1: String,
        amount: String,
        selectCategory: String,
        selectedMode: String,
        type: Int,
        note: String
    ) {
        var db1=writableDatabase
        var c1=ContentValues()

        c1.put("date",date1)
        c1.put("amount",amount)
        c1.put("category_Name",selectCategory)
        c1.put("mode",selectedMode)
        c1.put("type",type)
        c1.put("note",note)
        db1.insert("IncomeAndExpenseTB",null,c1)

    }
fun displayIncomeExp():ArrayList<IncomeExpenceModel>
{
    incomeExpenceList.clear()
    var db1=readableDatabase
    val sql="select * from IncomeAndExpenseTB"
    var cursor=db1.rawQuery(sql,null)
    if(cursor.count>0)
    {
        cursor.moveToFirst()
        do
        {
            var id=cursor.getInt(0)
            val date=cursor.getString(1)
            val amount=cursor.getString(2)
            val category=cursor.getString(3)
            val mode=cursor.getString(4)
            val type=cursor.getString(5)
            val note=cursor.getString(6)
            var incomeExpe=IncomeExpenceModel(id,date,amount,category,mode,type,note)
            this.incomeExpenceList.add(incomeExpe)
        }
            while (cursor.moveToNext())
    }
    else
    {

    }
    return incomeExpenceList
}

    fun UpdateRecord(amount: String, note: String, idNnumber: Int)
    {
        val update=writableDatabase
        val updateSql="update IncomeAndExpenseTB set amount='$amount',note= '$note' where  id_no='$idNnumber'    "
        update.execSQL(updateSql)
    }
fun deleteRecord(id:Int)
{
    var delete=writableDatabase
    var deleteSql="delete from IncomeAndExpenseTB where id_no='$id' "
    delete.execSQL(deleteSql)
}

}