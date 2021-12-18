package day17

import util.Point
import util.getInputAsText
import kotlin.math.abs

val input = getInputAsText(17).substring(13).split(", ").map { it.drop(2).split("..").let { (a,b) -> IntRange(a.toInt(), b.toInt()) }  }
val area = Area(input[0], input[1])
var successCount = 0
fun solve1(): Int = shootAll()
fun solve2(): Int = successCount
private fun shootAll(): Int {
    var highest = Int.MIN_VALUE
    for(y in area.yRange.first .. abs(area.yRange.first)){
        for(x in 1 .. area.xRange.last){
            val newHighest = shoot(Point(0,0), Point(x,y))
            if(newHighest > highest)
                highest = newHighest
        }
    }
    return highest
}
private fun shoot(pos: Point, vel: Point, highest: Int = Int.MIN_VALUE): Int{
    val newPos = Point(pos.x + vel.x, pos.y + vel.y)
    var newHighest = highest
    if(newPos.y > newHighest)
        newHighest = newPos.y
    return if(hits(newPos)) {
        successCount++
        newHighest
    }
    else if(overshoots(newPos))
        Int.MIN_VALUE
    else {
        shoot(newPos, applyForces(vel), newHighest)
    }
}
private fun applyForces(vel: Point): Point {
    val offX = if(vel.x > 0) -1 else if(vel.x < 0) 1 else 0
    return Point(vel.x + offX, vel.y - 1)
}
private fun hits(p: Point): Boolean = area.xRange.contains(p.x) && area.yRange.contains(p.y)
private fun overshoots(p: Point): Boolean = p.x > area.xRange.last || p.y < area.yRange.first //18,-6
data class Area(val xRange: IntRange, val yRange: IntRange)