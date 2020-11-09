package Core.Other

fun interface MathOperation {
    fun operation(valueOne: Int, valueTwo: Int): Int
}

fun main() {
    val sum = MathOperation { valueOne, valueTwo -> valueOne + valueTwo }
    val multiply = MathOperation { valueOne, valueTwo -> valueOne * valueTwo }

    sum.operation(3, 9) //12
    multiply.operation(3, 9) //27

}

