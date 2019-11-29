package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.controler.TableMaker
import kotlinx.android.synthetic.main.activity_main.*

class TableActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = intent.extras!!
        val x = bundle.getStringArrayList("arrayOfX")!!
        val y = bundle.getStringArrayList("arrayOfY")!!
        val isEqual = bundle.getBoolean("isEqual")

        val tableAdaptor = TableMaker(this, x, y)

        if (isEqual){
            tableLayoutPlace.addView(tableAdaptor.getTable(true))
        }else{
            tableLayoutPlace.addView(tableAdaptor.getTable(false))
        }



    }

}

