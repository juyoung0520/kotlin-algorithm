package `kotlin-algorithm`.`0x0C_BackTracking`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

private lateinit var eggs: Array<IntArray>
private lateinit var broken: BooleanArray
private var answer = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    eggs = Array(n) {
        val st = StringTokenizer(br.readLine())
        intArrayOf(st.nextToken().toInt(), st.nextToken().toInt())
    }

    broken = BooleanArray(n)

    perm(0, 0)

    println(answer)
}

private fun perm(idx: Int, cnt: Int) {
    if (idx == eggs.size) {
        if (cnt > answer) {
            answer = cnt
        }
        return
    }

    if (broken[idx] || cnt == broken.size - 1) {
        perm(idx + 1, cnt)
        return
    }

    for (i in eggs.indices) {
        if (i == idx || broken[i]) continue

        var ncnt = cnt
        val tmp1 = eggs[idx][0]
        val tmp2 = eggs[i][0]

        eggs[idx][0] -= eggs[i][1]
        eggs[i][0] -= eggs[idx][1]

        if (eggs[idx][0] <= 0) {
            ncnt++
            broken[idx] = true
        }

        if (eggs[i][0] <= 0) {
            ncnt++
            broken[i] = true
        }

        perm(idx + 1, ncnt)

        broken[idx] = false
        broken[i] = false
        eggs[idx][0] = tmp1
        eggs[i][0] = tmp2
    }
}