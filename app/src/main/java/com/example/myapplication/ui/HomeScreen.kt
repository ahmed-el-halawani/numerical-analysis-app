package com.example.myapplication.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.controler.TableMaker
import kotlinx.android.synthetic.main.activity_home_screen.*
import kotlinx.android.synthetic.main.input_data.view.*

class HomeScreen : AppCompatActivity() {
    lateinit var arrayOfX:ArrayList<String>
    lateinit var arrayOfY:ArrayList<String>
    var xIndex = 0
    var yIndex = 0
    var removeRow = 0
    val rowofdata = ArrayList<View>()

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        this.setTitle("Enter Data")
        firstThreeRow()

        addRow.setOnClickListener {
            val inputRowData = layoutInflater.inflate(R.layout.input_data,null)
            inputRowData.tv_xdata.text = "x$xIndex="
            inputRowData.tv_ydata.text = "y$yIndex="
            rowofdata.add(inputRowData)
            xIndex++
            yIndex++
            removeRow++
            btn_removeRow.visibility = View.VISIBLE
            inputRow.addView(inputRowData)
            btn_calculate.isEnabled = true
        }

        btn_removeRow.setOnClickListener {
            if (removeRow !=0) {
                inputRow.removeView(rowofdata[removeRow - 1])
                rowofdata.removeAt(removeRow - 1)
                xIndex--
                yIndex--
                removeRow--
            }
            if (removeRow == 0){
                btn_removeRow.visibility = View.GONE
                btn_calculate.isEnabled = false

            }
        }

        btn_calculate.setOnClickListener {
            var isDone = true
            arrayOfX = ArrayList()
            arrayOfY = ArrayList()
            for (i in rowofdata){
                if(i.et_xdata.text.isNotEmpty() && i.et_ydata.text.isNotEmpty()) {
                    arrayOfX.add(i.et_xdata.text.toString().trim())
                    arrayOfY.add(i.et_ydata.text.toString().trim())
                }else{
                    isDone = false
                    break
                }
            }


            if (isDone){
                val isEqual = TableMaker(this,arrayOfX,arrayOfY).isEqualInterval()
                if(isEqual){
                    val intent = Intent(this, MethodActivity::class.java)
                    intent.putExtra("arrayOfX",arrayOfX)
                    intent.putExtra("arrayOfY",arrayOfY)
                    startActivity(intent)
                }else{
                    val intent = Intent(this, LagrangeDividedMethod::class.java)
                    intent.putExtra("arrayOfX",arrayOfX)
                    intent.putExtra("arrayOfY",arrayOfY)
                    startActivity(intent)
                }
            }
        }

        fastTest.setOnClickListener {
            val intent = Intent(this, MethodActivity::class.java)
            intent.putExtra("arrayOfX",testMode()[0])
            intent.putExtra("arrayOfY",testMode()[1])
            startActivity(intent)
        }


    }



    fun testMode():Array<ArrayList<String>>{
        val testArrayOfX = ArrayList<String>()
        val testArrayOfY = ArrayList<String>()

        for (i in 0..5){
            testArrayOfX.add(i.toString())
            var factorial = 1
            for(j in 1..i){
                factorial *= i
            }
            testArrayOfY.add(factorial.toString())
        }
        return arrayOf(testArrayOfX,testArrayOfY)
    }

    @SuppressLint("RestrictedApi")
    fun firstThreeRow(){
        for (i in 0..2){
            val inputRowData = layoutInflater.inflate(R.layout.input_data,null)
            inputRowData.tv_xdata.text = "x$xIndex="
            inputRowData.tv_ydata.text = "y$yIndex="
            rowofdata.add(inputRowData)
            xIndex++
            yIndex++
            removeRow++
            btn_removeRow.visibility = View.VISIBLE
            inputRow.addView(inputRowData)
            btn_calculate.isEnabled = true
        }
    }
}
