package com.example.myapplication2

import java.util.LinkedList
import java.util.Queue

object MathParser {
    private val operations = listOf("+", "-", "x", "÷")

    fun separateExpression(expression: String): SeparatedExpression? {
        if (expression.isEmpty()) return null
        val values = LinkedList<Double>()
        val operation = LinkedList<Char>()
        var i = 0
        while (i < expression.length) {
            if (expression[i].toString() in operations)
                operation.add(expression[i])
            else {
                var valueStr = ""
                while (i < expression.length && !operations.contains(expression[i].toString()))
                //Пока не операция или не достигнем конца выражения
                {
                    valueStr += expression[i]
                    i++
                }
                val value = valueStr.toDoubleOrNull() ?: return null
                values.add(value)
                i--
            }
            i++
        }
        return SeparatedExpression(values, operation)
    }

    fun calculate(expression: SeparatedExpression): Double? {
        while (expression.operators.isNotEmpty() && expression.values.size > 1
        ) {
            val operator = expression.operators.remove() ?: return null
            val operand2 = expression.values.remove() ?: return null
            val operand1 = expression.values.remove() ?: return null
            when (operator.toString()) {
                "+" -> expression.values.add(operand1 + operand2)
                "-" -> expression.values.add(operand1 - operand2)
                "x" -> expression.values.add(operand1 * operand2)
                "÷" -> {
                    if (operand2 == 0.0) return null
                    expression.values.add(operand1 / operand2)
                }
            }
        }
        if (expression.operators.isNotEmpty() || expression.values.size > 1 ){
            return null
        }
        return expression.values.remove()?:return null
    }
}

data class SeparatedExpression(val values: Queue<Double>, val operators: Queue<Char>)