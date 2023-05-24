package `kotlin-algorithm`.`0x0D_Simulation`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private val dx = arrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
private val dy = arrayOf(-1, 0, 1, -1, 1, -1, 0, 1)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    // 겨울 마다 추가 되는 양분
    val food = Array(n) {
        st = StringTokenizer(br.readLine())
        IntArray(n) {
            st.nextToken().toInt()
        }
    }
    val arr = Array(n) { IntArray(n) { 5 } } // 현재 양분
    val add = Array(n) { IntArray(n) }  // 죽어서 추가 양분

    // 나무 정보
    val trees = LinkedList<Tree>()

    repeat(m) {
        st = StringTokenizer(br.readLine())
        val x = st.nextToken().toInt() - 1
        val y = st.nextToken().toInt() - 1
        val age = st.nextToken().toInt()
        trees.add(Tree(x, y, age))
    }

    repeat(k) { year ->
        // 봄
        for (i in 0 until trees.size) {
            val t = trees.poll()
            if (arr[t.x][t.y] >= t.age) {
                arr[t.x][t.y] -= t.age
                t.age++
                trees.add(t)
            } else {
                add[t.x][t.y] += t.age / 2
            }
        }
        // 여름
        for (i in add.indices) {
            for (j in add.indices) {
                if (add[i][j] != 0) {
                    arr[i][j] += add[i][j]
                    add[i][j] = 0
                }
            }
        }
        // 가을
        val newTrees = LinkedList<Tree>()
        newTrees.forEach { }

        for (t in trees) {
            if (t.age % 5 != 0) continue

            for (j in dx.indices) {
                val x = t.x + dx[j]
                val y = t.y + dy[j]

                if (x !in arr.indices || y !in arr[0].indices) continue

                newTrees.add(Tree(x, y))
            }
        }

        for (t in newTrees) {
            trees.addFirst(t)
        }

        // 겨울
        for (i in arr.indices) {
            for (j in arr[0].indices) {
                arr[i][j] += food[i][j]
            }
        }
    }

    println(trees.size)
}

private data class Tree(val x: Int, val y: Int, var age: Int = 1)