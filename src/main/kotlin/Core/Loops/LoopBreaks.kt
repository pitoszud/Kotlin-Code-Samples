package Core.Loops


// 12
fun returnOnly(){
    listOf(1,2,3,4,5,6).forEach {
        if (it == 3) return
        print(it)
    }
    print("this point is NOT reachable")
}

// 12456 this point is reachable
fun labelIt(){
    listOf(1,2,3,4,5,6).forEach lit@{
        if (it == 3) return@lit
        print(it)
    }
    print(" this point is reachable")
}


fun specifyBreakNonLambda(){
    // specify break loop
    loop@ for (i in 1..10){
        for (j in 1..10){
            println("$i, $j")
            if (j == 5) break@loop
        }
    }
}

// break
fun specifyBreakLambdaA() {
    run {
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return // non-local return from the lambda passed to run
            println(it)
        }
    }
    print(" this point is NOT reachable")
}


// break and continue
fun specifyBreakLambdaB() {
    run loop@{
        listOf(1, 2, 3, 4, 5).forEach {
            if (it == 3) return@loop // non-local return from the lambda passed to run
            println(it)
        }
    }
    print(" this point is  reachable")
}






fun main(args: Array<String>) {
    specifyBreakLambdaA()
    specifyBreakLambdaB()
}