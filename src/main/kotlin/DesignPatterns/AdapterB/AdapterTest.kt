package DesignPatterns.AdapterB



fun main(args: Array<String>) {

    val usVolt = SocketObjectAdapterImpl(UKPlug()).convertToUS()

    //------------------------------------------------

    val ukVolt = SocketObjectAdapterImpl().convertToUK()



}