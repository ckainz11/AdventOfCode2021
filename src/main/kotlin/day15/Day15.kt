package day15

import util.*
import kotlin.system.measureTimeMillis

val originalInput = getInputAsIntMatrix(15).mapMatrix { Node(it, false) }

fun solve1(): Int {
    return dijkstra(originalInput)
}

fun solve2(): Int {
    var fullMap: Matrix<Node>
    val time = measureTimeMillis {
        fullMap = generateFullMap(originalInput)
    }
    println("----- DONE WITH GENERATING MAP IN $time ms-----")
    return dijkstra(fullMap)
}

private fun generateFullMap(originalInput: Matrix<Node>): Matrix<Node> {
    val rows = originalInput.getRowNum()
    val cols = originalInput.getColNum()
    val fullMap = emptyMatrixOf<Node>(rows * 5, cols * 5, Node(0, false))
    for ((y, row) in originalInput.withIndex()) {
        for ((x, n) in row.withIndex()) {
            for(yOff in 0..4){
                for(xOff in 0..4){
                    val newValue = if (n.value + xOff + yOff > 9) n.value + xOff + yOff - 9 else n.value + xOff + yOff
                    fullMap[yOff * rows + y][xOff * cols + x] = Node(newValue, false)
                }
            }
        }
    }
    return fullMap
}
//Very inefficient but I do not have the time to fix it right now, maybe I'll do it later (I won't)
private fun dijkstra(map: Matrix<Node>): Int {
    val input = map.toMutableMatrix()
    val maxX = input.getColNum()
    val maxY = input.getRowNum()
    var visitedTracker = 0
    input[0][0] = Node(1, false, 0)
    while (visitedTracker != (maxX * maxY)) {
        val currentPoint = getLowestDist(input)
        val currentData = input[currentPoint.y][currentPoint.x]
        visitedTracker++
        input[currentPoint.y][currentPoint.x] = Node(currentData.value, true, currentData.distance)
        for (adjC in input.getAdjacentCoordinates(currentPoint)) {
            val adj = input[adjC.y][adjC.x]
            if (!adj.visited) {
                val newDist = currentData.distance + adj.value
                if (newDist < adj.distance) {
                    input[adjC.y][adjC.x] = Node(adj.value, adj.visited, newDist)
                }
                if (adjC.x == maxX - 1 && adjC.y == maxY - 1) {
                    return newDist
                }
            }
        }
    }
    return input[maxY - 1][maxX - 1].distance
}

private fun print(input: Matrix<Node>) {
    for (row in input) {
        for (node in row) {
            if (node.visited)
                print('*')
            else print('#')
        }
        println()
    }
}

private fun getLowestDist(input: Matrix<Node>): Point {
    var lowest = Int.MAX_VALUE
    var lowestPoint = Point(0, 0)
    for ((y, row) in input.withIndex()) {
        for (x in row.indices) {
            val p = input[y][x]
            if (!p.visited && p.distance < lowest) {
                lowest = p.distance
                lowestPoint = Point(x, y)
            }
        }
    }
    return lowestPoint
}

data class Node(var value: Int, var visited: Boolean, var distance: Int = Int.MAX_VALUE)