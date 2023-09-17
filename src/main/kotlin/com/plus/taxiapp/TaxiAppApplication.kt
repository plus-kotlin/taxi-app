package com.plus.taxiapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TaxiAppApplication

fun main(args: Array<String>) {
    runApplication<TaxiAppApplication>(*args)
}
