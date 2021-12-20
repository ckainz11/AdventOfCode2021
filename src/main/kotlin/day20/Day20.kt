package day20

import util.getInputAsText
val input = getInputAsText(20).lines()
val enhance = input.first()
var infiniteChar = '.'
var image = input.drop(2)

fun solve1(): Int {
    repeat(2) {
        constructNewImage()
    }

    return image.sumOf { it.count {c -> c == '#'} }
}
fun solve2(): Int {
    repeat(48) {
        constructNewImage()
    }

    return image.sumOf { it.count {c -> c == '#'} }
}
private fun constructNewImage() {
    image = enlarge(infiniteChar)
    val newImage = MutableList(image.size) {i -> image[i]}
    for((y, row) in newImage.withIndex()){
        for((x, col) in row.withIndex()){
            val newRow = newImage[y].toCharArray()
            newRow[x] = enhance[getBinaryIndex(getNeighbourString(y, x))]
            newImage[y] = String(newRow)
        }
    }
    infiniteChar = enhance[getBinaryIndex(String(CharArray(9) { infiniteChar}))]
    image = newImage
}
private fun enlarge(c: Char): List<String> {
    val newImage = mutableListOf<String>()
    newImage.add(String(CharArray(image.size + 2) {c}))
    for(row in image){
        newImage.add("$c$row$c")
    }
    newImage.add(String(CharArray(image.size + 2) {c}))
    return newImage
}
private fun getBinaryIndex(s: String): Int = s.replace('.', '0').replace('#', '1').toInt(2)
private fun getNeighbourString(row: Int, col: Int): String {
    var out = ""
    for(y in row-1 .. row + 1)
        for(x in col-1 .. col + 1){
            out += try {
                image[y][x]
            } catch (e: IndexOutOfBoundsException){
                infiniteChar
            }
        }
    return out
}