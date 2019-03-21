package Streams

import java.util.*


class Main{
    private val outputStreams: List<String?>

    @JvmOverloads constructor(outputStreams: List<String?> = LinkedList()) {

        if(outputStreams.filterNotNull().size < outputStreams.size) {
            throw IllegalArgumentException("outputStreams mustn't contain null")
        }
        this.outputStreams = outputStreams
    }

    fun getOutputStream(): List<String?>{
        return outputStreams
    }
}



class Main2{
    lateinit var outputStreams: List<String?>

    @JvmOverloads constructor(outputStreams: List<String?> = LinkedList()) {

        outputStreams.let {
            this.outputStreams = outputStreams
        }

    }

    fun getOutputStream(): List<String?>{
        return outputStreams
    }
}


fun main(args: Array<String>) {
    val outputStreamWithNull: List<String?> = listOf("alpha", "beta", null, "omega")
    val mainA = Main(outputStreamWithNull)


    mainA.getOutputStream().forEach {
        println(it)
    }

    val outputStreamNoNull: List<String> = listOf("alpha", "beta", "omega")
    val mainB = Main(outputStreamNoNull)
    mainB.getOutputStream().forEach {
        println(it)
    }


}