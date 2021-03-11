package bit_manipulation

fun main() {
    val l = arrayOf(1,2,3,4,3,2,1)
    val r = calc(l)

    println(r)
}

fun calc(array: Array<Int>): Int {
    var v = 0
    for (num in array) {
        println("$v xor")
        v = v xor num // ^ is XOR operator in Java
    }
    return v
}