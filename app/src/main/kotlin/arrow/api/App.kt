package arrow.api

import arrow.core.Either
import arrow.core.getOrElse

class App {
    fun greeting(): String {
        val errorOrGreeting = if (1 == 2) {
            Either.Right("Hello world")
        } else {
            Either.Left("Error")
        }
        return errorOrGreeting.getOrElse { "Whoops something went wrong" }
    }
}

fun main() {
    println(App().greeting())
}
