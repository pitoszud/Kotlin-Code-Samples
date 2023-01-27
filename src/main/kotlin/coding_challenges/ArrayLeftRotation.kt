package coding_challenges

fun main(args: Array<String>) {
    solution()
}

fun solution(){
    val (n, d) = readLine()!!.split(' ').map(String::toInt)
    //val na1: Array<Int> = readLine()!!.split(' ').map{it.toInt()}.toTypedArray() // Array<Int>
    //val na3: Array<Int> = readLine()!!.split(' ').map(String::toInt).toTypedArray()
    //val na4: List<Int> = readLine()!!.split(' ').map{it.toInt()}

    val na: Array<Int> = readLine()!!.split(' ').map{it.toInt()}.toTypedArray()
    if (na.size == n){
        val s: Array<Int> = sol(na, d)
        for (i in s) print("$i ")
    }
}

fun sol(na: Array<Int>, d: Int):Array<Int>{
    var i: Int
    if (d <= na.size){
        i = na.size - d
    } else{
        i = na.size - (d % na.size)
    }

    val sh = na.copyOfRange(0, na.size - i)
    val fh = na.copyOfRange(na.size - i, na.size)

    return fh + sh
}

fun sol2(na: Array<Int>, d: Int):Array<Int>{
    var solArr: Array<Int> = na
    for (i in 1..d){
        val sh: Array<Int> = arrayOf(solArr[0])
        val tempArr: Array<Int> = solArr.copyOfRange(1,na.size)
        solArr = tempArr + sh
    }
    return solArr
}

/*
* 1 12345 5%1=4
* 2 23451 5%2=3
* 3 45123 5%3=2
* 4 51234 5%4=1
* 5 12345 5%5=0
* 6 23451 size-(6%5)=4
* 7 34512 size-(7%5)=3
* */

