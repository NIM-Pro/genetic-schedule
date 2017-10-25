package io.github.nim_pro.genetic_schedule

interface ExersiseContext {
    fun getGroups(): Set<Group>
    fun getDisciplines(g: Group? = null): Set<Discipline>
    fun getPlaces(d: Discipline? = null): Set<Place>
    fun getTeachers(d: Discipline? = null): Set<Teacher>
}

data class Exersise(
        var group: Group,
        var discipline: Discipline,
        var place: Place,
        var teacher: Teacher
) {
    companion object {
        const val CrossBreedMinReplics: Int = 4
        const val CrossBreedMaxReplics: Int = 8
        const val CompletionMaxTries: Int = 8

        fun random(ctx: ExersiseContext) = Exersise(
                ctx.getGroups().random(),
                ctx.getDisciplines().random(),
                ctx.getPlaces().random(),
                ctx.getTeachers().random()
        )
    }

    fun crossBreed(ctx: ExersiseContext, rhs: Exersise): Set<Exersise> {
        val result = Set<Exersise>()
        val groups = Set(listOf(group, rhs.group))
        val disciplines = Set(listOf(discipline, rhs.discipline))
        val places = Set(listOf(place, rhs.place))
        val teachers = Set(listOf(teacher, rhs.teacher))
        val CrossBreedReplicsVar = CrossBreedMaxReplics - CrossBreedMinReplics
        val replics = Math.floor(Math.random() * CrossBreedReplicsVar).toInt() + CrossBreedMinReplics
        for (i in 0..replics) {
            val element = Exersise(
                    groups.random(),
                    disciplines.random(),
                    places.random(),
                    teachers.random()
            )
            if (element.complete(ctx))
                result.add(element)
        }
        return result
    }

    fun mutate(ctx: ExersiseContext): Option<Exersise> {
        val groups = ctx.getGroups()
        if (groups.isEmpty())
            return Option.none()
        val result = copy(group = groups.random())
        if (result.complete(ctx, true))
            return Option.some(result)
        return Option.none()
    }

    private fun complete(ctx: ExersiseContext, random: Boolean = false): Boolean {
        val disciplines = ctx.getDisciplines(group)
        for (i in 0 until CompletionMaxTries)
            if (!disciplines.has(discipline)) {
                discipline = disciplines.random()
                val places = ctx.getPlaces(discipline)
                val teachers = ctx.getTeachers(discipline)
                if (!places.isEmpty() && !teachers.isEmpty()) {
                    if (random || !places.has(place))
                        place = places.random()
                    if (random || !teachers.has(teacher))
                        teacher = teachers.random()
                    return true
                }
            }
        return false
    }
}
