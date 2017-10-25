package io.github.nim_pro.genetic_schedule

class Option<T> {
    class NoneOptionUnwrappingException : Exception("Trying to unwrap none-option")

    private var some: Boolean = false
    private var value: T? = null

    constructor(value: T) {
        set(value)
    }

    constructor() {
        clear()
    }

    companion object {
        fun <T> some(value: T) = Option(value)
        fun <T> none() = Option<T>()
    }

    fun isSome() = some
    fun unwrap(): T {
        if (!some)
            throw NoneOptionUnwrappingException()
        @Suppress("UNCHECKED_CAST")
        return value as T
    }

    private fun set(some: Boolean, value: T?) {
        this.some = some
        this.value = value
    }

    fun set(value: T) = set(true, value)
    fun clear() = set(false, null)
}
