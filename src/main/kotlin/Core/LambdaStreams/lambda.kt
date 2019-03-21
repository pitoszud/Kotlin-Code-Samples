package Core.LambdaStreams

/**
 * Created by upatryk on 11/10/2017.
 */
fun getCust(obj: Any): List<String> {
    val fruits = listOf("banana", "avocado", "apple", "kiwi")
    fruits
            .filter { it.startsWith("a") }
            .sortedBy { it }
            .map { it.toUpperCase() }
            .forEach { println(it) }
    return fruits
}

fun main(args: Array<String>) {
    println(getCust(args))
}