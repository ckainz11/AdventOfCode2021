import day7.solve1OL
import day7.solve2OL
import kotlin.system.measureTimeMillis

fun main() {
    println("Starting AdventOfCode 2021 - Merry Christmas!")
    val time1 = measureTimeMillis {
        println("Solution for part 1: ${solve1OL()}")
    }
    println("In ${time1} ms")
    val time2 = measureTimeMillis {
        println("Solution for part 2: ${solve2OL()}")
    }
    println("In ${time2} ms")
}