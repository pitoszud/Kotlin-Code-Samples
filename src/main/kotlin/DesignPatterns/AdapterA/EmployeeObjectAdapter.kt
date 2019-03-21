package DesignPatterns.AdapterA


class EmployeeObjectAdapter(private val adaptee: Employee) : Customer {

    override val name: String
        get() = adaptee.fullName!!

    override val designation: String
        get() = adaptee.jobTitle!!

    override val address: String
        get() = adaptee.officeLocation!!


}
