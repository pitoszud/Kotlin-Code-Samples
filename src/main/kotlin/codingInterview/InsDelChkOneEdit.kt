package codingInterview

fun main(args: Array<String>) {
    oneEditInsert("Pattryk", "Patryk")
}


fun oneEditInsert(s1: String, s2: String): Boolean{
    var i1 = 0
    var i2 = 0
    while (i2 < s2.length && i1 < s1.length){
        println("$i2 < ${s2.length} && $i1 < ${s1.length}")
        if (s1[i1] != s2[i2]){
            println("${s1[i1]} != ${s2[i2]}")
            if (i1 != i2){
                println("$i1 != $i2")
                return false
            }
            i2++
            println("$i2")
        }else{
            i1++
            i2++
            println("$i1, $i2")
        }
    }
    return true
}