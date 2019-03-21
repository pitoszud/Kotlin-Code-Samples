package Core.Nullability

class School(val name : String, val school: School?, val street: String? = null)

fun schoolType(school : School): String? = school.school?.name
//fun schoolStreet(school: School): String? =

fun main(args: Array<String>) {
    val primaryShool = School("Primary School", null, null)
    val skiSchool = School("Ski-Primay School", primaryShool, "Akacjowa")

    println(schoolType(primaryShool))
    println(schoolType(skiSchool))
}