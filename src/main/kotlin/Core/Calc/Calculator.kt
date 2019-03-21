package Core.Calc

open class Calculator{

    companion object {
        var l23Modules: MutableList<Module> = mutableListOf()
        var l2Modules = emptyList<Module>()
        var l3Modules = emptyList<Module>()


        fun calcGradeStatus(moduleList: List<Module>){
            l23Modules = moduleList.toMutableList()
            split()
            val extra3 = l3Modules.map { it.points.toInt() }.sum() - 120
            val extra2 = l2Modules.map { it.points.toInt() }.sum() - 120
            removeTheWorst(extra3)

        }


        // split Module list into level 2 and level 3 modules
        fun split(){
            l2Modules = l23Modules.filter { it.level == "2" }
            l3Modules = l23Modules.filter { it.level == "3" }
        }


        fun removeTheWorst(extraCredits: Int){ // size=5
            var bestValIndex = 0
            val worstModules: MutableList<Module> = mutableListOf()

            var creditBestValMap = mutableMapOf<Int, Int>()
            creditBestValMap.put(0, 0) // 30:4, 60:8, 90:12



            for (remCredit in 30..extraCredits step 30){
                println("evaluating weight $remCredit")
                var bestVal = 0
                for((index, module) in l23Modules.withIndex()){
                    val curGradeWeight = module.points.toInt() // 30, 30, 60...
                    val curCreditVal = module.grade.toInt() // 3, 4, 3...
                    println("check weight $curGradeWeight (value: $curCreditVal) at index $index")

                    if (curGradeWeight <= remCredit){ // 30 < 60, 30 < 60...
                        var possibleBestCredit = remCredit - curGradeWeight // 30-30, 60-30...
                        var tempV = creditBestValMap[possibleBestCredit]!!

                        println("previous weight: $possibleBestCredit = $remCredit - $curGradeWeight (value: $tempV)")
                        var possibleBestVal = creditBestValMap[possibleBestCredit]!! + curCreditVal // 0+3, 3+0, 3+3...
                        println("total value: $possibleBestVal = $tempV + $curCreditVal (current best value: $bestVal")
                        if (possibleBestVal > bestVal){ // 3 > 0, 4 > 3...
                            println("replace best value $bestVal with $possibleBestVal")
                            bestVal = possibleBestVal // 3, 4...
                            bestValIndex = index // 4
                            println("index with the best value: $bestValIndex")
                        }
                    }
                }
                // update best credit:value map
                creditBestValMap[remCredit] = bestVal // 30:4, 60:8, 90:12
                println("updating map for key $remCredit with value $bestVal")

                // add credit/Val to indicate the worst module that will be removed
                worstModules.add(l23Modules[bestValIndex])


                // remove best credit/value from the list (we cannot assign 3+3 or 4+4)
                //tempModuleList.removeAt(bestValIndex)
                println("------------------------")
            }

            worstModules.forEach { println(it.toString()) }
        }

    }

}





    fun main(args: Array<String>) {
        val moduleL2A = Module("T1","2","30","3")
        val moduleL2B = Module("T2","2","60","4")
        val moduleL2C = Module("T3","2","30","2")
        val moduleL2D = Module("T4","2","30","2")


        val moduleL3A = Module("T5","3","30","4")
        val moduleL3B = Module("T6","3","30","2")
        val moduleL3C = Module("T7","3","30","3")
        val moduleL3D = Module("T8","3","30","3")
        val moduleL3E = Module("T9","3","60","3")

//        val moduleL3A = Module("T5","3","30","3")
//        val moduleL3B = Module("T6","3","30","4")
//        val moduleL3C = Module("T7","3","30","3")
//        val moduleL3D = Module("T8","3","30","3")
//        val moduleL3E = Module("T9","3","60","4")

        var l23Modules: MutableList<Module> = mutableListOf()

        l23Modules.add(0, moduleL2A)
        l23Modules.add(0, moduleL2B)
        l23Modules.add(0, moduleL2C)
        l23Modules.add(0, moduleL2D)
        l23Modules.add(0, moduleL3A)
        l23Modules.add(0, moduleL3B)
        l23Modules.add(0, moduleL3C)
        l23Modules.add(0, moduleL3D)
        l23Modules.add(0, moduleL3E)

        Calculator.calcGradeStatus(l23Modules)

    }

