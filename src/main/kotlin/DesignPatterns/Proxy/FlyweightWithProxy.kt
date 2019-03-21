package DesignPatterns.Proxy


interface Diagnostics{
    fun runDiagnostics(obj: Any)
}


interface Processor{

    // shared state (intrinsic)
    fun getCores(): Int
    fun isMulticore(): Boolean

    // unshared state (extrinsic)
    fun diagnose(tool: Diagnostics)
}


class ProxyProcessor(cores: Int, multicore: Boolean) : Processor{

    private var processor: Processor = when{
        multicore -> MultiCoreProcessor(cores)
        else -> SingleCoreProcessor(1)
    }

    override fun getCores(): Int {
        return processor.getCores()
    }

    override fun isMulticore(): Boolean {
        return processor.isMulticore()
    }

    override fun diagnose(tool: Diagnostics) {
        println("start cpu diagnostics...")
        try{
            Thread.sleep(5000)
            println("cpu diagnostics completed")
        }catch (e: InterruptedException){
            println("cpu diagnostics interrupted")
        }
    }

}


abstract class AbstractProcessor(
        private val cores: Int,
        private val multicore: Boolean): Processor {

    override fun getCores(): Int {
        return cores
    }

    override fun isMulticore(): Boolean {
        return multicore
    }

    override fun diagnose(tool: Diagnostics) {}
}


class SingleCoreProcessor(cores: Int): AbstractProcessor(cores, false){

    override fun diagnose(tool: Diagnostics) {
        tool.runDiagnostics(this)
    }
}


class MultiCoreProcessor(cores: Int): AbstractProcessor(cores, true){

    override fun diagnose(tool: Diagnostics) {
        tool.runDiagnostics(this)
    }
}


class CpuDiagnostics: Diagnostics {

    override fun runDiagnostics(obj: Any){
        println("start cpu diagnostics...")
        try{
            Thread.sleep(5000)
            println("cpu diagnostics completed")
        }catch (e: InterruptedException){
            println("cpu diagnostics interrupted")
        }
    }

}



class CPUFactory2{

    private val singleCorePool = mutableMapOf<Int, Processor>()
    private val multiCorePool = mutableMapOf<Int, Processor>()

    fun getSingleCoreCPU(cores: Int): Processor {
        var cpu: Processor? = singleCorePool[cores]
        if (cpu == null){
            println("creating new SingleCoreCpu")
            cpu = SingleCoreProcessor(cores)
            singleCorePool[cores] = cpu
        }
        return cpu
    }

    fun getMultiCoreCPU(cores: Int): Processor {
        var cpu: Processor? = multiCorePool[cores]
        if (cpu == null){
            println("creating new MultiCoreCpu")
            cpu = MultiCoreProcessor(cores)
            multiCorePool[cores] = cpu
        }
        return cpu
    }

}



fun main(args: Array<String>) {
    val cpuFactory = CPUFactory2()
    val diagnosticTool: Diagnostics = CpuDiagnostics()


    val cpuS1 = cpuFactory.getSingleCoreCPU(1)
    cpuS1.diagnose(diagnosticTool)

    val cpuS2 = cpuFactory.getSingleCoreCPU(1)
    cpuS2.diagnose(diagnosticTool)

    val cpuS3 = cpuFactory.getSingleCoreCPU(1)
    cpuS3.diagnose(diagnosticTool)


    val cpuM1 = cpuFactory.getMultiCoreCPU(4)
    cpuM1.diagnose(diagnosticTool)

    val cpuM2 = cpuFactory.getMultiCoreCPU(4)
    cpuM2.diagnose(diagnosticTool)

    val cpuM3 = cpuFactory.getMultiCoreCPU(4)
    cpuM3.diagnose(diagnosticTool)



    /*

creating new SingleCoreCpu
start cpu diagnostics...
cpu diagnostics completed
start cpu diagnostics...
cpu diagnostics completed
start cpu diagnostics...
cpu diagnostics completed
creating new MultiCoreCpu
start cpu diagnostics...
cpu diagnostics completed
start cpu diagnostics...
cpu diagnostics completed
start cpu diagnostics...
cpu diagnostics completed

*/

}
