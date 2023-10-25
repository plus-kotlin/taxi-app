package com.plus.taxiapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import java.util.concurrent.ThreadPoolExecutor

@SpringBootApplication
class TaxiAppApplication
data class TaxiGpsInfoInMemory(var x: Double, var y: Double)

val drivingInMemory = ConcurrentHashMap<String, TaxiGpsInfoInMemory>()
val threadPool: ThreadPoolExecutor = Executors.newFixedThreadPool(10) as ThreadPoolExecutor
val threads = ConcurrentHashMap<String, Thread>()
fun main(args: Array<String>) {
    runApplication<TaxiAppApplication>(*args)
}

