package codingInterview


fun isUniqueString(str: String): Boolean {
    if (str.length > 128) return false

    val charSet = BooleanArray(128)
    for (i in 0 until str.length) {
        val charVal = str[i].toInt()
        println("check $charVal")
        if (charSet[charVal]) {
            println("already exists - return false")
            return false
        }
        println("update " + charSet[charVal] + " to true")
        charSet[charVal] = true
    }
    return true
}






fun main(args: Array<String>){

}