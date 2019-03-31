package com.example.coroutinesdemo

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main() = runBlocking {
    val channel = Channel<Int>()
    launch {
        for (x in 1..5) channel.send(x * x)
    }
    repeat(5) { println(channel.receive()) }
    println("Done!")
}
