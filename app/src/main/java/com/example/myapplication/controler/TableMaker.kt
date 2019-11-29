package com.example.myapplication.controler

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.example.myapplication.R
import kotlinx.android.synthetic.main.table_calculator.view.*
import kotlinx.android.synthetic.main.table_row.view.*
import kotlinx.android.synthetic.main.text.view.*

class TableMaker(private var context: Context, x: ArrayList<String>, y: ArrayList<String>) {
    private val arrayOfX = ArrayList<Double>()
    private val arrayOfY = ArrayList<Double>()

    val unEqualTableData get() = this.unEqualTableData()
    val equalTableData get() = this.equalTableData()

    init {
        for(i in 0.until(x.size)){
            arrayOfX.add(x[i].toDouble())
            arrayOfY.add(y[i].toDouble())
        }
    }

    fun isEqualInterval():Boolean{

        var old = 0.0
        for (i in 1.until(arrayOfX.size)){
            val interval = arrayOfX[i-1]-arrayOfX[i]
            if (old!=0.0){
                if (old != interval){
                    return false
                }
            }else{
                old=interval
            }
        }
        return true
    }

    private fun equalTableData():ArrayList<ArrayList<Double>>{
        val tableData = ArrayList<ArrayList<Double>>()
        tableData.add(arrayOfX)
        tableData.add(arrayOfY)

        for (i in 1.until(arrayOfY.size)){
            val column = ArrayList<Double>()
            for (j in 1.until(tableData[i].size)){
                val delta =tableData[i][j] - tableData[i][j-1]
                column.add(delta)
            }
            tableData.add(column)
        }
        return tableData
    }

    private fun unEqualTableData():ArrayList<ArrayList<Double>> {
        val tableData = ArrayList<ArrayList<Double>>()
        tableData.add(arrayOfX)
        tableData.add(arrayOfY)

        for (i in 1.until(arrayOfY.size)) {
            val column = ArrayList<Double>()
            for (j in 1.until(tableData[i].size)) {
                val delta =
                    (tableData[i][j] - tableData[i][j - 1]) / (arrayOfX[j + i - 1] - arrayOfX[j - 1])
                column.add(delta)
            }
            tableData.add(column)
        }
        return tableData
    }

    @SuppressLint("InflateParams", "SetTextI18n")
    fun getTable(isEqual:Boolean):View{
        val row:ArrayList<ArrayList<Double>> =
            if(isEqual){
                equalTableData()
            }else{
                unEqualTableData()
            }

        val tableLayout = LayoutInflater.from(context).inflate(R.layout.table_calculator,null)
        val headerRowLayout = LayoutInflater.from(context).inflate(R.layout.table_row,null)
        for (i in 0.until(arrayOfY.size+1)){
            when (i) {
                0 -> {
                    val text = LayoutInflater.from(context).inflate(R.layout.text,null)
                    text.tv_num.text = "x"
                    headerRowLayout.table_row.addView(text)
                }
                1 -> {
                    val text = LayoutInflater.from(context).inflate(R.layout.text,null)
                    text.tv_num.text = "y"
                    headerRowLayout.table_row.addView(text)
                }
                else -> {
                    val text = LayoutInflater.from(context).inflate(R.layout.text,null)
                    text.tv_num.text = "D`${i-1}(y)"
                    headerRowLayout.table_row.addView(text)
                }
            }
        }
        tableLayout.table.addView(headerRowLayout)

        //Row
        for(i in 0.until(arrayOfY.size)){
            //column
            val tableRowLayout = LayoutInflater.from(context).inflate(R.layout.table_row,null)
            for(j in 0.until(arrayOfY.size-i+1)){
                val text = LayoutInflater.from(context).inflate(R.layout.text,null)
                text.tv_num.text = row[j][i].toString()
                tableRowLayout.table_row.addView(text)
            }
            tableLayout.table.addView(tableRowLayout)

        }
        return tableLayout
    }


}