package day24
//FUNCTION NOTES
/*
* in4(w, in3(w, in2(w, in1(w))))
* fun1: z = w + 1
* fun2: z = (w + 1) * 27
* fun3: z = 718 + w * 703
* fun4: z = 707 + w * 703
* */
fun in1(w: Int, z: Int = 0): Int {
    var x = 0
    var y = 0
    var z = z
    var w = w
    x *= 0
    x += z
    x %= 26
    z /= 1
    x += 12
    x = if (x == w) 1 else 0
    x = if (x == 0) 1 else 0
    y *= 0
    y += 25
    y *= x
    y += 1
    z *= y
    y *= 0
    y += w
    y += 1
    y *= x
    z += y
    println("$w $z")
    return z
}
fun in2(w: Int, z: Int = 0): Int {
    var x = 0
    var y = 0
    var z = z
    var w = w
    x *= 0
    x += z
    x %= 26
    z /= 1
    x += 12
    x = if (x == w) 1 else 0
    x = if (x == 0) 1 else 0
    y *= 0
    y += 25
    y *= x
    y += 1
    z *= y
    y *= 0
    y += w
    y += 1
    y *= x
    z += y
    println("$w $z")
    return z
}
fun in3(w: Int, z: Int = 0): Int {
    var x = 0
    var y = 0
    var z = z
    var w = w
    x *= 0
    x += z
    x %= 26
    z /= 1
    x += 15
    x = if (x == w) 1 else 0
    x = if (x == 0) 1 else 0
    y *= 0
    y += 25
    y *= x
    y += 1
    z *= y
    y *= 0
    y += w
    y += 16
    y *= x
    z += y
    println("$w $z")
    return z
}
fun in4(w: Int, z: Int = 0): Int {
    var x = 0
    var y = 0
    var z = z
    var w = w
    x *= 0
    x += z
    x %= 26
    z /= 26
    x += -8
    x = if (x == w) 1 else 0
    x = if (x == 0) 1 else 0
    y *= 0
    y += 25
    y *= x
    y += 1
    z *= y
    y *= 0
    y += w
    y += 5
    y *= x
    z += y
    println("$w $z")
    return z
}
fun in5(w: Int, z: Int = 0): Int {
    var x = 0
    var y = 0
    var z = z
    var w = w
    x *= 0
    x += z
    x %= 26
    z /= 26
    x += -4
    x = if (x == w) 1 else 0
    x = if (x == 0) 1 else 0
    y *= 0
    y += 25
    y *= x
    y += 1
    z *= y
    y *= 0
    y += w
    y += 9
    y *= x
    z += y
    println("$w $z")
    return z
}
fun in6(w: Int, z: Int = 0): Int {
    var x = 0
    var y = 0
    var z = z
    var w = w
    x *= 0
    x += z
    x %= 26
    z /= 1
    x += 15
    x = if (x == w) 1 else 0
    x = if (x == 0) 1 else 0
    y *= 0
    y += 25
    y *= x
    y += 1
    z *= y
    y *= 0
    y += w
    y += 3
    y *= x
    z += y
    println("$w $z")
    return z
}
fun in7(w: Int, z: Int = 0): Int {
    var x = 0
    var y = 0
    var z = z
    var w = w
    x *= 0
    x += z
    x %= 26
    z /= 1
    x += 14
    x = if (x == w) 1 else 0
    x = if (x == 0) 1 else 0
    y *= 0
    y += 25
    y *= x
    y += 1
    z *= y
    y *= 0
    y += w
    y += 2
    y *= x
    z += y
    println("$w $z")
    return z
}
fun in8(w: Int, z: Int = 0): Int {
    var x = 0
    var y = 0
    var z = z
    var w = w
    x *= 0
    x += z
    x %= 26
    z /= 1
    x += 14
    x = if (x == w) 1 else 0
    x = if (x == 0) 1 else 0
    y *= 0
    y += 25
    y *= x
    y += 1
    z *= y
    y *= 0
    y += w
    y += 15
    y *= x
    z += y
    println("$w $z")
    return z
}
fun in9(w: Int, z: Int = 0): Int {
    var x = 0
    var y = 0
    var z = z
    var w = w
    x *= 0
    x += z
    x %= 26
    z /= 26
    x += -13
    x = if (x == w) 1 else 0
    x = if (x == 0) 1 else 0
    y *= 0
    y += 25
    y *= x
    y += 1
    z *= y
    y *= 0
    y += w
    y += 5
    y *= x
    z += y
    println("$w $z")
    return z
}
fun in10(w: Int, z: Int = 0): Int {
    var x = 0
    var y = 0
    var z = z
    var w = w
    x *= 0
    x += z
    x %= 26
    z /= 26
    x += -3
    x = if (x == w) 1 else 0
    x = if (x == 0) 1 else 0
    y *= 0
    y += 25
    y *= x
    y += 1
    z *= y
    y *= 0
    y += w
    y += 11
    y *= x
    z += y
    println("$w $z")
    return z
}
fun in11(w: Int, z: Int = 0): Int {
    var x = 0
    var y = 0
    var z = z
    var w = w
    x *= 0
    x += z
    x %= 26
    z /= 26
    x += -7
    x = if (x == w) 1 else 0
    x = if (x == 0) 1 else 0
    y *= 0
    y += 25
    y *= x
    y += 1
    z *= y
    y *= 0
    y += w
    y += 7
    y *= x
    z += y
    println("$w $z")
    return z
}
fun in12(w: Int, z: Int = 0): Int {
    var x = 0
    var y = 0
    var z = z
    var w = w
    x *= 0
    x += z
    x %= 26
    z /= 1
    x += 10
    x = if (x == w) 1 else 0
    x = if (x == 0) 1 else 0
    y *= 0
    y += 25
    y *= x
    y += 1
    z *= y
    y *= 0
    y += w
    y += 1
    y *= x
    z += y
    println("$w $z")
    return z
}
fun in13(w: Int, z: Int = 0): Int {
    var x = 0
    var y = 0
    var z = z
    var w = w
    x *= 0
    x += z
    x %= 26
    z /= 26
    x += -6
    x = if (x == w) 1 else 0
    x = if (x == 0) 1 else 0
    y *= 0
    y += 25
    y *= x
    y += 1
    z *= y
    y *= 0
    y += w
    y += 10
    y *= x
    z += y
    println("$w $z")
    return z
}
fun in14(w: Int, z: Int = 0): Int {
    var x = 0
    var y = 0
    var z = z
    var w = w
    x *= 0
    x += z
    x %= 26
    z /= 26
    x += -8
    x = if (x == w) 1 else 0
    x = if (x == 0) 1 else 0
    y *= 0
    y += 25
    y *= x
    y += 1
    z *= y
    y *= 0
    y += w
    y += 3
    y *= x
    z += y
    println("$w $z")
    return z
}