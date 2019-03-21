package FunctionalProgramming

// 1. Extension function cannot access private variables

class Tree(private val name: String, var size: Int, val location: Double)
// fun Tree.prefixName(): String = "big ${this.name} // DOES NOT compile


// 2. Extension function cannot be overriden


open class Processor
fun Processor.details() = "<generic processor>"

class Amd : Processor()
fun Amd.details() = "Amd"

fun printProcessor(proc: Processor) {
    println(proc.details())
}


fun main() {
    printProcessor(Processor()) // <generic processor>
    printProcessor(Amd()) // <generic processor>
}