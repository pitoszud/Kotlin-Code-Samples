package DesignPatterns.AdapterA

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        /** Using Class/Two-way adapt  */
        val adapt = EmployeeClassAdapter()
        getEmployeeData(adapt)
        val designer = BusinessCardDesigner()
        var card = designer.designCard(adapt)
        println(card)

        println("************************************************************")
        /** Using Object Adapter  */
        val empl = Employee()
        getEmployeeData(empl)
        val objectAdapter = EmployeeObjectAdapter(empl)
        card = designer.designCard(objectAdapter)
        println(card)
    }

    private fun getEmployeeData(employee: Employee) {
        employee.fullName = "Elliot Alderson"
        employee.jobTitle = "Security Engineer"
        employee.officeLocation = "Allsafe Cybersecurity, New York City, New York"
    }
}
