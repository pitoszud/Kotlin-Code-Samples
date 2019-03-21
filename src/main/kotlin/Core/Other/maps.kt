package Core.Other

/**
 * Created by upatryk on 11/10/2017.
 */
fun main(args: Array<String>) {
    val accMap = mapOf(5762 to "accA", 1358 to "accB", 3579 to "accC")

    val(accNum, name) = 4234 to "accD"
    println("accNumber: " + accNum)
    println("accName: " + name)

    infix fun Any.to(other: Any) = Pair(this, other)
    val sm1 = 1.to("one")
    println(sm1)



}