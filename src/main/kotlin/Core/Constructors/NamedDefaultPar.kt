package Core.Constructors

data class Employee(
        val firstName: String, // named argument optional
        val secondname: String, // named argument optional
        val yearsOfExperience: Int = 0, // named argument must be provided
        val language: String = "English" // named argument must be provided
)

fun interview(){
    val e1 = Employee(
            "Lolek",
            secondname = "KeloL",
            yearsOfExperience = 5,
            language = "Polish")


    val e2 = Employee(
            "Bolek",
            secondname = "BeloL")


        val e3 = Employee("Tomek", "Kemot")
}