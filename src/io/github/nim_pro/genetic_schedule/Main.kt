package io.github.nim_pro.genetic_schedule

fun main(args: Array<String>) {
    val initContext = ScheduleContext(
            Data.getGroups(),
            Data.getDisciplines(),
            Data.getPlaces(),
            Data.getTeachers()
    )
    for (i in 0 until 10) {
        val exChildren = Exersise.random(initContext).crossBreed(initContext, Exersise.random(initContext))
        for (exChild in exChildren) {
            println("Child $exChild")
            val mut = exChild.mutate(initContext)
            when {
                mut.isSome() -> {
                    println("Mutant ${mut.unwrap()}")
                }
                else -> exChild
            }
        }
    }
}
