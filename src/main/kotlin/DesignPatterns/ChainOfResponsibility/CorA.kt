package DesignPatterns.ChainOfResponsibility

interface OrderHandler{
    fun setNextHandler(handler: OrderHandler)
    fun processHandler(order: Order)

    fun getHandlerDesc(): String
}


abstract class OrderHandlerAbstract: OrderHandler{

    private var nextOrderHander: OrderHandler? = null

    override fun setNextHandler(handler: OrderHandler) {
        println("setting up ${handler.getHandlerDesc()}")
        nextOrderHander = handler
    }

    override fun processHandler(order: Order) {
        if (setPrefixList().contains(order.orderNum)){
            handleHere(order)
        }else{
            nextOrderHander?.processHandler(order)
        }
    }

    abstract fun handleHere(order: Order)
    abstract fun setPrefixList(): IntArray


    companion object {

        fun handle(order: Order){

            // handle objects
            val euroOrderHandler = EuroOrderHandler()
            val pacificOrderHandler = PacificOrderHandler()
            val asiaOrderHandler = AsiaOrderHandler()

            // chain objects
            euroOrderHandler.setNextHandler(pacificOrderHandler)
            pacificOrderHandler.setNextHandler(asiaOrderHandler)

            // start processing
            euroOrderHandler.processHandler(order)
        }
    }
}


class EuroOrderHandler : OrderHandlerAbstract() {
    override fun getHandlerDesc() = "Euro order handler"

    override fun handleHere(order: Order) {
        println("order number ${order.orderNum} processed in Europe")
    }

    override fun setPrefixList(): IntArray {
        return intArrayOf(30,31,33,34,35,38,39,44,45,46,47,58)
    }

    override fun toString(): String {
        return "EuroOrderHandler()"
    }


}



class PacificOrderHandler: OrderHandlerAbstract(){
    override fun getHandlerDesc() = "Pacific order handler"

    override fun handleHere(order: Order) {
        println("order number ${order.orderNum} processed in US")
    }

    override fun setPrefixList(): IntArray {
        return intArrayOf(1,2,5)
    }
}



class AsiaOrderHandler: OrderHandlerAbstract(){
    override fun getHandlerDesc() = "Asia order handler"

    override fun handleHere(order: Order) {
        println("order number ${order.orderNum}, for ${order.accName} processed in China")
    }

    override fun setPrefixList(): IntArray {
        return intArrayOf(2,3,6,7,8,9)
    }

}


class Order(val orderNum: Int, val accName: String)



fun main(args: Array<String>) {
    val orderA = Order(44, "Amazon UK")
    val orderB = Order(2, "Ebay US")

    OrderHandlerAbstract.handle(orderA)
    OrderHandlerAbstract.handle(orderB)

}


//setting up Pacific order handler
//setting up Asia order handler
//order number 44 processed in Europe
//setting up Pacific order handler
//setting up Asia order handler
//order number 2 processed in US

