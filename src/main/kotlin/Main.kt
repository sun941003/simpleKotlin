package org.example

import org.example.source.Sub1

fun main() {
    Sub1().hello()
    Sub1().test()
    println(Sub1().test(listOf("a", "b", "c")))
}