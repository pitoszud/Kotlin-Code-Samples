package DesignPatterns.Decorator

interface MobileDevice{
    enum class OS {IOS, ANDROID, WINDOWS, UBUNTU, NO_INSTALLATION}

    // abstract state access
    fun getCPU(): CPU
    fun getOS(): MobileDevice.OS
    fun install(os: MobileDevice.OS)

    // concrete state access
    fun getPrice(): Double
    fun getScreenSize(): Double
}


abstract class AbstractMobileDevice(
        val cpu: CPU,
        var os: MobileDevice.OS) : MobileDevice{


    override fun getCPU(): CPU{
        return this.cpu
    }

    override fun getOS(): MobileDevice.OS {
        return this.os
    }

    override fun install(os: MobileDevice.OS) {
        this.os = os
    }

}

// abstract class accepts an object, extracts its state and pass it to
// the super abstract class that will save the state and provide an access to the state
abstract class MobileDeviceDecorator(var mobDev: MobileDevice):
        AbstractMobileDevice(mobDev.getCPU(), mobDev.getOS())


// object is passed to the abstract decorator, which will extract the state
// and pass it to the super abstract class
class MobilePhoneLargeScreen(mobDev: MobileDevice): MobileDeviceDecorator(mobDev){

    override fun getPrice(): Double {
        val updatedPrice = mobDev.getPrice() + 500.00
        println("updated price: $updatedPrice")
        return updatedPrice
    }

    override fun getScreenSize(): Double {
        val upgradedScreen = mobDev.getScreenSize() + 1.0
        println("upgraded screen: $upgradedScreen")
        return upgradedScreen
    }
}

// state of the object is passed explicitly to the super abstract class
// concrete class must implement methods from the interface
// if the abstract class implementing the interface does not override the method

class MobilePhoneBasic(cpu: CPU): AbstractMobileDevice(cpu, MobileDevice.OS.NO_INSTALLATION) {

    override fun getScreenSize(): Double {
        val basicScreenSize = 4.7
        println("basic screen size: $basicScreenSize")
        return basicScreenSize
    }

    override fun getPrice(): Double {
        val basePrice = 1000.00
        println("base price: $basePrice")
        return basePrice
    }
}

fun main(args: Array<String>) {
    var mobDev: MobileDevice = MobilePhoneBasic(CPU())
    mobDev.install(MobileDevice.OS.ANDROID)

    // add bigger screen
    mobDev = MobilePhoneLargeScreen(mobDev)

    mobDev.getScreenSize()
    mobDev.getPrice()
}


//basic screen size: 4.7
//upgraded screen: 5.7
//base price: 1000.0
//updated price: 1500.0





class CPU{


}