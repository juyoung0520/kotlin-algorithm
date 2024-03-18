package `kotlin-algorithm`.programmers


private val score = arrayOf(intArrayOf(1, 1, 1), intArrayOf(5, 1, 1), intArrayOf(25, 5, 1))
private val mineralMap = HashMap<String, Int>()

private fun solution(picks: IntArray, minerals: Array<String>): Int {
    val groupSize = if (minerals.size % 5 == 0) minerals.size / 5 else minerals.size / 5 + 1
    var pickCount = 0
    for (p in picks) {
        pickCount += p
    }
    mineralMap["diamond"] = 0
    mineralMap["iron"] = 1
    mineralMap["stone"] = 2

    val size = groupSize.coerceAtMost(pickCount)
    val arr = ArrayList<IntArray>(size)

    for (i in 0 until size) {
        val curr = IntArray(3)
        for (j in 0 until 5) {
            val idx = i * 5 + j
            if (idx >= minerals.size) break
            val target = mineralMap[minerals[idx]]!!
            curr[0] += score[0][target]
            curr[1] += score[1][target]
            curr[2] += score[2][target]
        }
        arr.add(curr)
    }

    arr.sortBy { -it[2] }

    var sum = 0
    for (i in 0 until size) {
        sum += when {
            picks[0] > 0 -> {
                picks[0]--
                arr[i][0]
            }

            picks[1] > 0 -> {
                picks[1]--
                arr[i][1]
            }

            picks[2] > 0 -> {
                picks[2]--
                arr[i][2]
            }

            else -> 0
        }
    }
    return sum
}