package Recursion

class RecursionA{

    fun iterateAndDisplayAtoB(n: Int){
        if (n == 1){
            print("1,")
        }else{
            iterateAndDisplayAtoB(n-1)
            print("$n,")
        }
    }

    fun iterateAndDisplayBtoA(n: Int){
        if (n == 1){
            print("1,")
        }else{
            print("$n,")
            iterateAndDisplayBtoA(n-1)
        }
    }

}


fun main(args: Array<String>) {
    val rec = RecursionA()
    rec.iterateAndDisplayAtoB(10)
    println("")
    rec.iterateAndDisplayBtoA(10)
}