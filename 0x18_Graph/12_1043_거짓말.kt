package `kotlin-algorithm`.`0x18_Graph`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

/*
O(n ^ 2 * m = 50 ^ 3)
 */
private lateinit var parent: IntArray

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    parent = IntArray(n + 1) {
        it
    }

    st = StringTokenizer(br.readLine())
    repeat(st.nextToken().toInt()) {
        val idx = st.nextToken().toInt()
        parent[idx] = 0
    }

    val inputs = Array(m) {
        ArrayList<Int>()
    }

    for (i in 0 until m) {
        st = StringTokenizer(br.readLine())
        repeat(st.nextToken().toInt()) {
            val idx = st.nextToken().toInt()
            inputs[i].add(idx)
        }

        for (j in 1 until inputs[i].size) {
            union(inputs[i][0], inputs[i][j])
        }
    }

    var answer = m
    for (party in inputs) {
        if (find(party[0]) == 0) {
            answer--
        }
    }

    println(answer)
}

private fun find(idx: Int): Int {
    if (parent[idx] != idx) {
        parent[idx] = find(parent[idx])
    }
    return parent[idx]
}

private fun union(idx1: Int, idx2: Int) {
    val p1 = find(parent[idx1])
    val p2 = find(parent[idx2])

    if (p1 < p2) {
        parent[p2] = p1
    } else {
        parent[p1] = p2
    }
}