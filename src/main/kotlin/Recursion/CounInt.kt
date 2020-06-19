package Recursion

fun main() {
    println(numberOfDigits(3498787))
}

fun numberOfDigits(n: Int): Int{
    return if (n <= 9){
        println(n)
        1
    }else{
        println(n)
        1 + numberOfDigits(n/10)
    }
}

