package day22

import util.Point3
import util.getInputAsText
import kotlin.math.max
import kotlin.math.min

val rules = getInputAsText(22).lines().map {
    val split = it.split(" ")
    val ranges = split[1].split(",").map { s -> s.drop(2).split("..") }
    Rule(
        split[0] == "on",
        Cube(
            IntRange(ranges[0][0].toInt(), ranges[0][1].toInt()),
            IntRange(ranges[1][0].toInt(), ranges[1][1].toInt()),
            IntRange(ranges[2][0].toInt(), ranges[2][1].toInt())
        )
    )
}

fun solve1(): Int {
    return markCubesInRegion()
}

fun solve2(): Long {
    return markAllCubes()
}

private fun markAllCubes(): Long {
    var areaCount = mutableMapOf<Cube, Long>()
    for (rule in rules) {
        val updateAreaCount = mutableMapOf<Cube, Long>()
        for (a in areaCount) {

            val intersected = rule.cube.intersects(a.key)
            if (intersected != null) {
                updateAreaCount[intersected] = updateAreaCount.getOrDefault(intersected, 0) - a.value
            }
        }
        if (rule.on) {
            updateAreaCount[rule.cube] = updateAreaCount.getOrDefault(rule.cube, 0) + 1
        }
        areaCount = updateCount(areaCount, updateAreaCount)
    }
    return areaCount.map { it.key.volume * it.value }.sum()
}

private fun updateCount(origin: Map<Cube, Long>, update: Map<Cube, Long>): MutableMap<Cube, Long> {
    val new = origin.toMutableMap()
    for (entry in update) {
        val current = origin[entry.key]
        if (current == null)
            new[entry.key] = entry.value
        else
            new[entry.key] = current + entry.value
    }
    return new
}

private fun markCubesInRegion(): Int {
    val onCubes = mutableSetOf<Point3>()
    for (rule in rules) {
        if (rule.cube.zRange.first > 50 || rule.cube.zRange.last < -50 || rule.cube.yRange.first > 50 || rule.cube.yRange.last < -50 || rule.cube.xRange.first > 50 || rule.cube.xRange.last < -50)
            continue
        for (z in max(rule.cube.zRange.first, -50)..min(rule.cube.zRange.last, 50)) {
            for (y in max(rule.cube.yRange.first, -50)..min(rule.cube.yRange.last, 50)) {
                for (x in max(rule.cube.xRange.first, -50)..min(rule.cube.xRange.last, 50)) {
                    if (rule.on)
                        onCubes.add(Point3(x, y, z))
                    else
                        onCubes.remove(Point3(x, y, z))
                }
            }
        }
    }
    return onCubes.size
}


data class Rule(val on: Boolean, val cube: Cube)
data class Cube(val xRange: IntRange, val yRange: IntRange, val zRange: IntRange) {
    val volume = ((xRange.last - xRange.first + 1) * (yRange.last - yRange.first + 1) * (zRange.last - zRange.first + 1)).toLong()

    fun intersects(other: Cube): Cube? {
        val x0 = max(this.xRange.first, other.xRange.first)
        val x1 = min(this.xRange.last, other.xRange.last)
        val y0 = max(this.yRange.first, other.yRange.first)
        val y1 = min(this.yRange.last, other.yRange.last)
        val z0 = max(this.zRange.first, other.zRange.first)
        val z1 = min(this.zRange.last, other.zRange.last)
        return if (x0 <= x1 && y0 <= y1 && z0 <= z1) Cube(IntRange(x0, x1), IntRange(y0, y1), IntRange(y0, y1))
        else null
    }
}