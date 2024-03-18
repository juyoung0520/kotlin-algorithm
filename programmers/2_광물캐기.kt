package `kotlin-algorithm`.programmers

private lateinit var arr: IntArray
private var min = Int.MAX_VALUE

private fun solution(picks: IntArray, minerals: Array<String>): Int {
    var n = minerals.size / 5 + 1
    var sum = 0
    for (p in picks) {
        sum += p
    }
    if (sum < n) {
        n = sum
    }
    arr = IntArray(n)

    perm(0, n, picks, minerals)

    return min
}

private fun perm(idx: Int, n: Int, picks: IntArray, minerals: Array<String>) {
    if (idx == n) {
        var i = 0
        var j = 0
        var sum = 0
        while (i < minerals.size && j < n) {
            val p = arr[j]
            for (k in 0 until 5) {
                if (i + k >= minerals.size) {
                    min = min.coerceAtMost(sum)
                    return
                }
                val plus = when {
                    minerals[i + k] == "diamond" && p == 2 -> 25
                    minerals[i + k] == "diamond" && p == 1 -> 5
                    minerals[i + k] == "iron" && p == 2 -> 5
                    else -> 1
                }
                sum += plus
            }
            i += 5
            j++
        }

        min = min.coerceAtMost(sum)
        return
    }

    for (i in picks.indices) {
        if (picks[i] > 0) {
            picks[i]--
            arr[idx] = i
            perm(idx + 1, n, picks, minerals)
            picks[i]++
        }
    }
}