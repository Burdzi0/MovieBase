package tech.burdzi0.moviebase

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

object SingleThreadExecutor {

    private val thread = Executors.newSingleThreadExecutor()

    fun <T> execute(callable: Callable<T>): Future<T> {
       return thread.submit(callable)
    }
}