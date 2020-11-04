package Coroutines.Flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
val stateFlow = StateFlowExample()
    stateFlow.updateFlow()
    stateFlow.updateSharedFlow()

    stateFlow.observeName()
}


class StateFlowExample{
    // val _nameList = MutableStateFlow<List<String>>(emptyList())
    // val nameList: StateFlow<List<String>> = _nameList


    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val transformedName = name.map {
        it.toUpperCase()
    }


    private val _nameShared = MutableSharedFlow<String>()
    val nameShared: MutableSharedFlow<String> = _nameShared


    private val transformedNameShared = nameShared.map {
        it.toUpperCase()
    }

    suspend fun updateFlow(){
        for (i in 1..5){
            _name.value = "From StateFlow: name, $i"
            delay(1000)
        }
    }

    suspend fun updateSharedFlow(){
        for (i in 1..5){
            _nameShared.emit("From SharedFlow name, $i")
            delay(1000)
        }
    }


    suspend fun observeName(){
        transformedName.collect {
            print(it)
        }

        transformedNameShared.collect {
            print(it)
        }
    }


}