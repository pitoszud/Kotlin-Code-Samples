package Core.collections

data class Box(val a: Int, val b: Int, val c: Int, val d: Int)
val boxList = listOf(
        Box(1,2,3,4),
        Box(6,7,8,9),
        Box(10,11,12,13))


    val mutableSetNums = mutableListOf<String>()
    val items: List<String> get() = mutableSetNums.toList()
    val filteredItems = items.filter { it != "One" }


    fun main(args: Array<String>) {

        // mutable List
        val numsZ: MutableList<Int> = mutableListOf(1, 2, 3)
        numsZ.add(4)
        val numsA: List<String> = mutableListOf()
        val numsB = mutableListOf<String>()
        val numsC: MutableList<String> = arrayListOf()
        val numsD: MutableList<String> = ArrayList()


        // immutable List
        val immutableListA: List<Int> = numsZ
        val immutableListB = listOf(1, 2, 3)
        val emptyListA = listOf<String>()
        val emptyListB: List<String> = emptyList()


        // maps
        val readWriteMap = hashMapOf("One" to 1, "Two" to 2)
        val oneItem = readWriteMap["One"]
        val mapValues = readWriteMap.values
        val snapshot: Map<String, Int> = HashMap(readWriteMap)

        val empty = emptyMap<String, Int>()
        val mutMapA: Map<Int, Int> = mutableMapOf()
        val mutMapB = mutableMapOf<Int, Int>()
        mutMapB.put(0, 0)
        mutMapB[1] = 1

        // iterate map
        for ((k,v) in mutMapB.entries){
            println("$k -> $v")
        }


        // Sets
        val strings = hashSetOf("a", "b", "c", "c")
        val values = setOf(6, 7, 8, 9)
        val mutableSetVals = mutableSetOf("One", "Two", "Three")

        iterExample()

    }

    fun intArrExample(){
        val byteArrA = IntArray(8)
        val byteArrB = intArrayOf(1, 0, 0, 1, 0, 1, 1, 0)
        byteArrB.forEachIndexed{i, el ->
            println("$el at index $i")
        }
    }


    // iterate List
    fun iterExample(){
        println("withIndex example")
        for((i,b) in boxList.withIndex()){
            if ((i > 0) && (b.a < 10)){
                println(b.toString())
            }
        }

        println("\nfilterIndexed example")
        val copyBoxList = boxList.toMutableList().filterIndexed { i, b ->
            i > 0 && b.a < 10
        }
        copyBoxList.forEach { println(it.toString()) }

        println("\nforEachIndexed example")
        boxList.forEachIndexed {i, b ->
            if ((i > 0) && (b.a < 10)){
                println(b.toString())
            }
        }

        println("\nwith example")
        with(boxList){
            println(boxList[1].toString())
        }


        // filter
        val filteredBoxListA = boxList.sortedWith(compareBy( {it.a}, {it.b} ))
        val filteredBoxListB = boxList.sortedWith(compareBy<Box> {it.a}.thenBy {it.b})
        val filteredBoxListC = boxList.sortedBy({it.a})

        val filteredBoxListD = boxList.sortedWith(Comparator { o1, o2 ->
            if (o1.a > o2.b){
                return@Comparator 1
            }
            if(o1.a == o2.b){
                0
            } else{
                -1
            }
        })

        val filteredBoxListE = boxList.sortedByDescending { it.a }

    }

