package Core.Nullability

class Address(val streetAddress: String, val zipCode: Int, val city: String, val country: String)

class Company(val name: String, val address: Address?)

class Person(val name: String, val company: Company?)

fun Person.countryName(): String {
    return company?.address?.country ?: "Unknown"
}

//above is based on:
fun foo(s : String?): String{
    return s ?: "Unknown"
}


fun printLabel(person: Person){
    val address = person.company?.address ?: throw IllegalArgumentException("no address")
    //below will be run only if an exception is not thrown
    with(address){
        println(streetAddress)
        println("$zipCode $city, $country")
    }

}

fun main(args: Array<String>) {
    val p = Person("Pitos", null)
    println(p.countryName())

    val address = Address("Street name", 12345, "London", "UK")
    val company = Company("CompanyName", address)
    val person = Person("Pitos", company)
    printLabel(person)
    println(Person("Pitos", null))
}
