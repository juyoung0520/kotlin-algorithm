package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader

private lateinit var find: CharArray
private lateinit var tmp: CharArray
private var min = Int.MAX_VALUE

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val bulbs = br.readLine().toCharArray()
    find = br.readLine().toCharArray()

    tmp = bulbs.clone()
    switch(0)
    solve(1, n)

    tmp = bulbs.clone()
    solve(0, n)

    println(if (min == Int.MAX_VALUE) -1 else min)
}

private fun switch(idx: Int) {
    if (idx - 1 >= 0) {
        tmp[idx - 1] = if (tmp[idx - 1] == '0') '1' else '0'
    }
    tmp[idx] = if (tmp[idx] == '0') '1' else '0'
    if (idx + 1 < tmp.size) {
        tmp[idx + 1] = if (tmp[idx + 1] == '0') '1' else '0'
    }
}

private fun solve(initCount: Int, n: Int) {
    var count = initCount
    for (i in 1 until  n) {
        if (tmp[i - 1] != find[i - 1]) {
            switch(i)
            count++
        }
    }

    if (tmp[n - 1] == find[n - 1] && min > count) {
        min = count
    }
}