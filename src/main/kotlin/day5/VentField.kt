package day5

import util.Point
import util.count
import util.emptyMatrixOf
import java.lang.Integer.max
import kotlin.math.abs


class VentField(vents: List<Pair<Point, Point>>, part2: Boolean) {
    private val rows = vents.maxOf { max(it.first.y, it.second.y) }
    private val cols = vents.maxOf { max(it.first.x, it.second.x) }
    private val field = emptyMatrixOf(rows + 1, cols + 1, 0)

    init {
        vents.forEach {
            if (it.first.x == it.second.x || it.first.y == it.second.y)
                mark(it)
            else if (part2) {
                markDiagonal(it)
            }
        }
    }


    private fun mark(vent: Pair<Point, Point>) {
        val yDiff = vent.second.y - vent.first.y
        val xDiff = vent.second.x - vent.first.x
        for (row in 0 until abs(yDiff)) {
            if (yDiff > 0)
                field[vent.first.y + row][vent.first.x] += 1
            else
                field[vent.first.y - row][vent.first.x] += 1
        }
        for (col in 0 until abs(xDiff)) {
            if (xDiff > 0)
                field[vent.first.y][vent.first.x + col] += 1
            else
                field[vent.first.y][vent.first.x - col] += 1
        }
        field[vent.second.y][vent.second.x] += 1
    }
    private fun markDiagonal(vent: Pair<Point, Point>) {
        val yDiff = vent.second.y - vent.first.y
        val xDiff = vent.second.x - vent.first.x
        for(i in 0 .. abs(yDiff)){
                val stepY = if(yDiff > 0) 1 else -1
                val stepX = if(xDiff > 0) 1 else -1
                field[vent.first.y + i * stepY][vent.first.x + i * stepX] += 1
        }
    }

    fun calcScore(): Int = field.count { it > 1 }
}