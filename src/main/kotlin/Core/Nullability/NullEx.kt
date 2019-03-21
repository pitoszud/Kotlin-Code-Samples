package Core.Nullability

// check null value in the method
fun checkLength1(str : String?): Int = str?.length ?: 0


// more verbose version
fun checkLength2(str : String?): Int = if (str != null) str.length else 0
fun checkLength2B(str : String?): Int = if (str == null) 0 else str.length

// using extension function
//fun checkLength4(str : String?): Int = if (str.isNullOrBlank()) 0 else str.length // only safe(.?) or non-null allowed
fun checkLength4B(str : String): Int = if (str.isNullOrBlank()) 0 else str.length






// checking at variable for null object
fun checkLength3(str : String): Int = str.length

fun main(args: Array<String>) {
    val str: String? = "abc"

    val v0: Int = checkLength2(str)
    val n0: Int = checkLength2(null)

    val v1: Int = checkLength1(str)
    val n1: Int = checkLength1(null)

    //var v4: Int = checkLength4(str)
    //var n4: Int = checkLength4(null)

    //var v4b: Int = checkLength4B(str) // required String, but found String?
    //var n4b: Int = checkLength4B(null) // null cannot be a value of non-null type String



    //val v2 : Int = checkLength3(str) // DOES NOT COMPILE - type mismatch
    str?.let { checkLength3(it) }
    val n2: Int = checkLength3(str!!) // will throw an exception
    //val k2: Int = checkLength3(null) // Null cannot be a value of a non-null type
    val w2: Int = checkLength3(str)

    val strNull = null
    strNull?.let { checkLength3(it) }


    println(v0)
    println(n0)
    println(v1)
    println(n1)

    println(n2)
    println(w2)

    println(strNull)
}