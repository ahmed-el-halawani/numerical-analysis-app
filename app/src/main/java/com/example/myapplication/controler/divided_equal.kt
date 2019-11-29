package com.example.myapplication.controler

import android.content.Context
import android.view.LayoutInflater
import com.example.myapplication.R
import com.example.myapplication.ui.MethodActivity
import kotlinx.android.synthetic.main.activity_lagrange_divided__method.view.*
import kotlinx.android.synthetic.main.activity_method.*
import kotlinx.android.synthetic.main.function_text.view.*

class divided_equal(context: Context, ActivityView: MethodActivity, val table:TableMaker) {
    init {
        val x = ActivityView.xValue.text.toString().trim().toDouble()
        val data = table.unEqualTableData
        var YxEquation = ""
        var YxSolve = ""
        var YxSolveSimplfy = ""
        var finalSolve = 0.0


        for(i in 0 until data[0].size){
            var topfunc = ""
            var top = ""
            var topSolve = ""
            var topFinalSolve = 1.0
            if(i == 0){
                top += "y0"
                topSolve += "${data[1][0]}"
                topFinalSolve += 1
                YxEquation += "y0"
                YxSolve += "${data[1][0]}"
                YxSolveSimplfy += "${data[1][0]}"
                finalSolve += data[1][0]
            }else{
                for(j in 0 until i){
                    top += "(X - X$j)"
                    topSolve += "($x - ${data[0][j]})"
                    topFinalSolve *= (x - data[0][j])
                    topfunc +=",x${j+1}"
                }
                YxEquation += " + $top*f[x0$topfunc]"
                YxSolve += " + $topSolve*${data[i+1][0]}"
                YxSolveSimplfy += " + $topFinalSolve*${data[i+1][0]}"
                finalSolve += (topFinalSolve)*data[i+1][0]
            }
        }

        val view = LayoutInflater.from(context).inflate(R.layout.function_text,null)


        view.tv_functionText.text ="""
            Y($x) = $finalSolve
            
            Steps:-
            x = $x
            
            Y(x) = $YxEquation
            
            Y($x) = $YxSolve
            
            Y($x) = $YxSolveSimplfy
            
            Y($x) = $finalSolve
            
        """.trimIndent()

        ActivityView.methodSolve.addView(view)

        ActivityView.lengthOfMethod.tv_methodLength.text =
            "method length = ${view.tv_functionText.text.toString().replace(" ","").length}"
    }
}