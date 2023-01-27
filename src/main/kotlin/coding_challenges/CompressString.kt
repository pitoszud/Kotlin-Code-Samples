package coding_challenges

fun main(args: Array<String>) {

}

fun compress(str: String){
    var compressed = StringBuilder()
    var conc = 0
    for (i in 0 until str.length) {
        conc++
        println(conc)
        if (i + 1 >= str.length || str[i] != str[i+1]){
            compressed.append(str[i])
            compressed.append(conc)
            conc = 0
        }
    }
}
