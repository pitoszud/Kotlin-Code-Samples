package DesignPatterns.Flyweight


interface DiagnosticTool{
    fun runDiagnostics(obj: Any)
}


interface CPU{

    // shared state (intrinsic)
    fun getCores(): Int
    fun isMulticore(): Boolean

    // unshared state (extrinsic)
    fun diagnose(tool: DiagnosticTool)
}


abstract class AbstractCPU(
        private val cores: Int,
        private val multicore: Boolean): CPU{

    override fun getCores(): Int {
        return cores
    }

    override fun isMulticore(): Boolean {
        return multicore
    }

    override fun diagnose(tool: DiagnosticTool) {}
}


class SingleCoreCPU(cores: Int): AbstractCPU(cores, false){

    override fun diagnose(tool: DiagnosticTool) {
        tool.runDiagnostics(this)
    }
}


class MultiCoreCPU(cores: Int): AbstractCPU(cores, true){

    override fun diagnose(tool: DiagnosticTool) {
        tool.runDiagnostics(this)
    }
}


class CpuDiagnosticTool: DiagnosticTool{

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



class CPUFactory{

    private val singleCorePool = mutableMapOf<Int, CPU>()
    private val multiCorePool = mutableMapOf<Int, CPU>()

    fun getSingleCoreCPU(cores: Int): CPU{
        var cpu: CPU? = singleCorePool[cores]
        if (cpu == null){
            println("creating new SingleCoreCpu")
            cpu = SingleCoreCPU(cores)
            singleCorePool[cores] = cpu
        }
        return cpu
    }

    fun getMultiCoreCPU(cores: Int): CPU{
        var cpu: CPU? = multiCorePool[cores]
        if (cpu == null){
            println("creating new MultiCoreCpu")
            cpu = MultiCoreCPU(cores)
            multiCorePool[cores] = cpu
        }
        return cpu
    }

}



fun main(args: Array<String>) {
    val cpuFactory = CPUFactory()
    val diagnosticTool: DiagnosticTool = CpuDiagnosticTool()


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
