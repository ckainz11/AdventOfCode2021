package day14

import util.getInputAsText

val input = getInputAsText(14).lines()
var template = input.take(1)[0]
val rules = input.drop(2).map { it.split(" -> ") }.associate { rule -> rule[0] to rule[1].toCharArray()[0] }
val outMap = template.associate { it to template.count { c -> c == it }.toLong() }.toMutableMap()


fun solve1(): Long {
    generateMappings(template, 9)
    return outMap.maxOf { it.value } - outMap.minOf { it.value }
}

fun solve2(): Long {
    val lastChar = input.first().last()
    val template = input.first()
            .zipWithNext { a, b ->
                Pair(a, b)
            }
            .groupingBy { it }
            .eachCount()
            .mapValues { (_, v) -> v.toLong() }
            .toMutableMap()

    val rules = input.drop(2).associate {
        val (a, b) = it.split(" -> ")
        Pair(a[0], a[1]) to Pair(Pair(a[0], b[0]), Pair(b[0], a[1]))
    }

    repeat(40) {
        for ((k, v) in template.toMap()) {
            val (a, b) = rules[k] ?: error("yahoooo")
            template[k] = template.getOrDefault(k, 0) - v
            template[a] = template.getOrDefault(a, 0) + v
            template[b] = template.getOrDefault(b, 0) + v
        }
    }

    val count = template.map { (k,v) -> k.first to v }
    .groupBy({ it.first }) { it.second }
    .mapValues { (k, v) -> if(k == lastChar) v.sum() + 1 else v.sum() }
    .values

    return count.maxOf { it } - count.minOf { it }
}
private fun generateMappings(inital: String, iterations: Int) {
    val initialRules = inital.windowed(2)
    for (rule in initialRules) {
        insertRule(rule, 0, iterations)
    }
}

private fun insertRule(rule: String, depth: Int, maxDepth: Int): String {
    val r = rules[rule] ?: error("no rule found, lmao")
    val current = outMap[r] ?: 0
    outMap[r] = current + 1
    if (depth == maxDepth) {
        return "" + rule[0] + r
    }
    val newRule = "" + rule[0] + r + rule[1]
    var nextRule = ""
    for (i in 0 until newRule.length - 1) {
        nextRule += insertRule(newRule[i] + "" + newRule[i + 1], depth + 1, maxDepth)
    }
    return nextRule
}