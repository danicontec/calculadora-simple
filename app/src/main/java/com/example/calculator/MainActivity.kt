package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private  var operator_inserted: Boolean = false
    private  var dot_inserted: Boolean = false
    private lateinit var  currs:String
    private lateinit var  result:String
    private lateinit var inputTv: EditText
    private lateinit var outputTv: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inputTv = findViewById(R.id.inputValue)
        outputTv = findViewById(R.id.outputValue)
        currs = ""
        result = ""
    }

    fun inputShow(){
        inputTv.setText(currs)
    }

    fun outputShow(){
        outputTv.setText(result)
    }

    fun numberOperator(v: View) {
        var num = v as Button

        when(num.id){
            R.id.zero -> {
               currs+="0"
            }

            R.id.one -> {
                currs+="1"
            }

            R.id.two -> {
                currs+="2"
            }

            R.id.three -> {
                currs+="3"
            }

            R.id.four -> {
                currs+="4"
            }

            R.id.five -> {
                currs+="5"
            }

            R.id.six -> {
                currs+="6"
            }

            R.id.seven -> {
                currs+="7"
            }

            R.id.eight -> {
                currs+="8"
            }

            R.id.nine -> {
                currs+="9"
            }
        }

        if(currs.isNotEmpty() && currs.substring(0,1).equals("0") && currs.length == 1){
            delete(v)
        }
        inputShow()
    }

    fun delete(v: View){
        if(currs.isNotEmpty()){
            if (currs.substring(currs.length-1, currs.length).equals(".")){
               dot_inserted = false
            }
            if(currs.substring(currs.length -1, currs.length).equals(" ")){
                currs = currs.substring(0 , currs.length -3)
                operator_inserted = false
            }
            else {
                currs = currs.substring(0, currs.length -1)
            }
        }
            inputShow()
    }

    fun calculatorOperator(view: View) {
        var calculatBtn = view as Button
        dot_inserted = false
        if (currs.isNotEmpty()){

            if (currs.substring(
                    currs.length-1,currs.length
                ).equals(".")){
                delete(view)
            }
            if (!operator_inserted){

                when(calculatBtn.id){
                    R.id.plus ->{
                        currs += " + "
                    }
                    R.id.minus ->{
                        currs += " - "
                    }
                    R.id.multiply ->{
                        currs += " * "
                    }
                    R.id.divide ->{
                        currs += " / "
                    }
                }
                operator_inserted = true
            }
        }
        inputShow()
    }

    fun dotOperator(view: View) {
        if (currs.isEmpty()){
            currs = "0."
            dot_inserted = true

        }

        if (!dot_inserted){
            currs += "."
            dot_inserted = true
        }
        inputShow()
    }
    fun equalOperator(view: View) {

        val tokens = currs.split(" ")
        val cont = 0
        if(operator_inserted && !currs.substring(currs.length -1, currs.length).equals(" ")){
                val firstNum = java.lang.Double.parseDouble(tokens[0])
                val secondNum = java.lang.Double.parseDouble(tokens[2])
                when (tokens[1].get(0)) {
                    '+' -> result = (firstNum + secondNum).toString()
                    '-' -> result = (firstNum - secondNum).toString()
                    '*' -> result = (firstNum * secondNum).toString()
                    '/' -> result = (firstNum / secondNum).toString()
                }
            currs = ""
            operator_inserted = false
            dot_inserted = false
        }

        inputShow()
        outputShow()
    }

    fun clearAll(view: View) {
        result = "0"
        currs = ""
        dot_inserted = false
        operator_inserted = false
        inputShow()
        outputShow()
    }
}