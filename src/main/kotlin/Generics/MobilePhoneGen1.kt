package Generics

class MobilePhoneGen0(val component: Component, val componentCount: Int)
class MobilePhoneGen1<T>(val component: T, val componentCount: Int)
class MobilePhoneGen2<T: Component>(val component: T, val componentCount: Int)
class MobilePhoneGen3<out T: Component>(private val component: T?, val componentCount: Int)
class MobilePhoneGen4<in T: Component>(private val component: T?, val componentCount: Int)


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
    // You can only use a type that extends the Component interface
    val mobileCpuGen2: MobilePhoneGen2<Cpu> = MobilePhoneGen2(Cpu(), 4)
    val ramCpuGen2: MobilePhoneGen2<Ram> = MobilePhoneGen2(Ram(), 1)
    //val infectedCpuGen2: MobilePhoneGen2<RansomwareVirus> = MobilePhoneGen2(RansomwareVirus(), 1000000000) // DOES NOT COMPILE


    // ---------------------------------------------------------------------------------------------------------------


    // using out T: Component it is possible to make MobilePhoneGen4<Cpu> a subtype of MobilePhoneGen4<Component>
    val mobileCpuGen3Cpu: MobilePhoneGen3<Cpu> = MobilePhoneGen3(Cpu(), 1)
    val mobileCpuGen3Ram: MobilePhoneGen3<Component> = mobileCpuGen3Cpu


    // with in T: Component it is possible to make MobilePhoneGen4<Component> a subtype of MobilePhoneGen4<Cpu>
    val mobileCpuGen4Ram: MobilePhoneGen4<Component> = MobilePhoneGen4(Cpu(), 1)
    val mobileCpuGen4Cpu: MobilePhoneGen4<Cpu> = mobileCpuGen4Ram


    // next: Type Projection
    // https://www.raywenderlich.com/3634394-kotlin-generics-tutorial-getting-started
}



interface Virus

data class RansomwareVirus(
    val name: String = "very dangerous virus"
): Virus