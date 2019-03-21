package DesignPatterns.AdapterA

/**
 * A class adapter, works as Two-way adapter
 */
class EmployeeClassAdapter : Employee(), Customer {

    override val name: String
        get() = this.fullName!!

    override val designation: String
        get() = this.jobTitle!!

    override val address: String
        get() = this.officeLocation!!

}
