package FunctionalProgramming

data class Person(val name: String, val age: Int)

fun main(args: Array<String>) {
    val people = listOf(
            Person("Patryk", 36),
            Person("Iwona", 36),
            Person("Oliwia", 2))

    people.forEach{
        if (it.name == "Patryk"){
            println("${it.name} found")
            return
        }
    }

    people.forEach label@{
        if (it.name == "Patryk") return@label
    }

    people.forEach {
        if (it.name == "Patryk") return@forEach
    }

    people.forEach(fun(person) {
        if (person.name == "Patryk") return
    })

    people.filter(fun (person): Boolean{
        return person.name == "Patryk"
    })
}