package Generics

class MobilePhoneGen1<T>(val component: T, val componentCount: Int)
class MobilePhoneGen2<T: Component>(val component: T, val componentCount: Int)


interface Component

data class Cpu(
    val procId: Int = 123,
    val name: String = "Arm",
    val speed: Double = 4.1
): Component

data class Ram(
    val ramId: Int = 456,
    val name: String = "ram",
    val size: Int = 1500
): Component


fun main() {
    val mobileCpuGen1: MobilePhoneGen1<Cpu> = MobilePhoneGen1(Cpu(), 4)
    val ramCpuGen1: MobilePhoneGen1<Ram> = MobilePhoneGen1(Ram(), 1)

    // Problem - you can define any class type, which is not desired
    val infectedCpuGen1: MobilePhoneGen1<RansomwareVirus> = MobilePhoneGen1(RansomwareVirus(), 1000000000)


    // Solution - Create generic constrain <T: Component> type argument RansomwareVirus
    val mobileCpuGen2: MobilePhoneGen2<Cpu> = MobilePhoneGen2(Cpu(), 4)
    val ramCpuGen2: MobilePhoneGen2<Ram> = MobilePhoneGen2(Ram(), 1)
    //val infectedCpuGen2: MobilePhoneGen2<RansomwareVirus> = MobilePhoneGen2(RansomwareVirus(), 1000000000) // DOES NOT COMPILE


    // next: Generics and Variance
    // https://www.raywenderlich.com/3634394-kotlin-generics-tutorial-getting-started
}



interface Virus

data class RansomwareVirus(
    val name: String = "very dangerous virus"
): Virus