package com.example.myapplication.controler

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import com.example.myapplication.R
import com.example.myapplication.ui.MethodActivity
import kotlinx.android.synthetic.main.activity_method.*
import kotlinx.android.synthetic.main.activity_method.view.*
import kotlinx.android.synthetic.main.function_text.view.*

class NewtonBack(val context: Context, private val ActivityView: MethodActivity, val table:TableMaker) {

     init {
         getNewtonBack()
    }


    @SuppressLint("SetTextI18n", "InflateParams")
    fun getNewtonBack(){
        if (table.isEqualInterval()){
            val x = ActivityView.xValue.text.toString()
            val data = table.equalTableData
            val h = data[0][1]-data[0][0]
            val p = (x.trim().toDouble() - data[0][data[0].lastIndex])/h
            var equation = ""
            var solve = ""
            var solveSimplify = ""
            var finalSolve =0.0

            for (i in 0 until data[0].size){
                when (i) {
                    0 -> {
                        equation += "Yn"
                        solve += "${data[i+1][data[i+1].lastIndex]}"
                        solveSimplify += "${data[i+1][data[i+1].lastIndex]}"
                        finalSolve += data[i+1][data[i+1].lastIndex]
                    }
                    1 -> {
                        equation += " + Pdy"
                        solve += " + $p x ${data[i+1][data[i+1].lastIndex]}"
                        solveSimplify += " + ${p*data[i+1][data[i+1].lastIndex]}"
                        finalSolve += p * data[i+1][data[i+1].lastIndex]
                    }
                    else -> {
                        var top = ""
                        var topSolve = ""
                        var topFinalSolve = 1.0
                        for (j in 0 until i){
                            if (j==0){
                                top += "P"
                                topSolve += "$p"
                                topFinalSolve *= p
                            }else{
                                top += "(P + $j)"
                                topSolve += "($p + $j)"
                                topFinalSolve *= (p + j)
                            }
                        }
                        var factorial = 1
                        for(j in 1..i){
                            factorial *= j
                        }

                        equation+= " +  ($top/$i!)*d${i}y "
                        solve += " +  ($topSolve/$i!)x${data[i+1][data[i+1].lastIndex]}"
                        solveSimplify += " +  ($topFinalSolve/$factorial)x${data[i+1][data[i+1].lastIndex]}"
                        finalSolve += (topFinalSolve/factorial)*data[i+1][data[i+1].lastIndex]
                    }
                }

            }
            val view = LayoutInflater.from(context).inflate(R.layout.function_text,null)
            view.tv_functionText.text =
                """
                Y($x) = $finalSolve
                
                Steps:-
                h = $h
                p = X - Xn/h = ($x - ${data[0][data[0].lastIndex]})/$h = $p
                
                Y(x) = $equation
                
                Y($x) = $solve
                
                Y($x) = $solveSimplify
                
                Y($x) = $finalSolve
                """.trimIndent()

            ActivityView.methodSolve.addView(view)
            ActivityView.lengthOfMethod.tv_methodLength.text = "method length = ${view.tv_functionText.text.toString().replace(" ","").length}"





        }else{
            val view = LayoutInflater.from(context).inflate(R.layout.function_text,null)
            view.tv_functionText.text = "Here difference of X is not same, So solution using Newton's forward differentiation method is not possible.\n" +
                    "Solve this using Langrange's Interpolating Mehtod or Divided Difference"
            ActivityView.methodSolve.addView(view)
        }
    }
}