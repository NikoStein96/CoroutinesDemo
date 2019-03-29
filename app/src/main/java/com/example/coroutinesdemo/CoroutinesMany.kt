package com.example.coroutinesdemo

import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicLong

fun main() {
    val c = AtomicLong()

    for (i in 1..1_000_000L)
        GlobalScope.launch {
            c.addAndGet(i)
        }

    println(c.get())
}