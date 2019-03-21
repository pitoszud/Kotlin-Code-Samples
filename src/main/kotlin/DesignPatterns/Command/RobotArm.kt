package DesignPatterns.Command



fun main(args: Array<String>) {


    val robotArm = RobotArm()
    val robotEye = RobotEye()
    val movementCoordinator = MovementCoordinator()


    robotArm.initMove = true

    // setup arm commands and pass them to coordinator
    val armUpCommand: Command = MoveHandUpCommand(robotArm)
    val armDownCommand: Command = MoveHandDownCommand(robotArm)


    // setup eye commands and pass them to coordinator
    val robotEyeUpCommand: Command = OpenEyeCommand(robotEye)
    val robotEyeDownCommand: Command = CloseEyeCommand(robotEye)


    // ------------------------------------------------------------------------

    movementCoordinator.setCommands(armUpCommand, armDownCommand)

    // run coordinator
    movementCoordinator.performUpCommand()
    movementCoordinator.performUpCommand()
    movementCoordinator.performUpCommand()
    movementCoordinator.performDownCommand()
    movementCoordinator.performDownCommand()



    movementCoordinator.setCommands(robotEyeUpCommand, robotEyeDownCommand)

    // run coordinator again
    movementCoordinator.performUpCommand()
    movementCoordinator.performDownCommand()



}



class RobotArm {
    var initMove = false

    var position = DEFAULT_HIGH

    companion object {
        const val MAX_HIGH: Int = 10
        const val MIN_HIGH: Int = 0
        const val DEFAULT_HIGH: Int = 5
    }

    fun moveUp(){
        if (initMove){
            if (position < MAX_HIGH){
                position ++
                println("moving arm up to position $position")
            }
        }
    }


    fun moveDown(){
        if (initMove){
            if (position > MIN_HIGH){
                position --
                println("moving arm down to position $position")
            }
        }
    }
}


class RobotEye{
    private var open = false

    fun openEye(){
        if (!open){
            open = true
            println("opening an eye")
        }
    }

    fun closeEye(){
        if (open){
            open = false
            println("closing an eye")
        }
    }
}


interface Command{
    fun execute()
}


class MovementCoordinator{
    lateinit var upCommand: Command
    lateinit var downCommand: Command

    fun setCommands(upCommand: Command, downCommand: Command){
        this.upCommand = upCommand
        this.downCommand = downCommand
    }

    fun performUpCommand(){
        upCommand.execute()
    }

    fun performDownCommand(){
        downCommand.execute()
    }
}





// Command classes
class MoveHandUpCommand(private val robotArm: RobotArm): Command{

    override fun execute() {
       robotArm.moveUp()
    }
}

class MoveHandDownCommand(private val robotArm: RobotArm): Command{
    override fun execute() {
        robotArm.moveDown()
    }
}

class OpenEyeCommand(private val robotEye: RobotEye): Command{
    override fun execute() {
        robotEye.openEye()
    }
}

class CloseEyeCommand(private val robotEye: RobotEye): Command{
    override fun execute() {
        robotEye.closeEye()
    }
}