package day23

import util.Point
import util.getInputAsText
import kotlin.math.min

var lowest = Int.MAX_VALUE
fun solve1(): Int {
    val b: Burrow?
    val startingAmphs = mutableListOf<Field.Amphipod>()
    for((y, line) in getInputAsText(23).lines().withIndex()){
        for((x, col) in line.withIndex()){
            if(amphipodColors.contains(col))
                startingAmphs.add(Field.Amphipod(Point(x,y), Point(x,y), col))
        }
    }
    b = Burrow(startingAmphs)
//    for(a in b.amphipods) {
//        b.getMoves(a).forEach { println("Amphipod ${a.pos} ${a.type} can move to ${it.first} with ${it.second} energy")}
//    }
    solve(b)
    return lowest
}
val states = mutableMapOf<String, Int>()
private fun solve(b: Burrow, cost: Int = 0) {
    val amphipods = b.amphipods
    for(a in amphipods) {
        val moves = b.getMoves(a)
        for(move in moves) {
            val prev = a.copy()
            a.update(move.first)
            val newCost = cost + move.second
            if(b.isSolved())
                lowest = min(newCost, lowest)
            else {
                val newB = Burrow(amphipods.toList())
                val s = newB.toString()
                val current = states[s]
                if(current == null || newCost < current) {
                    states[s] = newCost
                    solve(newB, newCost)

                }
            }
            a.update(prev.pos)
        }
    }
}
fun solve2(): Int {

    return 0
}