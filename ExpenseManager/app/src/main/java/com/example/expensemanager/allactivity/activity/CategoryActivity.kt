package com.example.expensemanager.allactivity.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.expensemanager.allactivity.helper.MyDataHelper
import com.example.expensemanager.allactivity.model.CategoryModel
import com.example.expensemanager.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {

    lateinit var binding:ActivityCategoryBinding

    lateinit var cHelper: MyDataHelper
    var list=ArrayList<CategoryModel>()
//    var adapter=CategoryAdapter(list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        adapter= CategoryAdapter(list)
//        binding.rcvCategory.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
//        binding.rcvCategory.adapter=adapter
        intView()
    }

    private fun intView() {
        cHelper=MyDataHelper(this)


        binding.btnAdd.setOnClickListener {
            val name = binding.edtCategoryName.text.toString()
            cHelper.settingData(name)
            list=cHelper.dataDisplay()
//            adapter.updateData(list)


        }
    }
}