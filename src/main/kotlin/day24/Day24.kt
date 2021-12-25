package day24

import util.getInputAsText

fun solve1(): Int {
    var temp = 5
    temp /= 2
    println(temp)
    var funNum = 0
    for (chunk in getInputAsText(24).lines().chunked(18)) {
        funNum++
        parse(chunk, funNum)
    }
    for (w in 1..9) {
        in4(w, 718 + w * 703)
    }
    val w = 9
    println(in4(w, in3(w, in2(w, in1(w)))))
    return 0
}

fun solve2(): Int = 0
fun parse(lines: List<String>, funNum: Int): String {
    var out = "fun in$funNum(w: Int, z: Int = 0): Int { \n" +
            "var x = 0 \n" +
            "var y = 0 \n" +
            "var z = z \n" +
            "var w = w \n"
    for (line in lines.drop(1)) {
        out += parseLine(line) + "\n"
    }
    out += "println(\"\$w \$z\") \n"
    out += "return z \n"
    out += "}"
    println(out)
    return out
}

private fun parseLine(s: String): String {
    val split = s.split(" ")
    return when (split[0]) {
        "mul" -> "${split[1]} *= ${split[2]}"
        "add" -> "${split[1]} += ${split[2]}"
        "div" -> "${split[1]} /= ${split[2]}"
        "mod" -> "${split[1]} %= ${split[2]}"
        "eql" -> "${split[1]} = if (${split[1]} == ${split[2]}) 1 else 0"
        else -> error("yikes")
    }
}
