package `kotlin-algorithm`.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)

private lateinit var board: Array<CharArray>
private val que = LinkedList<Bomb>()
private val explodeQue = LinkedList<Bomb>()

private const val BOMB = 'O'
private const val NOTHING = '.'

// 91프로에서 틀
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val r = st.nextToken().toInt()
    val c = st.nextToken().toInt()
    val n = st.nextToken().toInt()

    board = Array(r) { i ->
        val line = br.readLine()
        CharArray(c) { j ->
            if (line[j] == BOMB) {
                que.add(Bomb(i, j, 0))
            }
            line[j]
        }
    }

    repeat(n) { t ->
        val time = t + 1
        val peek = que.peek()
        if (time % 2 == 0) {
            // 1초 뒤에 터질 폭탄 구하기(이전에 파괴되지 않고 남은 폭탄들)
            while (que.peek() != null && que.peek().time + 3 == time + 1) {
                val bomb = que.poll()
                if (board[bomb.x][bomb.y] == NOTHING) continue
                explodeQue.add(bomb)
            }

            // 나머지 폭탄 설치
            installBomb(time)
        } else if (explodeQue.isNotEmpty()) {
            // 터짐
            explodeBomb()
        }
    }

    val sb = StringBuilder()
    for (i in board.indices) {
        for (j in board[0].indices) {
            sb.append(board[i][j])
        }
        sb.appendLine()
    }

    println(sb.toString())
}

private fun explodeBomb() {
    while (explodeQue.isNotEmpty()) {
        val (x, y, _) = explodeQue.poll()
        board[x][y] = NOTHING

        for (i in dx.indices) {
            val xx = x + dx[i]
            val yy = y + dy[i]

            if (xx !in board.indices || yy !in board[0].indices || board[xx][yy] == NOTHING) continue

            board[xx][yy] = NOTHING
        }
    }
}

private fun installBomb(time: Int) {
    for (i in board.indices) {
        for (j in board[0].indices) {
            if (board[i][j] == NOTHING) {
                board[i][j] = BOMB
                que.add(Bomb(i, j, time))
            }
        }
    }
}

private data class Bomb(val x: Int, val y: Int, val time: Int)