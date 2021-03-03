package refactoring

class OrderBefore {

    fun calculatePrice(): Double{
        val exRate = 1.34
        val basePrice = 1.0
        val higherPrice = 2.0
        val highestPrice = 3.0

        return (basePrice + higherPrice + highestPrice) * exRate
    }
}
// ----------------------------------------------------------------

class OrderAfter {
    val exRate = 1.34

    fun calculatePrice(): Double {
        return PriceProcessor(this).computePrice()
    }
}


open class PriceProcessor(val order: OrderAfter) {
    val basePrice = 1.0
    val higherPrice = 2.0
    val highestPrice = 3.0

    fun computePrice(): Double {
        return (basePrice + higherPrice + highestPrice) * order.exRate
    }


}