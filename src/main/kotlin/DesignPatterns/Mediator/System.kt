package DesignPatterns.Mediator

class System(val batteryLevel: Int = 0){

    var charger: Charger? = null
    var powerSaving: PowerSaving? = null
    var display: Display? = null

    fun chargingStarted(){

    }

    fun powerSavingEnabled(){

    }


}