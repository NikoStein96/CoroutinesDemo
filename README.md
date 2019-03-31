# Hvad er Coroutines ?

+ Coroutines er light-weight udgave af threads, forstået på den måde at threads er mere cpu heavy.
+ Coroutines kan håndtere concurrency.
+ Coroutines kan bruges til asynchronous og non blocking programmering.

# Hvordan starter man en Coroutine ? 

```kotlin
import kotlinx.coroutines.*

fun main() {
    GlobalScope.launch { // launch new coroutine in background and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello,") // main thread continues while coroutine is delayed
    Thread.sleep(2000L) // block main thread for 2 seconds to keep JVM alive
}
```

```
*** OUTPUT *** 
Hello,
World!
```

I Main bliver launch funktionen kaldt, som starter en ny Coroutine. Inde i launch funktionen laver vi et non blokerende kald på 1 sekund, som medfører at programmet hopper videre og printer "Hello", Derefter kalder vi Thread.Sleep i 2 sekunder så programmet ikke slutter og imens main thread er i sleep, printer Coroutinen "World!"

Når man arbejder med coroutiner skal man sikre sig, at et programs main thread ikke bliver færdig før de andre. Hvis det sker stopper programmet uanset hvor langt de forskellige coroutiner er nået. 
For at forhindre det kan man gøre forskellige ting. 

Man kan stoppe main thread med sleep metoden, hvilket gør at main thread ikke afslutter, så man kan sikre sig at coroutinerne er færdige når programmet slutter. 
Ved at bruge en runblocking scope

# Suspending Functions

> A suspending function is just a regular Kotlin function with an additional suspend modifier which indicates that the function can suspend the execution of a coroutine. Suspending functions can invoke any other regular functions, but to actually suspend the execution, it has to be another suspending function.


+ Sekventielt Eksempel

```kotlin
suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}

val time = measureTimeMillis {
    val one = doSomethingUsefulOne()
    val two = doSomethingUsefulTwo()
    println("The answer is ${one + two}")
}
println("Completed in $time ms")
```

```
*** OUTPUT ***
The answer is 42
Completed in 2017 ms
```
I dette sekventielle eksempel, har vi to suspend funktioner der skal simulere en calculation der tager 1 sekund. Fordi vi kun starter en coroutine, tager det 2 sekunder fordi den ikke udfører handlingen paralelt. 

```kotlin
val time = measureTimeMillis {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    println("The answer is ${one.await() + two.await()}")
}
println("Completed in $time ms")
```

```
*** OUTPUT *** 
The answer is 42
Completed in 1017 ms
```
Derimod hvis man bruger ovenstående kode eksempel, vil det gå hurtigere. Det skyldes at koden kører asynkront nu og det sker paralelt. Async fungerer ligesom launch, Det starter en ny coroutine som arbejder paralelt med alle andre coroutiner. Forskellen er at launch returnere et job object der ikke har en værdi i sig, og async returnere en future med et promise om at returnere en værdi.  

[Vi har brugt den officielle Kotlin dokumentation på Coroutines ](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html)



