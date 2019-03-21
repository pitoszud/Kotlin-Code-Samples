package FunctionalProgramming

fun main(args: Array<String>) {
    val i by lazy{
        println("lazy eval") // 2
        1 // 3
    }
    println("before using i") // 1
    println(i)
}