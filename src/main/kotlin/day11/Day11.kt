package day11

import util.*

val input = getInputAsIntMatrix(11).map { it.map { o -> Octopus(o, false) } }.toMutableMatrix()
var flashCount = 0
fun solve1(): Int {
    repeat(100) {
        playStep()
    }
    return flashCount
}

fun solve2(): Int {
    var step = 100
    while (!isSynchronized()) {
        step++
        playStep()
    }
    return step
}

private fun playStep() {
    val flashers = mutableSetOf<Point>()
    for ((y, row) in input.withIndex()) {
        for (x in row.indices) {
            input[y][x].charge++
            val newO = input[y][x]
            if (newO.charge > 9 && !newO.hasFlashed) {
                input[y][x].hasFlashed = true
                flashCount++
                flashers.add(Point(x, y))
            }
        }
    }
    if (flashers.isNotEmpty())
        flash(flashers)
}

private fun flash(flashers: MutableSet<Point>) {
    val nextFlashers = mutableSetOf<Point>()
    for (p in flashers) {
        for (adjC in input.getSurroundingCoordinates(p)) {
            val adj = input[adjC.y][adjC.x]
            adj.charge++
            if (adj.charge > 9 && !adj.hasFlashed) {
                nextFlashers.add(adjC)
                adj.hasFlashed = true
                flashCount++
            }
        }
    }
    if (nextFlashers.isNotEmpty())
        flash(nextFlashers)
    for (p in flashers) {
        input[p.y][p.x] = Octopus(0, false)
    }
}

private fun isSynchronized(): Boolean = input.mapMatrix { it.charge == 0 }.all { row -> row.all { it } }

data class Octopus(var charge: Int, var hasFlashed: Boolean)