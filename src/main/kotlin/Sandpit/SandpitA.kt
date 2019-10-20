package Sandpit


data class OrderDTO(
    val orderName: String,
    val quantity: Int,
    val orderList: List<OrderDTO>
)


class OrderPOSB(
    val orderName: String,
    val quantity: Int
){
    var orderList: MutableList<OrderPOSB> = mutableListOf()

    constructor(orderName: String, quantity: Int, orderPOSB: MutableList<OrderDTO>) : this(orderName, quantity){
        orderList = orderPOSB.toOrderPOSList()
    }
}



fun OrderDTO.toOrderPOS(): OrderPOSB {
    return OrderPOSB(
        orderName = orderName,
        quantity = quantity,
        orderPOSB = this.orderList.toMutableList()
    )
}

fun OrderDTO.toOrderPOSA(): OrderPOSB {
    return OrderPOSB(
        orderName = orderName,
        quantity = quantity
    )
}


fun List<OrderDTO>.toOrderPOSList(): MutableList<OrderPOSB>{
    val orderPOSList = mutableListOf<OrderPOSB>()
    this.forEach {
        orderPOSList.add(it.toOrderPOSA())
    }

    return orderPOSList
}


fun main() {
    val orderList = listOf(OrderDTO("order1_add_on1", 2, emptyList()))
    val orderDTO = OrderDTO("order1", 1, orderList)

    val orderPOS = orderDTO.toOrderPOS()

    val retPos = orderPOS.orderList[0]
    println(retPos.orderName)
    println(retPos.quantity)
}
