package com.example.expensemanager.allactivity.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensemanager.allactivity.adapter.CategoryAdapter
import com.example.expensemanager.allactivity.adapter.ModeAdapter
import com.example.expensemanager.allactivity.helper.MyDataHelper
import com.example.expensemanager.allactivity.model.CategoryModel
import com.example.expensemanager.databinding.ActivityAddIncomeBinding
import com.example.expensemanager.databinding.DilogBinding
import com.example.expensemanager.databinding.DilogModeBinding
import java.text.SimpleDateFormat
import java.util.*

class AddIncomeActivity : AppCompatActivity() {
    var list = ArrayList<CategoryModel>()
    lateinit var binding: ActivityAddIncomeBinding
    var mode = ArrayList<String>()
    var selectCategory = ""
    var selectedMode = ""
    var date1 = ""
    var type = -1
    var idNnumber = 0
    lateinit var dbhelper: MyDataHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddIncomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var name: String? = intent.getStringExtra("title")
        binding.txttitle.text = name
        dbhelper = MyDataHelper(this)

        dilog()
    }

    private fun dilog() {

        if (intent != null && intent.hasExtra("UpdateRecord")) {


            var newAmount: String? = intent.getStringExtra("amount").toString()
            var newNote: String? = intent.getStringExtra("note")
            var icon: String? = intent.getStringExtra("iconKey")
            idNnumber = intent.getIntExtra("id",0)

            binding.edtAmout.setText(newAmount)
            binding.edtNote.setText(newNote)
            binding.btnDone.setText(icon)

        }

        binding.btnDone.setOnClickListener {

            var amount = binding.edtAmout.text.toString()
            var note = binding.edtNote.text.toString()

            if (amount.isEmpty()) {
                Toast.makeText(this, "please enter amout", Toast.LENGTH_SHORT).show()
            } else if (amount.length <= 1 || amount.length >= 10) {
                Toast.makeText(this, "please enter a amount", Toast.LENGTH_SHORT).show()
            } else if (note.isEmpty()) {
                Toast.makeText(this, "please enter a note", Toast.LENGTH_SHORT).show()
            } else if (note.length <= 1 || note.length >= 10) {
                Toast.makeText(this, "please enter a note", Toast.LENGTH_SHORT).show()
            } else if (note.isEmpty()) {
                Toast.makeText(this, "please entear a note", Toast.LENGTH_SHORT).show()
            } else if (note.length <= 1 || note.length >= 20) {
                Toast.makeText(this, "pl,ease entear a note", Toast.LENGTH_SHORT).show()
            } else {

                // GETTING RADIO BUTTON CHCKED
                if (binding.rdgType.checkedRadioButtonId == -1) {

                } else {
                    val selectId: Int = binding.rdgType.checkedRadioButtonId
                    var selectedRadioButton: RadioButton = findViewById(selectId)
                    var text = selectedRadioButton.text.toString()
                    if (text.equals("Income")) {
                        type = 1
                    } else {
                        type = 2
                    }
                }

                if (binding.btnDone.text.toString().equals("updateButton")) {
                    dbhelper.UpdateRecord(amount, note,idNnumber)
                } else {
                    dbhelper.incomeExpenseRecord(
                        date1,
                        amount,
                        selectCategory,
                        selectedMode,
                        type,
                        note
                    )

                }
            }

            val trans = Intent(this@AddIncomeActivity, TransactionActivity::class.java)
            startActivity(trans)


        }

        val SimpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        val date: String = SimpleDateFormat.format(Date())
        binding.txtDate.text = date
        date1 = date


        val simpletimeformat = SimpleDateFormat("HH:mm")
        val time: String = simpletimeformat.format(Date())
        binding.txtTime.text = time
        var timeValue = time

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, ExpenseMANAgerActivity1::class.java)
            startActivity(intent)
            finish()
        }



        binding.cdClickIncome.setOnClickListener {
            val dialog = Dialog(this)
            val dilogBinding: DilogBinding = DilogBinding.inflate(layoutInflater)
            dialog.setContentView(dilogBinding.root)
            list = dbhelper.dataDisplay()
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            var adapter = CategoryAdapter(list, { name ->
                selectCategory = name
                Log.e("TAG", "dilog: " + selectCategory)
            })


            dilogBinding.rcvSelectCategory.layoutManager =
                                                           LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            dilogBinding.rcvSelectCategory.adapter = adapter

            dilogBinding.btnCancel.setOnClickListener {
                Toast.makeText(this, "are you sure want cancle", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            dilogBinding.btnSet.setOnClickListener {
                Toast.makeText(this, "your category is set", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            dialog.show()
        }

        binding.lytMode.setOnClickListener {
            val d = Dialog(this)
            val ddMode: DilogModeBinding = DilogModeBinding.inflate(layoutInflater)
            d.setContentView(ddMode.root)
            d.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            d.window?.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            ddMode.btnSetMode.setOnClickListener {
                d.dismiss()
            }
            ddMode.btnCancelMode.setOnClickListener {
                d.dismiss()
            }
            d.show()
            var modeAdapter = ModeAdapter(mode, { mode ->
                selectedMode = mode
                Log.e("TAG", "dilog: " + selectedMode)
            })
            var modeManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            ddMode.rcvSelectPaymentMode.layoutManager = modeManager
            ddMode.rcvSelectPaymentMode.adapter = modeAdapter
        }
        mode.add("CASH")
        mode.add("CREDIT CARD")
        mode.add("UPI")
        mode.add("NET BANKING")
        mode.add("DEBIT CARD")


    }


}
