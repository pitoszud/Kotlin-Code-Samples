package Coroutines.Flows

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

@FlowPreview
@InternalCoroutinesApi
fun main() {
    val fm = FlatMaps()
    fm.accountCollector()

}

class FlatMaps{

    fun stringProducer() = flow{
        emit("Patryk")
        delay(500)
        emit("Iwona")
    }

    fun accountProducer(name: String) = flow{
        emit(Account(name))
    }


    @FlowPreview
    fun accountProducer(): Flow<Account> = flow {
        val f1: Flow<Account> = stringProducer().flatMapConcat {
            delay(100)
            accountProducer(it)
        }
    }


    @FlowPreview
    fun accountProducer2(): Flow<Account> = flow {
        val f2: Flow<Account> = stringProducer().onEach { delay(100) }
            .flatMapConcat{
                accountProducer(it)
            }
    }




    @FlowPreview
    fun accountCollector() = runBlocking {
        accountProducer2().collect {acc ->
            println(acc.name)
        }
    }


}


class Account(
    val name: String
)