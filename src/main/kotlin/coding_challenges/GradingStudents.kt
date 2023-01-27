package coding_challenges

/**
 * Every student receives a grade in the inclusive range from 0 to 100.
 * Any grade less than 40 is a failing grade.
 * If the difference between the grade and the next multiple of 5 is less than 3, round grade up to the next multiple of 5.
 * If the value of grade is less than 38, no rounding occurs as the result will still be a failing grade.
 * */
fun gradingStudents(grades: Array<Int>): Array<Int> {
    var arr = arrayOf<Int>()
    grades.forEach { num ->
        arr += if (num < 38) {
            num
        } else {
            val rem = num % 5
            if ((5 - rem < 3)) num + (5 - rem) else num
        }
    }
    return arr
}