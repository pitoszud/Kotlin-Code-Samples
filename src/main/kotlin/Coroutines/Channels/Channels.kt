package Coroutines.Channels
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import java.util.concurrent.Executors


@ExperimentalCoroutinesApi
fun main() {
    //basicChannel(5)
    //basicProducer(5)
    //appComponentA()
    //publisherExample()
    actorsExample()
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
fun actorsExample() = runBlocking{

    val posList = getPosList()

    val channel = Channel<ProductPOS>(3)
    launch {
        posList.forEach {
            channel.send(it)
        }
    }


    // PRODUCER / CONSUMER

    // actor is only going to process one object at a time.
    val actor: SendChannel<ProductPOS> = actor<ProductPOS>(){
        consumeEach {
            println(it.name)
        }
    }


    launch {
        actor.send(ProductPOS("Banana", "1237", 2))
    }

    launch {
        actor.send(ProductPOS("Strawberry", "1237", 2))
    }




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








