package bit_manipulation

import java.nio.ByteBuffer
import java.util.*
import kotlin.experimental.and


private fun answersToInteger(answers: List<Question>): Int{
    val answersByteArray = ByteArray(32)

    answers.forEach {
        if(it.isPositive){
            answersByteArray[it.qIndex] = 1
        }
    }

    return ByteBuffer.wrap(answersByteArray).int
}


private fun answersToInteger3(answers: List<Question>): Int{
    var aInt = 0

    answers.forEach {
        if(it.isPositive){
            val d: Int = (1 shl it.qIndex)

            println("1 shl i:${it.qIndex} = $d / (${d.to32bitString()})")
            val tempInt = aInt

            aInt = aInt or d
            println("$tempInt or $d = $aInt / (${aInt.to32bitString()})")
            println("-----------")
        }
    }

    return aInt
}


fun booleanArray2Byte(bytes: BooleanArray): Byte {
    return ((if (bytes[0]) 1 shl 7 else 0) + (if (bytes[1]) 1 shl 6 else 0) + (if (bytes[2]) 1 shl 5 else 0) +
            (if (bytes[3]) 1 shl 4 else 0) + (if (bytes[4]) 1 shl 3 else 0) + (if (bytes[5]) 1 shl 2 else 0) +
            (if (bytes[6]) 1 shl 1 else 0) + if (bytes[7]) 1 else 0).toByte()
}


fun Int.to32bitString(): String =
    Integer.toBinaryString(this).padStart(Int.SIZE_BITS, '0')


@ExperimentalUnsignedTypes
fun main() {
    val questions = listOf<Question>(
        Question("1", 0, "qa", isPositive = true), // 0001 / 1
        Question("2", 1, "qa", isPositive = true), // 000
        Question("3", 2, "qa", isPositive = true),
        Question("4", 3, "qa", isPositive = false),
        Question("5", 4, "qa", isPositive = false),
    )

    val d = answersToInteger3(questions)

    println(d.to32bitString())
/*
1 shl index:0 - 1
0 or 1 - 1
1 shl index:1 - 2
1 or 2 - 3
1 shl index:2 - 4
3 or 4 - 7
* */

}



data class Question(
    val questionId: String = UUID.randomUUID().toString(),
    val qIndex: Int = 0,
    val question: String = "",
    var isPositive: Boolean = false
)