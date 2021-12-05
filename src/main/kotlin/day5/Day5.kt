package day5

import util.Point
import util.getInputAsText
val input = getInputAsText(5).lines()
    .map { it.split(" -> ")
        .map { p -> p.split(",").map { pString -> pString.toInt() } }}
    .map { points -> Point(points[0][0], points[0][1]) to Point(points[1][0], points[1][1])}

fun solve1(): Int {
    val field = VentField(input, false)
    return field.calcScore()
}
fun solve2(): Int {
    val field = VentField(input, true)
    return field.calcScore()
}