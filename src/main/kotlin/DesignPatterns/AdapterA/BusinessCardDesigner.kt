package DesignPatterns.AdapterA

/**
 * Client code which requires Customer interface.
 */
class BusinessCardDesigner {

    fun designCard(customer: Customer): String {
        var card = ""
        card += customer.name
        card += "\n" + customer.designation
        card += "\n" + customer.address
        return card
    }
}
