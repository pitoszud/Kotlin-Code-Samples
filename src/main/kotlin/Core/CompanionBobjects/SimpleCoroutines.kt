package Core.CompanionBobjects

/*
class SimpleCoroutines{

    // runBlocking blocks the current Thread until the coroutine finishes
    fun simpleLaunch() = runBlocking{
        // launch creates a new coroutine without blocking the current Thread
        launch {
            // This function is a suspending function that
            // delays the current coroutine without blocking the current thread.
            delay(1000)
            println("World")
        }
        print("Hello ")
        delay(2000)
    }

}

fun main(args: Array<String>){
    val sc = SimpleCoroutines()
    sc.simpleLaunch()
}

        */