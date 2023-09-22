package com.example.myapplication2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), OnClickListener {
    private var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.text_view)
        subskribeButtons()

    }

    override fun onClick(viev: View?) {
        when (viev?.id) {
            R.id.button_c -> textView?.text = ""
            R.id.button_b -> backScapeTextView()
            R.id.button_result -> showResult()
            else -> if (viev is Button) {
                "${textView?.text.toString()}${viev.text}".also { textView?.text = it }
            }
        }
    }

    private fun showResult() {
        val expression = MathParser.separateExpression(textView?.text.toString())
        if (expression == null){
            textView?.text = getString(R.string.error)
            return
        }
        val result = MathParser.calculate(expression)
        if (result == null){
            textView?.text = getString(R.string.error)
            return
        }
        textView?.text = result.toString()
    }

    private fun backScapeTextView(){
        if (textView?.text?.length!! > 1)
            textView?.text = textView?.text?.subSequence(0, textView?.text?.length!! - 1)
        else if (textView?.text?.length!! == 1)
            textView?.text = ""
    }

    private fun subskribeButtons() {
        val buttons = mutableListOf<Button>()
        buttons.run {
            add(findViewById(R.id.button_0))
            add(findViewById(R.id.button_1))
            add(findViewById(R.id.button_2))
            add(findViewById(R.id.button_3))
            add(findViewById(R.id.button_4))
            add(findViewById(R.id.button_5))
            add(findViewById(R.id.button_6))
            add(findViewById(R.id.button_7))
            add(findViewById(R.id.button_8))
            add(findViewById(R.id.button_9))
            add(findViewById(R.id.button_point))
            add(findViewById(R.id.button_plus))
            add(findViewById(R.id.button_x))
            add(findViewById(R.id.button_subtraction))
            add(findViewById(R.id.button_division))
            add(findViewById(R.id.button_result))
            add(findViewById(R.id.button_c))
            add(findViewById(R.id.button_b))
        }

        for (button in buttons)
            button.setOnClickListener(this)
    }
}