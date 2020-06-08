package Algorithms.String

fun main() {
    val isTheSame = sameCharacters("aaaawwww")

    println(isTheSame)
}

/*
* Find Strings with the same number of ch
* */
fun sameCharacters(itemStr: String): Boolean{
    val firstChar: Char = itemStr[0]
    var even = false

    for (i in 1 until itemStr.length){
        even = itemStr[i] <= firstChar
    }

    return even
}