package io.github.nim_pro.genetic_schedule

object Data {
    fun getGroups() = Set(listOf(
            Group(
                    0,
                    "14214",
                    Set(listOf(0, 1))
            ),
            Group(
                    1,
                    "15214",
                    Set(listOf(1, 2))
            )
    ))

    fun getPlaces() = Set(listOf(
            Place(0, "201"),
            Place(1, "202")
    ))

    fun getDisciplines() = Set(listOf(
            Discipline(
                    0,
                    "Some IT discipline",
                    Set(listOf(0, 1)),
                    Set(listOf(0, 1))
            ),
            Discipline(
                    1,
                    "Some 1C discipline",
                    Set(listOf(1)),
                    Set(listOf(2, 3, 4))
            ),
            Discipline(
                    2,
                    "Some useless discipline",
                    Set(listOf(2)),
                    Set(listOf(2, 3, 4))
            )
    ))

    fun getTeachers() = Set(listOf(
            Teacher(0, "IT teacher #0"),
            Teacher(1, "IT teacher #1"),
            Teacher(2, "1C teacher #1"),
            Teacher(3, "1C teacher #2"),
            Teacher(4, "1C teacher #3")
    ))
}
