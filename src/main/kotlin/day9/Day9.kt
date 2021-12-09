package day9

import util.Point
import util.getAdjacent
import util.getAdjacentCoordinates
import util.getInputAsIntMatrix

val input = getInputAsIntMatrix(9)

fun solve1(): Int {
    var lowPoints = 0
    for((i, row) in input.withIndex()){
        for((j, col) in row.withIndex()){
            var lowPoint = true
            for(a in input.getAdjacent(i, j)){
                lowPoint = lowPoint && a > col
            }
            if (lowPoint) lowPoints += 1 + col
        }
    }
    return lowPoints
}
val visited = mutableListOf<Point>()
fun solve2(): Int {
    val basins = mutableListOf<List<Point>>()
    for((i, row) in input.withIndex()){
        for((j, col) in row.withIndex()){
            if(input[i][j] != 9 && !pointIsVisited(Point(j, i)) )
                basins.add(findBasin(i, j))
        }
    }
    return basins.sortedBy { it.size }.takeLast(3).fold(1) {acc, list -> acc * list.size }
}
fun findBasin(row: Int, col: Int): List<Point> {
    val basin = mutableListOf(Point(col, row))
    visited.add(Point(col, row))
    for(a in input.getAdjacentCoordinates(row, col)){
        if(input[a.y][a.x] != 9 && !pointIsVisited(a))
            basin.addAll(findBasin(a.y, a.x))
    }
    return basin
}
fun pointIsVisited(point: Point): Boolean {
    return visited.any { it -> it.x == point.x && it.y == point.y }
}