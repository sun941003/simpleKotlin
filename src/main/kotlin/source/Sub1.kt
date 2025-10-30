package org.example.source

class Sub1() {
    fun hello() = println("hello from sub1")
    fun test() {
        println("test 작성을 위한 함수")
    }
    fun test(item : List<String>): String {
        return item.joinToString { it }
    }
}