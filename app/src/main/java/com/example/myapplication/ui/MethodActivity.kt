package com.example.myapplication.ui

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.myapplication.R
import com.example.myapplication.controler.*
import kotlinx.android.synthetic.main.activity_method.*
import kotlinx.android.synthetic.main.function_text.view.*

class MethodActivity : AppCompatActivity() {
    lateinit var table:TableMaker
    lateinit var x:ArrayList<String>
    lateinit var y:ArrayList<String>
    var isEqual:Boolean = true

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_method)

        val bundle = intent.extras!!
        x = bundle.getStringArrayList("arrayOfX")!!
        y = bundle.getStringArrayList("arrayOfY")!!

        table = TableMaker(this,x,y)

        showTable.setOnClickListener {
            val intent = Intent(this, TableActivity::class.java)
            intent.putExtra("arrayOfX",x)
            intent.putExtra("arrayOfY",y)
            intent.putExtra("isEqual",isEqual)
            startActivity(intent)
        }

        newForward.setOnClickListener {
            reset()
            newForward.backgroundTintList = getColorStateList(R.color.black)
            newForward.setTextColor(Color.WHITE)

            methodSolve.removeAllViews()

            if (xValue.text.isNotEmpty()){
                newtonForward(this,this,table)
                //newForwardMethod()
            }else{
                val view = layoutInflater.inflate(R.layout.function_text,null)
                view.tv_functionText.text = "enter X please"
                methodSolve.addView(view)
            }

        }

        newBack.setOnClickListener {
            reset()
            newBack.backgroundTintList = getColorStateList(R.color.black)
            newBack.setTextColor(Color.WHITE)

            methodSolve.removeAllViews()


            if (xValue.text.isNotEmpty()){
                NewtonBack(this,this,table)
            }else{
                val view = layoutInflater.inflate(R.layout.function_text,null)
                view.tv_functionText.text = "enter X please"
                methodSolve.addView(view)
            }
        }

        lagrange_method_equal.setOnClickListener {
            reset()
            lagrange_method_equal.backgroundTintList = getColorStateList(R.color.black)
            lagrange_method_equal.setTextColor(Color.WHITE)
            methodSolve.removeAllViews()

            if (xValue.text.isNotEmpty()){
                lagrange_equal(this,this,table)
            }else{
                val view = layoutInflater.inflate(R.layout.function_text,null)
                view.tv_functionText.text = "enter X please"
                methodSolve.addView(view)
            }

        }

        divided_method_equal.setOnClickListener {
            reset()
            isEqual= false
            divided_method_equal.backgroundTintList = getColorStateList(R.color.black)
            divided_method_equal.setTextColor(Color.WHITE)
            methodSolve.removeAllViews()

            if (xValue.text.isNotEmpty()){
                divided_equal(this,this,table)
            }else{
                val view = layoutInflater.inflate(R.layout.function_text,null)
                view.tv_functionText.text = "enter X please"
                methodSolve.addView(view)
            }

        }
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun reset(){
        lagrange_method_equal.backgroundTintList = getColorStateList(R.color.white)
        lagrange_method_equal.setTextColor(Color.BLACK)
        divided_method_equal.backgroundTintList = getColorStateList(R.color.white)
        divided_method_equal.setTextColor(Color.BLACK)
        newForward.backgroundTintList = getColorStateList(R.color.white)
        newForward.setTextColor(Color.BLACK)
        newBack.backgroundTintList = getColorStateList(R.color.white)
        newBack.setTextColor(Color.BLACK)
        isEqual= true
    }


}
