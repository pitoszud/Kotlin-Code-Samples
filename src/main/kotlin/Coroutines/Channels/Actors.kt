package Coroutines.Channels


import kotlinx.coroutines.*
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach

@ExperimentalCoroutinesApi
fun main() {
    actorsExample()
}


@UseExperimental(ObsoleteCoroutinesApi::class)
@ExperimentalCoroutinesApi
fun actorsExample() = runBlocking{

    val posList = getPosList()


    // PRODUCER / CONSUMER

    // actor is only going to process one object at a time.
    val actor: SendChannel<ProductPOS> = actor<ProductPOS>(){
        //for (product in channel){ println(product.name) }
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
