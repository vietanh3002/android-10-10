package com.learn.happybirthday

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.DecimalFormat

class MainActivity :View.OnClickListener, AppCompatActivity() {

    lateinit var textReuslt: TextView

    //    private var state: Int = 0
    private var oper : Int = 0
    private var resultOne : Double = 0.0
    private var resultTwo : Double = 0.0
    private var str: String = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textReuslt = findViewById(R.id.text_result)
        findViewById<Button>(R.id.button_add_sub).setOnClickListener(this)
        findViewById<Button>(R.id.button_0).setOnClickListener(this)
        findViewById<Button>(R.id.button_dot).setOnClickListener(this)
        findViewById<Button>(R.id.button_equal).setOnClickListener(this)
        findViewById<Button>(R.id.button_1).setOnClickListener(this)
        findViewById<Button>(R.id.button_2).setOnClickListener(this)
        findViewById<Button>(R.id.button_3).setOnClickListener(this)
        findViewById<Button>(R.id.button_add).setOnClickListener(this)
        findViewById<Button>(R.id.button_4).setOnClickListener(this)
        findViewById<Button>(R.id.button_5).setOnClickListener(this)
        findViewById<Button>(R.id.button_6).setOnClickListener(this)
        findViewById<Button>(R.id.button_sub).setOnClickListener(this)
        findViewById<Button>(R.id.button_7).setOnClickListener(this)
        findViewById<Button>(R.id.button_8).setOnClickListener(this)
        findViewById<Button>(R.id.button_9).setOnClickListener(this)
        findViewById<Button>(R.id.button_mul).setOnClickListener(this)
        findViewById<Button>(R.id.button_ce).setOnClickListener(this)
        findViewById<Button>(R.id.button_c).setOnClickListener(this)
        findViewById<Button>(R.id.button_bs).setOnClickListener(this)
        findViewById<Button>(R.id.button_div).setOnClickListener(this)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClick(view: View?) {
        val id = view?.id;
        when(id){
            R.id.button_0 -> addDigit("0")
            R.id.button_1 -> addDigit("1")
            R.id.button_2 -> addDigit("2")
            R.id.button_3 -> addDigit("3")
            R.id.button_4 -> addDigit("4")
            R.id.button_5 -> addDigit("5")
            R.id.button_6 -> addDigit("6")
            R.id.button_7 -> addDigit("7")
            R.id.button_8 -> addDigit("8")
            R.id.button_9 -> addDigit("9")
            R.id.button_add -> {
                calculate()
                addDigit("+")
            }
            R.id.button_sub -> {
                calculate()
                addDigit("-")
            }
            R.id.button_mul -> {
                calculate()
                addDigit("*")
            }
            R.id.button_div -> {
                calculate()
                addDigit("/")
            }
            R.id.button_dot -> {
                calculate()
                addDigit(".")
            }
            R.id.button_equal -> {
                calculate()
                val decimalFormat = DecimalFormat("#.#####")
                oper = 0
                str = decimalFormat.format(resultOne)
                textReuslt.text = str
            }
            R.id.button_add_sub -> {}
            R.id.button_c -> {
                resultOne = 0.0
                resultTwo = 0.0
                oper = 0
                str = "0"
                textReuslt.text = str
            }
            R.id.button_ce -> {}
            R.id.button_bs -> {}
            else -> {}
        }
    }

    private  fun addDigit(c: String) {
        val num : Double = c.toDoubleOrNull() ?: -1.0;
        if(num == -1.0) {
            if(c == "+") {
                oper = 1
            }
            else if(c == "-") {
                oper = 2
            }
            else if(c == "*") {
                oper = 3
            }
            else if(c == "/") {
                oper = 4
            }
            else if(c == ".") {
                oper = 5
            }
        }else {
            if(oper == 0){
                resultOne = resultOne * 10.0 + num;
            }else{
                resultTwo = resultTwo * 10.0 + num;
            }
        }
        if(str == "0") {
            str = c;
        }else{
            str += c;
        }

        textReuslt.text = str;
    }

    private fun calculate(){
        when(oper){
            1 -> resultOne += resultTwo
            2 -> resultOne -= resultTwo
            3 -> resultOne *= resultTwo
            4 -> if(resultTwo!= 0.0) resultOne /= resultTwo
            5 -> {
                val numDigits = resultTwo.toInt().toString().length
                val divisor = Math.pow(10.0, numDigits.toDouble())
                resultOne += resultTwo / divisor
            }
        }
        resultTwo = 0.0
    }
}