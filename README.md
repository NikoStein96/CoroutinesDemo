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

# CoroutinesDemo
Når man arbejder med coroutiner skal man sikre sig, at et programs main thread ikke bliver færdig før de andre. Hvis det sker stopper programmet uanset hvor langt de forskellige coroutiner er nået. 
For at forhindre det kan man gøre forskellige ting. 

Man kan stoppe main thread med sleep metoden, hvilket gør at main thread ikke afslutter, så man kan sikre sig at coroutinerne er færdige når programmet slutter. 
Ved at bruge en runblocking scope

[Vi har brugt den officielle Kotlin dokumentation på Coroutines ](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html)



