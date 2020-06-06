package com.app.master.extension

sealed class Either<out L, out R> {

    class Loading<L, R> : Either<L, R>()

    data class Left<out L>(val left: L) : Either<L, Nothing>()

    data class Right<out R>(val right: R) : Either<Nothing, R>()


    fun either(fnLoad: () -> Any, fnL: (L) -> Any, fnR: (R) -> Any): Any =
        when (this) {
            is Loading -> fnLoad()
            is Left -> fnL(left)
            is Right -> fnR(right)
        }

    companion object {

        fun <L, R> loading() = Loading<L, R>()

        fun <R> right(right: R) = Right(right)

        fun <L> left(left: L) = Left(left)
    }
}