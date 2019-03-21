package FunctionalProgramming

fun String.filter(predicate: (Char) -> Boolean): String{
    val sb = StringBuilder()
    for (i in 0 until length){
        val element = get(i)
        // predicate - name of the function
        // element - Char parameter
        // StringBuilder append(char c) - function body, returns String
        if(predicate(element)) sb.append(element)

    }
    return sb.toString()
}



// hard-coded toString conversion
fun <T> Collection<T>.joinToString(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = ""): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

// nullable parameter of a function type
fun <T> Collection<T>.joinToStringNull(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = "",
        transform: ((T) -> String)? = null): String {
            val result = StringBuilder(prefix)
            for ((index, element) in this.withIndex()) {
                if (index > 0) result.append(separator)
                val str = transform?.invoke(element) ?:
                    element.toString()
                result.append(str)
            }
            result.append(postfix)
            return result.toString()
        }

// default value for a parameter of a function type
fun <T> Collection<T>.joinToStringDefVal(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = "",
        transform: (T) -> String = { it.toString() }): String {
            val result = StringBuilder(prefix)
            for ((index, element) in this.withIndex()) {
                if (index > 0) result.append(separator)
                result.append(transform(element))
            }
            result.append(postfix)
            return result.toString()
        }


// Generic example of fold extension
fun <T, R> Collection<T>.fold(
        initial: R,
        combine: (acc: R, nextElement: T) -> R
): R {
    var accumulator: R = initial
    for (element: T in this) {
        accumulator = combine(accumulator, element)
    }
    return accumulator
}


fun main(args: Array<String>) {
    println("ab1c".filter { it in 'a'..'z' })

    val names = listOf("Patryk", "Iwona", "Oliwia")
    println(names.joinToString())
    println(names.joinToString{it.toLowerCase()})
    println(names.joinToString(
            separator = "; ",
            postfix = " Jakubik",
            transform = {it.toUpperCase()}))

    println(names.joinToStringNull())
    println(names.joinToStringNull{it.toLowerCase()})
    println(names.joinToStringNull(
            separator = ", ",
            postfix = " GlobGlob",
            transform = {it.toUpperCase()}))

    println(names.joinToStringDefVal())
    println(names.joinToStringDefVal{it.toLowerCase()})
    println(names.joinToStringDefVal(
            separator = "- ",
            postfix = " JamJam",
            transform = {it.toUpperCase()}))
}


