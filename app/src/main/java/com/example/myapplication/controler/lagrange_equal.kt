package com.example.myapplication.controler

import android.content.Context
import android.view.LayoutInflater
import com.example.myapplication.R
import com.example.myapplication.ui.MethodActivity
import kotlinx.android.synthetic.main.activity_lagrange_divided__method.view.*
import kotlinx.android.synthetic.main.activity_method.*
import kotlinx.android.synthetic.main.function_text.view.*

class lagrange_equal(context: Context, ActivityView: MethodActivity, val table:TableMaker) {
    init {
        val x = ActivityView.xValue.text.toString().trim().toDouble()
        val data = table.equalTableData
        var YxEquation = ""
        var YxSolve = ""
        var YxSolveSimplfy = ""
        var finalSolve = 0.0

        for(i in 0 until data[0].size){
            var top = ""
            var bot = ""
            var topSolve = ""
            var botSolve = ""
            var topFinalSolve = 1.0
            var botFinalSolve = 1.0

            for(j in 0 until data[0].size){
                if (j != i){
                    top += "(X - X$j)"
                    bot += "(X$i - X$j)"
                    topSolve += "($x - ${data[0][j]})"
                    botSolve += "(${data[0][i]} - ${data[0][j]})"
                    topFinalSolve *= (x - data[0][j])
                    botFinalSolve *= (data[0][i] - data[0][j])

                }
            }

            var blus = ""
            if (i != 0){blus ="  +  "}
            YxEquation += "${blus}($top/$bot)*y$i"
            YxSolve += "${blus}($topSolve/$botSolve)*${data[1][i]}"
            YxSolveSimplfy += "${blus}($topFinalSolve/$botFinalSolve)*${data[1][i]}"
            finalSolve += (topFinalSolve/botFinalSolve)*data[1][i]
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