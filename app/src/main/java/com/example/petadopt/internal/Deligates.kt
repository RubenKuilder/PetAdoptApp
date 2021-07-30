package com.example.petadopt.internal

import kotlinx.coroutines.*

//TODO: Wat is lazy deferred?
fun<T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}