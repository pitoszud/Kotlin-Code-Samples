package rxjavaS.impl

class Student(
    val name: String,
    val age: Int
){

    override fun toString(): String {
        return "Student(name='$name', age=$age)"
    }
}
