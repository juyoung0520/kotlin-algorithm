package `kotlin-algorithm`.programmers

fun main() {
    val cap = 4
    val n = 5
    val deliveries = intArrayOf(1, 0, 3, 1, 2)
    val pickups = intArrayOf(0, 3, 0, 4, 0)
    println(solution(cap, n, deliveries, pickups))
}

private fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
    var answer = 0L
    val deliveryStack = ArrayList<House>(n)
    val pickupStack = ArrayList<House>(n)

    for (i in deliveries.indices) {
        if (deliveries[i] > 0) {
            deliveryStack.add(House(i, deliveries[i]))
        }
    }

    for (i in pickups.indices) {
        if (pickups[i] > 0) {
            pickupStack.add(House(i, pickups[i]))
        }
    }

    while (deliveryStack.isNotEmpty() || pickupStack.isNotEmpty()) {
        var max = 0
        var tmp = cap
        if (deliveryStack.isNotEmpty()) {
            max = max.coerceAtLeast(deliveryStack.last().idx)
        }
        while (tmp > 0 && deliveryStack.isNotEmpty()) {
            val b = deliveryStack.removeLast()
            if (b.boxCount <= tmp) {
                tmp -= b.boxCount
            } else {
                deliveryStack.add(House(b.idx, b.boxCount - tmp))
                break
            }
        }

        tmp = cap
        if (pickupStack.isNotEmpty()) {
            max = max.coerceAtLeast(pickupStack.last().idx)
        }
        while (tmp > 0 && pickupStack.isNotEmpty()) {
            val b = pickupStack.removeLast()
            if (b.boxCount <= tmp) {
                tmp -= b.boxCount
            } else {
                pickupStack.add(House(b.idx, b.boxCount - tmp))
                break
            }
        }

        answer += (max + 1) * 2
    }

    return answer
}


data class House(val idx: Int, val boxCount: Int)