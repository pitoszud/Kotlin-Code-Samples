package Core.Calc


class CalcDec{

    companion object {
        var inModuleList: MutableList<Module> = mutableListOf()

        var resultStringList: MutableList<String> = mutableListOf("", "", "")

        var level2ModList: MutableList<Module> = mutableListOf()
        var level3ModList: MutableList<Module> = mutableListOf()

        var level2ModListDel: MutableList<Module> = mutableListOf()
        var level3ModListDel: MutableList<Module> = mutableListOf()

        var level2ModListRem: MutableList<Module> = mutableListOf()
        var level3ModListRem: MutableList<Module> = mutableListOf()

        val extraGrade: Int = inModuleList.count()

        var weightGross: Int = 0
        var qAssGross: Int = 0

        var finalGradeStr: String = ""

        var qEnsVal: Int = 0
        var qEnsStr: String = ""

        var statusL3Under120 = ""
        var statusL2Under120 = ""

        fun calcGradeStatus(moduleList: List<Module>): MutableList<String>{
            inModuleList = moduleList.toMutableList()

            splitMods()
            sortModules()
            delWorstMod()
            getTotalPoints()
            getFinalGrade()
            getBestSixty()
            getQualityAssurance()

            return resultStringList
        }




        // split Module list into level 2 and level 3 modules
        private fun splitMods(){
            println("Splitting modules...")
            level2ModList = inModuleList.filter { it.level == "2" }.toMutableList()
            level3ModList = inModuleList.filter { it.level == "3" }.toMutableList()
            println("L2")
            level2ModList.forEach { println(it.toString()) }
            println("---------------------------------")
            println("L3")
            level3ModList.forEach { println(it.toString()) }
        }

        private fun sortModules(){
            println("\nSorting modules by grade (worst to best)...")
            level2ModList = level2ModList.sortedByDescending { it.grade }.toMutableList()
            level2ModList.forEach { println(it.toString()) }
            println("-----------------------------------")
            level3ModList = level3ModList.sortedByDescending { it.grade }.toMutableList()
            level3ModList.forEach { println(it.toString()) }
        }


        private fun delWorstMod(){
            println("\nRemoving worst module(s) if exist(s)...")
            val l2sum = level2ModList.map { it.points.toInt() }.sum()
            if (l2sum > 120){
                var l2diff = l2sum - 120
                // sort modules by grade from worst to best
                level2ModList.forEach {
                    val curPoints = it.points.toInt()
                    // add the worst module to del list and decrease extra points
                    if (l2diff >= curPoints) {
                        level2ModListDel.add(it)
                        l2diff -= it.points.toInt()
                    } else{
                        level2ModListRem.add(it)
                    }
                }
            }else if (l2sum < 120){
                statusL2Under120 = "Current Level2 credit: $l2sum. You need extra ${120 - l2sum} to reach target"
            }

            println("-----------------------------------")

            val l3sum = level3ModList.map { it.points.toInt() }.sum()
            if (l3sum > 120){
                var l3diff = l3sum - 120
                // sort modules by grade from worst to best
                level3ModList.forEach {
                    val curPoints = it.points.toInt()
                    // add the worst module to del list and decrease extra points
                    if (l3diff >= curPoints) {
                        level3ModListDel.add(it)
                        l3diff -= it.points.toInt()
                    } else{
                        level3ModListRem.add(it)
                    }
                }
            }else if(l3sum < 120){
                statusL3Under120 = "Current Level3 credit: $l3sum. You need extra ${120 - l3sum} to reach target"
            }

            println("L2")
            if (statusL2Under120.isNotEmpty()){ println(statusL2Under120) }
            level2ModListDel.forEach { println(it.toString()) }
            println("-------------")
            level2ModListRem.forEach { println(it.toString()) }

            println("L3")
            if (statusL3Under120.isNotEmpty()){ println(statusL3Under120) }
            level3ModListDel.forEach { println(it.toString()) }
            println("-------------")
            level3ModListRem.forEach { println(it.toString()) }

            // add status to the display list
            resultStringList[1] = "$statusL2Under120 \n $statusL3Under120"

            // add deleted modules to the display list
            addDelModToDisplayList()

        }

        private fun addDelModToDisplayList(){
            if (level2ModListDel.isNotEmpty()){
                level2ModListDel.forEach {
                    resultStringList[2] = "L2 \n $it"
                }
            }
            if (level3ModListDel.isNotEmpty()){
                level3ModListDel.forEach {
                    val s: String = resultStringList[2]
                    resultStringList[2] = "$s \n L3 \n $it"
                }
            }
        }

        fun removeWorstMods(modList: List<Module>){ // size=5

        }


        private fun getTotalPoints(){
            println("\ngetting module final grade...")
            weightGross = 0
            level2ModListRem.forEach {
                weightGross += it.points.toInt() * it.grade.toInt()
            }
            level3ModListRem.forEach {
                weightGross += it.points.toInt() * (it.grade.toInt()*2)
            }
            resultStringList[0] = "Module total gross points: $weightGross"

        }

        private fun getFinalGrade() {
            finalGradeStr =
                    when(weightGross){
                        in 0..630 -> "First (0 - 630)"
                        in 631..900 -> "Upper Second (631 - 900)"
                        in 901..1170 -> "Lower Second (901 - 1170)"
                        in 1171..1440 -> "Third (1171 - 1440)"
                        else -> ""
                    }
            val s: String = resultStringList[0]
            resultStringList[0] = "$s \nThe final grade: $finalGradeStr"
        }


        private fun getQualityAssurance(){
            qEnsStr =
                    when(qEnsVal){
                        in 0..60 -> "First (0 - 60)"
                        in 61..120 -> "Upper Second (61 - 120)"
                        in 121..180 -> "Lower Second (121 - 180)"
                        in 181..240 -> "Third (181 - 240)"
                        else -> ""
                    }
            val s: String = resultStringList[0]
            resultStringList[0] = "$s Quality Ensurance: $qEnsVal - $qEnsStr"
        }

        fun compareGradeToQualityAss(){
            val qEnsReg = ".*?(?= \\()".toRegex()
        }

        private fun getBestSixty(){
            val bestSixtyModList: MutableList<Module> = mutableListOf()
            val level3ModListRemSorted: MutableList<Module> = level3ModList.sortedBy { it.grade }.toMutableList()
            var sixtyCredits = 0

            level3ModListRemSorted.forEachIndexed { i, m ->
                if ((i == 0) && (m.points.toInt() == 60)){
                    bestSixtyModList.add(m)
                    sixtyCredits += m.points.toInt()
                } else{
                    if (sixtyCredits < 60){
                        sixtyCredits += m.points.toInt()
                        bestSixtyModList.add(m)
                    }
                }
            }

            val sb = StringBuilder()
            bestSixtyModList.forEach {
                sb.append(it.toString())
                //sb.append("\n")
            }

            qEnsVal = bestSixtyModList.map { it.points.toInt() }.sum()

            val s: String = resultStringList[0]
            resultStringList[0] = "$s \nbest 60 points module(s): \n $sb"

        }

    }
}



fun main(args: Array<String>) {

    val allModuleList: MutableList<Module> = mutableListOf()

    val moduleL2A = Module("T1","2","30","3")
    val moduleL2B = Module("T2","2","60","4")
    val moduleL2C = Module("T3","2","30","2")
    val moduleL2D = Module("T4","2","30","2")

    val moduleL3A = Module("T5","3","30","3")
    val moduleL3B = Module("T6","3","30","3")
    val moduleL3C = Module("T7","3","30","3")
    //val moduleL3D = Module("T8","3","30","3")
    val moduleL3E = Module("T9","3","60","2")

    allModuleList.add(0, moduleL2A)
    allModuleList.add(0, moduleL2B)
    allModuleList.add(0, moduleL2C)
    allModuleList.add(0, moduleL2D)
    allModuleList.add(0, moduleL3A)
    allModuleList.add(0, moduleL3B)
    allModuleList.add(0, moduleL3C)
    //allModuleList.add(0, moduleL3D)
    allModuleList.add(0, moduleL3E)

    val resList: MutableList<String> = Calc3.calcGradeStatus(allModuleList)

    resList.forEach {
        println(it)
    }

}
