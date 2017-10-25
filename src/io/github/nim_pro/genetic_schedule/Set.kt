package io.github.nim_pro.genetic_schedule

import java.util.stream.Collectors
import java.util.stream.Stream

class Set<T> : Iterable<T> {
    private var elements: ArrayList<T> = ArrayList()

    constructor()
    constructor(stream: Stream<T>) {
        elements = stream.collect(Collectors.toCollection(::ArrayList))
    }

    constructor(it: Iterable<T>) {
        add(it)
    }

    fun has(elem: T) = elements.contains(elem)
    fun add(elem: T) {
        if (has(elem))
            return
        elements.add(elem)
    }

    fun add(it: Iterable<T>) {
        for (elem in it)
            add(elem)
    }

    fun remove(elem: T) = elements.remove(elem)
    fun size() = elements.size
    fun isEmpty() = elements.isEmpty()
    fun random(): T {
        if (isEmpty())
            throw NoSuchElementException()
        return elements[Math.floor(Math.random() * elements.size).toInt()]
    }

    fun stream() = elements.stream()
    override fun iterator() = elements.iterator()
    fun filter(predicate: (T) -> Boolean): Set<T> = Set(stream().filter(predicate))

    override fun toString(): String {
        val builder = StringBuilder()
        builder.append("Set(")
        val iterator = iterator()
        if (iterator.hasNext())
            builder.append(iterator.next())
        while (iterator.hasNext()) {
            builder.append(", ")
            builder.append(iterator.next())
        }
        builder.append(")")
        return builder.toString()
    }
}
