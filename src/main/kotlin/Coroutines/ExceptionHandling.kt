package Coroutines
import kotlinx.coroutines.*


fun main(){
    val parentChildHandler: CoroutineExceptionHandler = CoroutineExceptionHandler{ _, exception ->
        println("Exception thrown in the child or parent job: $exception")
    }

    val childHandler: CoroutineExceptionHandler = CoroutineExceptionHandler{ _, exception ->
        println("Exception thrown in the child: $exception")
    }

    supervisorExample(parentChildHandler, childHandler)


}

fun supervisorExample(pChH: CoroutineExceptionHandler, chH: CoroutineExceptionHandler){
    val parentJob = CoroutineScope(Dispatchers.Main).launch(pChH) {

        supervisorScope {
            val jobA = launch {
                val stock = getStock1(1000)
                println("Getting stock: $stock")
            }

            val jobB = launch {
                val stock = getStock2(500)
                println("Getting stock: $stock")
            }

            val jobC = launch(chH) {
                val stock = getStock3(1500)
                println("Getting stock: $stock")
            }
        }
    }

    parentJob.invokeOnCompletion {th ->
        if (th != null){
            println("Parent job failed: $th")
        }else{
            println("Parent job completed")
        }
    }
}








private suspend fun getStock1(delay: Long) : Int {
    delay(delay)
    return 55000
}

private suspend fun getStock2(delay: Long) : Int {
    delay(delay)
    return 35000
}

private suspend fun getStock3(delay: Long): Int {
    delay(delay)
    if (delay > 1000){
        throw Exception("Timeout exception")
    }
    return 45000
}