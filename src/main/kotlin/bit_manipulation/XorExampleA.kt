package bit_manipulation

/*
* XOR is used to check if two numbers are the same.
* A 	B 	A XOR B
* 
* 0 	0 	0
* 0 	1 	1
* 1 	0 	1
* 1 	1 	0
* */
fun main() {
    val l = arrayOf(1,2,3,4,3,2,1)
    val r = calc(l)

    println(r)
}

fun calc(array: Array<Int>): Int {
    var v = 0
    for (num in array) {
        println("$v (${v.to32bitString()}) xor $num (${num.to32bitString()})")

        v = v xor num // ^ is XOR operator in Java

        println("= $v (${v.to32bitString()})")
    }
    return v
}