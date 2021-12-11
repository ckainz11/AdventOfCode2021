package day11

import util.Point
import util.getInputAsIntMatrix
import util.getSurroundingCoordinates
import util.matrixToString

val input = getInputAsIntMatrix(11).map { it.toMutableList() }.toMutableList()
var alreadyFlashed = mutableListOf<Point>()
fun solve1(): Int {
    println("Before any steps")
    println(input.matrixToString())
    repeat(2) {
        playStep()
        println("After step ${it+1}:")
        println(input.matrixToString())
        alreadyFlashed = mutableListOf()
    }
    return 0
}
private fun playStep() {
    val flashers = mutableListOf<Point>()
    for((y, row) in input.withIndex()){
        for(x in row.indices) {
            input[y][x] += 1
            if(input[y][x] > 9)
                flashers.add(Point(x,y))
        }
    }
    flash(flashers)

}
private fun flash(flashers: List<Point>) {
    val nextFlashers = mutableListOf<Point>()
    for(flash in flashers){
        for(s in input.getSurroundingCoordinates(flash.y, flash.x)) {
            if(input[s.y][s.x] != 0 && input[s.y][s.x] < 9)
                input[s.y][s.x] += 1
            if(input[s.y][s.x] == 9 && !hasFlashed(Point(s.x, s.y)))
                nextFlashers.add(Point(s.x ,s.y))
        }
        input[flash.y][flash.x] = 0
        alreadyFlashed.add(Point(flash.x, flash.y))
    }
    //println("------------------")
    //println(input.matrixToString())
    if(nextFlashers.isNotEmpty())
        flash(nextFlashers)
}
private fun hasFlashed(point: Point): Boolean = alreadyFlashed.any { point.x == it.x && point.y == it.y }