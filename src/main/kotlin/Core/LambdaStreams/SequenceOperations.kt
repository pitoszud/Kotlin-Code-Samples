package Core.LambdaStreams

data class Swimmer(var name : String, val age : Int, val sex : String, val siblings : List<String>)

fun main(args: Array<String>) {
    val swimmers = listOf(
            Swimmer("Pitos", 36, "male", listOf("Kamil", "Eryk")),
            Swimmer("Iwona", 35, "female", listOf("Gosia")),
            Swimmer("Oliwia", 2, "female",listOf("None")),
            Swimmer("Asia",34,"female",listOf("Adam", "Staszek")),
            Swimmer("Marysia",6,"female",listOf("None")))


    // Lazy collection

    // using temporary object
    val ldy_list1 = swimmers
            .map(Swimmer::name)
            .filter { it.endsWith("a") }
            .toList()
    println(ldy_list1)

    // using sequence
    val ldy_list2 = swimmers
            .asSequence()
            .map(Swimmer::name) // inter operation
            .filter { it.endsWith("a") } // inter operation
            .toList() // terminal operation
    println(ldy_list2)


    val numsL = generateSequence(1){it * 2}
    val num_to_1024 = numsL.takeWhile { it < 1024 }
    num_to_1024.forEach { println(it) }
}