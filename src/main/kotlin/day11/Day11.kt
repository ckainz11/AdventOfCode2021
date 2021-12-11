package day11

import util.*

val input = getInputAsIntMatrix(11).map { it.map { o -> Octopus(o, false) }.toMutableList() }.toMutableList()
var alreadyFlashed = mutableListOf<Point>()
var flashCount = 0
fun solve1(): Int {
    println("Before any steps")
    println(input.mapMatrix {o -> o.charge }.matrixToString())
    repeat(100) {
        playStep()
        println("After step ${it+1}:")
        println(input.mapMatrix {o -> o.charge }.matrixToString())
        alreadyFlashed = mutableListOf()
    }
    return flashCount
}
fun solve2(): Int {
    var step = 100
    while (!input.mapMatrix { it.charge == 0 }.all { row -> row.all { it } }) {
        step += 1
        playStep()
    }
    return step
}
private fun playStep() {
    val flashers = mutableSetOf<Point>()
    for((y, row) in input.withIndex()){
        for((x, o) in row.withIndex()){
            input[y][x] = Octopus(o.charge + 1, o.hasFlashed)
            val newO = input[y][x]
            if(newO.charge > 9 && !newO.hasFlashed) {
                input[y][x] = Octopus(newO.charge, true)
                flashCount++
                flashers.add(Point(x, y))
            }
        }
    }
    if(flashers.isNotEmpty())
        flash(flashers)
}

private fun flash(flashers: MutableSet<Point>) {
    val nextFlashers = mutableSetOf<Point>()
    for(p in flashers){
        for(adjC in input.getSurroundingCoordinates(p)){
            var adj = input[adjC.y][adjC.x]
            input[adjC.y][adjC.x] = Octopus(adj.charge + 1, adj.hasFlashed)
            adj = input[adjC.y][adjC.x]
            if(adj.charge > 9 && !adj.hasFlashed) {
                nextFlashers.add(adjC)
                input[adjC.y][adjC.x] = Octopus(adj.charge, true)
                flashCount++
            }
        }
    }
    if(nextFlashers.isNotEmpty())
        flash(nextFlashers)
    for(p in flashers) {input[p.y][p.x] = Octopus(0, false)}
}

data class Octopus(val charge: Int, val hasFlashed: Boolean)