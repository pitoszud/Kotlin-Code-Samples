package Core.ExtensionsFunct

infix fun Int.addTo(i: Int) = this + i

fun main(args: Array<String>) {
    val iA = 1 addTo 2
    val iB = 1.addTo(2)

    println("$iA, $iB")

}