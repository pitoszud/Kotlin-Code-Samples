package Core.Nullability

class City(val name: String){
    override fun equals(city: Any?): Boolean {
        val otherCity = city as? City ?: return false

        return otherCity.name == name
    }

    override fun hashCode(): Int =
        name.hashCode() * 37
}

fun main(args: Array<String>) {
    val h1 = City("Cambridge") // true
    val h2 = City("Cambridge") // false
    println(h1 == h2)
    println(h1.equals(city = 52))
}