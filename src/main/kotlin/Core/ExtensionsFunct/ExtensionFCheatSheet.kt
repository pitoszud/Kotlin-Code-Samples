package Core.ExtensionsFunct

/**
 * Created by upatryk on 11/10/2017.
 */
val String.firstChar1: Char
    get() = get(0) // extension property


val String.firstName: String
    get(){
        val i = indexOf(" ")
        return if (i < 0) this else substring(0, i)
    }

fun String.lastCharA(): Char =
        get(this.length - 1) // extension function


// Investigate this one
fun String.lastCharB(fn: Char.() -> Char){
    val ch = "Patryk".toCharArray()[0]
    ch.fn()
}

fun String.lastChar2(): Char =
        this[this.length - 1]




fun main(args: Array<String>) {
    println("Kotlin".firstChar1)
    println("Kotlin".lastCharA())
    println("Java".lastChar2())
    println("Patryk Jakubik".firstName)
    val c = "Pitos".last() +" "+ "Patryk".first()

}