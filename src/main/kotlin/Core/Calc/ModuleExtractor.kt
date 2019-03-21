package Core.Calc

import java.util.regex.Pattern

class ModuleExtractor{
    fun extractModules(studRecStr: String): List<Module> {
        var refList = mutableListOf<String>()
        var creditList = mutableListOf<String>()
        var gradeList = mutableListOf<String>()

        val p1 = Pattern.compile("((?<=\\()[A-Z]{1,3}\\d{1,4}(?=\\)))")
        val m1 = p1.matcher(studRecStr)

        while (m1.find()) {
            val refStr = m1.group()
            //println(refStr)
            refList.add(refStr)
        }

        val p2 = Pattern.compile("((3|6)0(?= credits))")
        val m2 = p2.matcher(studRecStr)

        while (m2.find()) {
            val creditStr = m2.group()
            //println(creditStr)
            creditList.add(creditStr)
        }

        val p3 = Pattern.compile("((?<=Grade |grade )\\b([1-9])\\b)")
        val m3 = p3.matcher(studRecStr)

        while (m3.find()) {
            val gradeStr = m3.group()
            //println(gradeStr)
            gradeList.add(gradeStr)
        }

        // Check the number of level 1 modules (gradeList < refList as L1 does not have grades at all)
        val numL1 = refList.size - gradeList.size


        // remove last two modules/credits that are level 1
        refList = refList.dropLast(numL1).toMutableList()
        creditList = creditList.dropLast(numL1).toMutableList()

        val moduleList = mutableListOf<Module>()

        if ((refList.size == creditList.size) && (creditList.size == gradeList.size)) {
            refList.forEachIndexed { i, s ->
                val module = Module(s, "2", creditList[i], gradeList[i])
                moduleList.add(module)
            }
        }

        //moduleList.forEach { println(it.toString()) }

        // strangely the return type of the method converts mutable to immutable collection (during a runtime I believe)
        return moduleList

    }
}

fun main(args: Array<String>) {
    val str = "ovides official gown hire and photographic services on behalf of the University at each ceremony. If you have not already done so we would strongly advise you to book these services immediately by going to www.edeandravenscroft.co.uk to avoid disappointment. Facilities for the deaf and hard of hearing or those with speech impairments are available using your Textphone. Dial 18001 01223 861854 to access the RNID Typetalk system. For any other enquiries regarding gown hire and photography please call Ede and Ravenscroft on 01223 861854.\n" +
            "\n" +
            "Completed modules\n" +
            "Data management and analysis (TM351)\t2017\t30 credits\tGrade 3 Pass\n" +
            "Web, mobile and cloud technologies (TM352)\t2016\t30 credits\tGrade 2 Pass\n" +
            "Software engineering (TM354)\t2015\t30 credits\tGrade 3 Pass\n" +
            "Algorithms, data structures and computability (M269)\t2015\t30 credits\tGrade 3 Pass\n" +
            "Developing concurrent distributed systems (M362)\t2015\t30 credits\tGrade 3 Pass\n" +
            "Object-oriented Java programming (M250)\t2013\t30 credits\tGrade 4 Pass\n" +
            "Cisco networking (CCNA) (T216)\t2013\t60 credits\tGrade 2 Pass\n" +
            "Computers and processors (T224)\t2012\t30 credits\tGrade 4 Pass\n" +
            "Microsoft server technologies (TM128)\t2010\t30 credits\tPass\n" +
            "Data, computing and information (M150)\t2010\t30 credits\tPass\n" +
            "Networked living: exploring information and communication technologies (T175)\t2009\t30 credits\tPass\n" +
            "Diploma supplement\n" +
            "Your Diploma Supplement is produced using terms and levels that are approved within the Higher Education Qualifications Framework that applies in England, Wales and Northern Ireland. Terms used in your Diploma Supplement about academic levels and credit more generally should be read within the context of the Higher Education system within which your qualification sits."

    val me = ModuleExtractor()
    me.extractModules(str)
}