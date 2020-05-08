package DesignPatterns.Mediator

class System(val batteryLevel: Int = 0){

    var charger: Charger? = null
    var powerSaving: PowerSaving? = null
    var display: Display? = null

    fun chargingStarted(){
        val currentChargeLevel = charger?.chargeLevel ?: 0
        powerSaving?.disable(currentChargeLevel)
    }

    fun chargingStopped(){
        val currentChargeLevel = charger?.chargeLevel ?: 0
        powerSaving?.enable(currentChargeLevel)
    }



    fun powerSavingEnabled(){
        display?.turnOff()
    }

    fun powerSavingDisabled(){
        display?.turnOn()
    }


    fun displayTurnedOff(){
        println("display has been turned off")
    }

    fun displayTurnedOn(){
        println("display has been turned on")
    }


}