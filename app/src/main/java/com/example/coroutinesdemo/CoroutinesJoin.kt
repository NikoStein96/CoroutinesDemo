package com.example.coroutinesdemo

import kotlinx.coroutines.*

suspend fun main() {
    val job = GlobalScope.launch { // launch new coroutine and keep a reference to its Job
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    job.join() // wait until child coroutine completes
}