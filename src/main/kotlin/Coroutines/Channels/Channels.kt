package Coroutines.Channels
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.launch


@ExperimentalCoroutinesApi
fun main() {
    //basicChannel(5)
    basicProducer(5)
}


/**
 * A Channel is conceptually very similar to BlockingQueue
 * Unlike a queue, a channel can be closed to indicate that no more elements are coming.
 *
 * */
@ExperimentalCoroutinesApi
fun basicChannel(size: Int) = runBlocking{
    val ch1 = Channel<Double>()
    GlobalScope.launch {
        for (x in 0..size) ch1.send(x * 1.114)
        ch1.close()
        println(ch1.isClosedForSend)
    }

    repeat(size) { println(ch1.receive()) }
}


// ---------------------------------------------------------

@ExperimentalCoroutinesApi
fun CoroutineScope.produceNumbers(size: Int): ReceiveChannel<Double> = produce{
    for (x in 0..size) send(x * 1.114)
}

@ExperimentalCoroutinesApi
fun basicProducer(size: Int) = runBlocking {
    val receivedNumbers = produceNumbers(size).apply {
        consumeEach {
            println(it)
        }
    }
}

// Next: Pipelines
// https://kotlinlang.org/docs/reference/coroutines/channels.html





