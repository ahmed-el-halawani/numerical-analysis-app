package com.example.myapplication.controler

import android.content.Context
import android.view.LayoutInflater
import com.example.myapplication.R
import com.example.myapplication.ui.MethodActivity
import kotlinx.android.synthetic.main.activity_method.*
import kotlinx.android.synthetic.main.activity_method.view.*
import kotlinx.android.synthetic.main.function_text.view.*

class newtonForward(context: Context,ActivityView:MethodActivity,val table:TableMaker) {

    init{

        if (table.isEqualInterval()){
            val x = ActivityView.xValue.text.toString()
            val data = table.equalTableData
            val h = data[0][1]-data[0][0]
            val p = (x.trim().toDouble() - data[0][0])/h
            var YxEquation = ""
            var YxSolve = ""
            var YxSolveSimplfy = ""
            var finalSolve =0.0

            for (i in 0 until data[0].size){
                if (i == 0){
                    YxEquation += "Y0"
                    YxSolve += "${data[i+1][0]}"
                    YxSolveSimplfy += "${data[i+1][0]}"
                    finalSolve += data[i+1][0]
                }else if (i == 1){
                    YxEquation += " + Pdy"
                    YxSolve += " + $p x ${data[i+1][0]}"
                    YxSolveSimplfy += " + ${p*data[i+1][0]}"
                    finalSolve += p * data[i+1][0]
                }else{
                    var top = ""
                    var topSolve = ""
                    var topFinalSolve = 1.0
                    for (j in 0 until i){
                        if (j==0){
                            top += "P"
                            topSolve += "$p"
                            topFinalSolve *= p
                        }else{
                            top += "(P - $j)"
                            topSolve += "($p - $j)"
                            topFinalSolve *= (p-j)
                        }
                    }
                    var factorial = 1
                    for(j in 1..i){
                        factorial *= j
                    }

                    YxEquation+= " +  ($top/$i!)*d${i}y "
                    YxSolve += " +  ($topSolve/$i!)x${data[i+1][0]}"
                    YxSolveSimplfy += " +  ($topFinalSolve/$factorial)x${data[i+1][0]}"
                    finalSolve += (topFinalSolve/factorial)*data[i+1][0]
                }

            }
            val view = LayoutInflater.from(context).inflate(R.layout.function_text,null)
            view.tv_functionText.text =
                """
                Y($x) = $finalSolve
                
                Steps:-
                h = $h
                p = X - X0/h = ($x - ${data[0][0]})/$h = $p
                
                Y(x) = $YxEquation
                
                Y($x) = $YxSolve
                
                Y($x) = $YxSolveSimplfy
                
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