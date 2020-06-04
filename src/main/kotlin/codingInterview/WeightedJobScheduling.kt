package codingInterview

import kotlin.collections.ArrayList
import kotlin.math.max


class WeightJob(val start: Int = 0, val end: Int = 0, price: Int = 0){
    val weightedPrice: Int = (end - start) * price

}

// https://www.geeksforgeeks.org/weighted-job-scheduling/

    fun maximum(jobs: ArrayList<WeightJob>): Int {
        jobs.sortBy {
            it.end
        }

        val tArr = IntArray(jobs.size)

        tArr[0] = jobs[0].weightedPrice

        for (i in 1 until jobs.size) {
            tArr[i] = jobs[i].weightedPrice.coerceAtLeast(tArr[i - 1])

            println("${jobs[i].weightedPrice}; ${tArr[i-1]}")
            println(tArr[i])
            println("---")

            for (j in i - 1 downTo 0) {
                if (jobs[j].end <= jobs[i].start) {
                    tArr[i] = tArr[i].coerceAtLeast(jobs[i].weightedPrice + tArr[j])

                    println("${jobs[j].end} <= ${jobs[i].start}")
                    println(tArr[i])
                    println("-----------")

                    break
                }
            }
        }
        var maxVal = Int.MIN_VALUE
        for (v in tArr) {
            if (maxVal < v) {
                maxVal = v
            }
        }
        return maxVal
    }

    fun main(args: Array<String>) {

//        val events = ArrayList<WeightJob>()
//        events.add(WeightJob(3, 9 ,2))
//        events.add(WeightJob(5, 15 ,3))
//        events.add(WeightJob(12, 7 ,2))
//        events.add(WeightJob(18, 20 ,1))

        // val evW = maximum(events)
        // println(evW)

        val eventsArr = ArrayList<WeightJob>()
        eventsArr.add(WeightJob(1,3,5))
        eventsArr.add(WeightJob(2,5,6))
        eventsArr.add(WeightJob(4,6,5))
        eventsArr.add(WeightJob(6,7,4))
        eventsArr.add(WeightJob(5,8,11))
        eventsArr.add(WeightJob(7,9,2))
        val mW = maximum(eventsArr)
        println(mW)
    }


