package coding_challenges


fun main(args: Array<String>) {
    val q = intArrayOf(2, 5, 4, 2, 6)
    minimumBribes(q)
}


fun minimumBribes(q: IntArray) {
    var bribe = 0
    var chaotic = false
    val n = q.size
    for (i in 0 until n) {
        println("check if ${q[i]} - ${(i + 1)} > 2")
        if (q[i] - (i + 1) > 2) {
            chaotic = true
            break
        }
        for (j in 0.coerceAtLeast(q[i] - 2) until i) {
            if (q[j] > q[i]) bribe++
        }
    }
    if (chaotic) println("Too chaotic") else println(bribe)
}