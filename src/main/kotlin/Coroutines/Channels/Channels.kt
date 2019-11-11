package Coroutines.Channels
import com.sun.deploy.trace.TraceLevel.UI
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*


@ExperimentalCoroutinesApi
fun main() {
    //basicChannel(5)
    //basicProducer(5)
    //appComponentA()
    //publisherExample()
    broastcastChannel()
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


// ----------------------------------------------------------

class ProductPOS(
    val name: String = "",
    val posId: String = "",
    val quantity: Int = 0
)

@ExperimentalCoroutinesApi
fun channelExample() = runBlocking{

    val posList = getPosList()

    // Publisher A
    val channel = Channel<ProductPOS>(3)
    launch {
        posList.forEach {
            channel.send(it)
        }
    }


    // PRODUCER
    // ------------------------------------------
    // CONSUMER


    //val productPOSReceived: ProductPOS = channel.receive()

    val productPOSListReceived = mutableListOf<ProductPOS>()


    // receiving on consumer A Apple

    launch {
        val product: ProductPOS = channel.receive()
        productPOSListReceived.add(product)
        println("receiving on consumer A "+ product.name)
    }


    //receiving on consumer B Pear
    //receiving on consumer B Plum

    launch {
        for (value in channel) {
            println("receiving on consumer B "+ value.name)
            productPOSListReceived.add(value)
        }
    }

}


@ExperimentalCoroutinesApi
fun publisherExample() = runBlocking{

    val posList = getPosList()


    // Publisher B
    // Only the code inside produce can send elements to the channel so it prevents other coroutines calling send on that channel.
    val publisher: ReceiveChannel<ProductPOS> = produce<ProductPOS>(capacity = 3){
        posList.forEach {
            channel.send(it)
        }
    }

    // PRODUCER
    // ------------------------------------------
    // CONSUMER


    //val productPOSReceived: ProductPOS = channel.receive()

    val productPOSListReceived = mutableListOf<ProductPOS>()


    launch {
        publisher.consumeEach {product ->
            productPOSListReceived.add(product)
            println("receiving on consumer C "+ product.name)
        }
    }

}





@UseExperimental(ObsoleteCoroutinesApi::class)
@ExperimentalCoroutinesApi
fun broastcastChannel() = runBlocking{

    val posList = getPosList()

    // Publisher A
    val broadcastChannel = BroadcastChannel<ProductPOS>(2)

    val publisher: Job = launch {
        posList.forEach {
            broadcastChannel.send(it)
        }
    }
    //publisher.join()



    // PRODUCER
    // ------------------------------------------
    // CONSUMER


    //val productPOSReceived: ProductPOS = channel.receive()

    val productPOSListReceived = mutableListOf<ProductPOS>()


    val observerA: Job = launch {
        broadcastChannel.consumeEach{
            productPOSListReceived.add(it)
            println(productPOSListReceived.size)
            println("ObserverA: receiving on observer A "+ it.name)
        }
    }
    //println("active: ${observerA.isActive}, cancelled: ${observerA.isCancelled}, completed: ${observerA.isCompleted}")

//    val observerA: Job = GlobalScope.launch(Dispatchers.Default) {
//        broadcastChannel.consumeEach{
//            productPOSListReceived.add(it)
//            //println("ObserverA: receiving on observer A "+ it.name)
//        }
//    }

    //observerA.join()

    val observerB: Job = launch {
        broadcastChannel.consumeEach {
            productPOSListReceived.add(it)
            println(productPOSListReceived.size)
            println("ObserverB: receiving on observer B "+ it.name)
        }
    }


    //println("active: ${observerA.isActive}, cancelled: ${observerA.isCancelled}, completed: ${observerA.isCompleted}")

//    val observerB: Job = GlobalScope.launch(Dispatchers.Default) {
//        broadcastChannel.consumeEach {
//            productPOSListReceived.add(it)
//            //println("ObserverB: receiving on observer B "+ it.name)
//        }
//    }

    //observerB.join()

    //publisher.join()
    //observerA.join()
    //observerB.join()



    //publisher.cancel()
    //publisher.join()

    //observerA.join()
    //observerA.cancel()

    //observerB.join()
    //observerB.cancel()


}







suspend fun getPosList(): List<ProductPOS>{
    return withContext(Dispatchers.IO){
        listOf(
            ProductPOS("Apple", "123", 1),
            ProductPOS("Pear", "124", 1),
            ProductPOS("Plum", "125", 1)
        )
    }

}




// Next: Pipelines
// https://kotlinlang.org/docs/reference/coroutines/channels.html








