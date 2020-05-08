package DesignPatterns.Mediator

class Display(val mediator: System, var on: Boolean = false) {

    init {
        mediator.display = this
    }

    fun turnOn(){
        on = true
        mediator.displayTurnedOn()
    }

    fun turnOff(){
        on = false
        mediator.displayTurnedOff()
    }



}