package day8

import util.getInputAsText

val uniqueLengths = setOf(2, 4, 3, 7)
val input = getInputAsText(8).lines()
fun solve1(): Int = input.map { it.split(" | ")[1].split(" ").count { s -> uniqueLengths.contains(s.length) } }.sum()

fun solve2(): Int {
    var sum = 0
    for (line in input) {
        val splitted = line.split(" | ")
        val conf = generateConfig(splitted[0].split(" "))
        val nums = splitted[1].split(" ")
        var finalNum = ""
        for (n in nums) {
            if(n == "ed")
                println("break")
            finalNum += getIntRepresentation(n, conf).toString()
        }
        sum += finalNum.toInt()
    }
    return sum
}

fun generateConfig(input: List<String>): Array<CharArray> {
    val conf = Array(4) { "".toCharArray() } //1, 4, 7, 8
    for (s in input) {
        when (s.length) {
            2 -> conf[0] = s.toCharArray().sortedArray()
            4 -> conf[1] = s.toCharArray().sortedArray()
            3 -> conf[2] = s.toCharArray().sortedArray()
            7 -> conf[3] = s.toCharArray().sortedArray()
        }
    }
    return conf
}

fun getIntRepresentation(n: String, conf: Array<CharArray>): Int {
    val twoRep = n.toCharArray().toMutableList()
    twoRep.removeAll(conf[1].toList())
    return when {
        n.toCharArray().sortedArray().contentEquals(conf[0]) -> 1
        n.toCharArray().sortedArray().contentEquals(conf[1]) -> 4
        n.toCharArray().sortedArray().contentEquals(conf[2]) -> 7
        n.toCharArray().sortedArray().contentEquals(conf[3]) -> 8
        n.length == 6 && n.toCharArray().toSet().containsAll((conf[1] + conf[2]).distinct()) -> 9
        n.length == 5 && n.toCharArray().toSet().containsAll((conf[0] + conf[2]).distinct()) -> 3
        n.length == 6 && n.toSet().containsAll(conf[2].toList()) -> 0
        n.length == 6 -> 6
        twoRep.size > 2 -> 2
        else -> 5
    }
}