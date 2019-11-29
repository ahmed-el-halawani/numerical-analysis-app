package com.example.myapplication.ui

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.myapplication.R
import com.example.myapplication.controler.divided
import com.example.myapplication.controler.lagrange
import com.example.myapplication.controler.TableMaker
import kotlinx.android.synthetic.main.activity_lagrange_divided__method.*
import kotlinx.android.synthetic.main.function_text.view.*

class LagrangeDividedMethod : AppCompatActivity() {
    lateinit var table: TableMaker
    lateinit var x:ArrayList<String>
    lateinit var y:ArrayList<String>

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lagrange_divided__method)

        val bundle = intent.extras!!
        x = bundle.getStringArrayList("arrayOfX")!!
        y = bundle.getStringArrayList("arrayOfY")!!

        table = TableMaker(this,x,y)

        showTable_unequal.setOnClickListener {
            val intent = Intent(this, TableActivity::class.java)
            intent.putExtra("arrayOfX",x)
            intent.putExtra("arrayOfY",y)
            intent.putExtra("isEqual",false)
            startActivity(intent)
        }

        lagrange_method.setOnClickListener {
            lagrange_method.backgroundTintList = getColorStateList(R.color.black)
            lagrange_method.setTextColor(Color.WHITE)
            divided_method.backgroundTintList = getColorStateList(R.color.white)
            divided_method.setTextColor(Color.BLACK)
            methodSolve_unequal.removeAllViews()

            if (xValue_unequal.text.isNotEmpty()){
                lagrange(this,this,table)
                //newForwardMethod()
            }else{
                val view = layoutInflater.inflate(R.layout.function_text,null)
                view.tv_functionText.text = "enter X please"
                methodSolve_unequal.addView(view)
            }

        }

        divided_method.setOnClickListener {
            lagrange_method.backgroundTintList = getColorStateList(R.color.white)
            lagrange_method.setTextColor(Color.BLACK)
            divided_method.backgroundTintList = getColorStateList(R.color.black)
            divided_method.setTextColor(Color.WHITE)
            methodSolve_unequal.removeAllViews()

            if (xValue_unequal.text.isNotEmpty()){
                divided(this,this,table)
                //newForwardMethod()
            }else{
                val view = layoutInflater.inflate(R.layout.function_text,null)
                view.tv_functionText.text = "enter X please"
                methodSolve_unequal.addView(view)
            }

        }

    }
}
