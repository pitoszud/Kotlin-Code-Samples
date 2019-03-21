package Core.LambdaStreams

data class Person(var name : String, val age : Int, val sex : String)

fun declare() = println("I'm not a member of the Person class")

fun main(args: Array<String>) {
    val people = listOf(
            Person("Pitos", 36, "male"),
            Person("Iwona", 35, "female"),
            Person("Oliwia", 2, "female"),
            Person("Asia",34,"female"),
            Person("Marysia",6,"female"))

    println(people.maxBy { it.age })
    println(people.maxBy { p -> p.age })
    println(people.maxBy(Person::age))
    println(people.maxBy { p: Person -> p.age })

    println(people.filter { it.age > 30 }) // filter can remove elements, but cannot change them

    // [] ?????
    println(people.filter { it.age > 30 }
            .filter {it.name=="male"}
            .map { "Mr " + it.name})

    println(people.joinToString(" ") { it.name })
    println(people.joinToString(" ") { p: Person -> p.name })

    // member reference
    val getAge = Person::age // val getAge = { p: Person -> p.age }

    // running function that is not member of the Person class
    run(::declare)

    // constructor reference
    val constructPerson = ::Person
    val p = constructPerson("Iwona", 36, "female")
    println(p)

    // constructor reference with extension function
//    fun Person.isAdult() = age >= 18
//    val isA = Person::isAdult
//    println(isA)

    // simple predicate
    val isAdult = {p : Person -> p.age >= 18}
    println(people.all(isAdult)) // returns boolan
    println(people.any(isAdult)) // return boolean
    println(people.find(isAdult)) // returns first occurance
}