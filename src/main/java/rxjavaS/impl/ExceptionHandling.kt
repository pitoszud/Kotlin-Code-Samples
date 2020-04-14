package rxjavaS.impl

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

fun main() {
    cancelAndExceptionHandling()
}

fun cancelAndExceptionHandling(){
    val handler = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Exception thrown in one of the children: $throwable")
    }

    val parentJob = CoroutineScope(IO).launch(handler){

        // ------ Child Job A -------------
        val childAJob = launch {
            val resA = performOperation(1)
            println("resultA: $resA")
            throw RuntimeException("error")
        }
        childAJob.invokeOnCompletion {
            if(it != null){
                println("Error getting resultA: $it")
            }
        }


        // ------ Child Job B -------------
        val childBJob = launch {
            val resB = performOperation(1)
            println("resultA: $resB")
            throw RuntimeException("error")
        }
        childBJob.invokeOnCompletion {
            if(it != null){
                println("Error getting resultA: $it")
            }
        }
    }

    parentJob.invokeOnCompletion { throwable ->
        if(throwable != null){
            println("Parent job failed: $throwable")
        }
        else{
            println("Parent job SUCCESS")
        }
    }
}


suspend fun performOperation(num: Int): Int{
    return withContext(Main){
        delay(num * 500L)
        if(num == 2){
            throw Exception("Error getting result for number: $num")
        }
        num * 2
    }
}
