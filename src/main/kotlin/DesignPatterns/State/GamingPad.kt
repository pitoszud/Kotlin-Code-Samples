package DesignPatterns.State

/*
* Defines methods for handling changes to the state
* plus methods that can provide user instructions and return
* the actual selected value.
* */
interface PadSetupState {
    fun previousPosition()
    fun nextPosition()
    fun selectPosition()

    fun getInstructions(): String
    fun getSelectedPosition(): Int
}

/*
* Holds references to each state and forwards
* to whichever is the current state.
* */
class Context {
    private val verticalState: PadSetupState = Vertical(this)
    private val horizontal: PadSetupState = Horizontal(this)


    lateinit var currentState: PadSetupState

    init {
        // initial state will be vertical
        setState(verticalState)
        println(currentState.getInstructions())
    }

    fun setState(state: PadSetupState){
        this.currentState = state
    }
}


class Vertical(val context: Context) : PadSetupState {
    var verticalPosition = 0
    override fun previousPosition() {
        TODO("Not yet implemented")
    }

    override fun nextPosition() {
        TODO("Not yet implemented")
    }

    override fun selectPosition() {
        TODO("Not yet implemented")
    }

    override fun getInstructions(): String {
        TODO("Not yet implemented")
    }

    override fun getSelectedPosition(): Int {
        TODO("Not yet implemented")
    }


}

class Horizontal(val context: Context) : PadSetupState {
    override fun previousPosition() {
        TODO("Not yet implemented")
    }

    override fun nextPosition() {
        TODO("Not yet implemented")
    }

    override fun selectPosition() {
        TODO("Not yet implemented")
    }

    override fun getInstructions(): String {
        TODO("Not yet implemented")
    }

    override fun getSelectedPosition(): Int {
        TODO("Not yet implemented")
    }


}