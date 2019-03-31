package com.example.coroutinesdemo

import kotlinx.coroutines.*

fun main() = runBlocking { // this: CoroutineScope
    launch { // launch new coroutine in the scope of runBlocking
        delay(1000L)
        println("World!" + Thread.currentThread())
    }
    println("Hello," + Thread.currentThread())
}