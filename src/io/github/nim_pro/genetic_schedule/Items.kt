package io.github.nim_pro.genetic_schedule

abstract class Item {
    abstract val desc: String
    override fun toString(): String {
        return "${this.javaClass.simpleName}($desc)"
    }
}

class Group(
        val id: Int,
        override val desc: String,
        val disciplines: Set<Int>
) : Item()

class Discipline(
        val id: Int,
        override val desc: String,
        val places: Set<Int>,
        val teachers: Set<Int>
) : Item()

class Place(
        val id: Int,
        override val desc: String
) : Item()

class Teacher(
        val id: Int,
        override val desc: String
) : Item()
