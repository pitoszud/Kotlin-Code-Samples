package Algorithms.String

import java.util.*

fun main() {
    val sourceStr = "abcdef"
    val isUnique = hasUniqueCharacters(sourceStr)
    val isUnique2 = hasUniqueCharacters2(sourceStr)
    println(isUnique)
    println(isUnique2)
}

/*
* Find Strings with the same number of ch
* */
fun hasUniqueCharacters(itemStr: String): Boolean{
    val charList = mutableListOf<Char>()
    val charSet = mutableSetOf<Char>()

    for (i in 1 until itemStr.length){
        charList.add(itemStr[i])
        charSet.add(itemStr[i])
    }

    return charList.size == charSet.size
}

fun hasUniqueCharacters2(itemStr: String): Boolean{

    val chArr: CharArray = itemStr.toCharArray()
    val sortedChArr: CharArray = chArr.sortedArray()

    for (i in sortedChArr.indices){
        if(sortedChArr[i] != sortedChArr[i]+1){
            continue
        }else{
            return false
        }
    }

    return true

}
