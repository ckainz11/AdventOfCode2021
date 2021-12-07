package day7

import util.getInputAsText
import java.lang.Math.abs

val input = getInputAsText(7).split(",").map(String::toInt).toIntArray()
fun solve1(): Int {
    var out = Int.MAX_VALUE
    for(align in input.minOrNull()!! .. input.maxOrNull()!!){
        val tempCost = calcCost(align)
        if(tempCost < out)
            out = tempCost
    }
    return out
}
fun solve2(): Int {
    var out = Int.MAX_VALUE
    for(align in input.minOrNull()!! .. input.maxOrNull()!!){
        val tempCost = calcCrabCost(align)
        if(tempCost < out)
            out = tempCost
    }
    return out
}
fun calcCost(align: Int): Int = input.sumOf { abs(it - align) }
fun calcCrabCost(align: Int) = input.sumOf { calcTriangularNumber(abs(it-align)) }
private fun calcTriangularNumber(num:Int):Int = (1..num).sumOf { it }

fun solve1OL(): Int = getInputAsText(7).split(",").map(String::toInt).run {
    (this.minOrNull()!!..this.maxOrNull()!!).minOf { dst ->
        this.sumOf { crab ->
            abs(crab - dst)
        }
    }
}
fun solve2OL(): Int = getInputAsText(7).split(",").map(String::toInt).run {
    (this.minOrNull()!!..this.maxOrNull()!!).minOf { dst ->
        this.sumOf { crab ->
            (0).rangeTo(abs(crab - dst)).sumOf { it }
        }
    }
}