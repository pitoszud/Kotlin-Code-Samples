package Coroutines

import kotlin.concurrent.thread

class SimpleThreads{

    fun runWithTwoSleeps(){
        thread {
            Thread.sleep(1000)
            println("World!")
        }
        print("Hello ")
        Thread.sleep(2000)
    }

        fun runWithJoin(){
        val comp = thread {
            Thread.sleep(2000)
            println("World!")
        }
        print("Hello ")
        //causes the current thread to pause execution
        // until t's thread terminates.
        comp.join()
    }
}



fun main(args: Array<String>){
    val st = SimpleThreads()
    st.runWithTwoSleeps()
    st.runWithJoin()
}