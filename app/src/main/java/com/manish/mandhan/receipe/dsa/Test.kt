package com.manish.mandhan.receipe.dsa

class Test {

}

fun main() {

    val dog = object : Animal() {
        override fun walk() {
            println("Walking with four legs")
        }

    }

    dog.walk()
}

abstract class Animal(){

    abstract fun walk()

    fun drink(){
        println("Drinking Water")
    }
}