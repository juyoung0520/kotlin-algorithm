package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val r = st.nextToken().toInt() - 1
    val c = st.nextToken().toInt() - 1
    val k = st.nextToken().toInt()

    val arr = Array(100) { IntArray(100) }
    repeat(3) { i ->
        st = StringTokenizer(br.readLine())
        repeat(3) { j ->
            arr[i][j] = st.nextToken().toInt()
        }
    }

    val counter = IntArray(101)
    val priorityQueue = PriorityQueue<Node25>(compareBy({ it.count }, { it.item }))

    var rSize = 3
    var cSize = 3

    var time = 0
    if (arr[r][c] == k) {
        println(time)
        return
    }
    while (++time <= 100) {
        if (rSize >= cSize) {
            var maxSize = 0
            for (i in 0 until rSize) {
                for (j in 0 until cSize) {
                    counter[arr[i][j]]++
                }
                for (j in 1 until counter.size) {
                    if (counter[j] != 0) {
                        priorityQueue.add(Node25(j, counter[j]))
                        counter[j] = 0
                    }
                }
                for (j in 0 until cSize) {
                    arr[i][j] = 0
                }
                var idx = 0
                while (priorityQueue.isNotEmpty()) {
                    val (item, count) = priorityQueue.poll()
                    if (idx == arr.size) break
                    arr[i][idx++] = item
                    if (idx == arr.size) break
                    arr[i][idx++] = count
                }
                if (idx > maxSize) {
                    maxSize = idx
                }
            }
            if (arr[r][c] == k) {
                println(time)
                return
            }
            cSize = maxSize
        } else {
            var maxSize = 0
            for (i in 0 until cSize) {
                for (j in 0 until rSize) {
                    counter[arr[j][i]]++
                }
                for (j in 1 until counter.size) {
                    if (counter[j] != 0) {
                        priorityQueue.add(Node25(j, counter[j]))
                        counter[j] = 0
                    }
                }
                for (j in 0 until rSize) {
                    arr[j][i] = 0
                }
                var idx = 0
                while (priorityQueue.isNotEmpty()) {
                    val (item, count) = priorityQueue.poll()
                    if (idx == arr.size) break
                    arr[idx++][i] = item
                    if (idx == arr.size) break
                    arr[idx++][i] = count
                }
                if (idx > maxSize) {
                    maxSize = idx
                }
            }
            if (arr[r][c] == k) {
                println(time)
                return
            }
            rSize = maxSize
        }
    }

    println(-1)
}

data class Node25(val item: Int, val count: Int)