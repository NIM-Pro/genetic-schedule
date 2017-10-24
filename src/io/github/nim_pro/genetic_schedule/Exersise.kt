package io.github.nim_pro.genetic_schedule

interface ExersiseContext {
    fun getGroups(): Set<Group>
    fun getDisciplines(g: Group): Set<Discipline>
    fun getPlaces(d: Discipline): Set<Place>
    fun getTeachers(d: Discipline): Set<Teacher>
}

data class Exersise (
        var group: Group,
        var discipline: Discipline,
        var place: Place,
        var teacher: Teacher
){
    companion object {
        const val CrossBreedMax: Int = 3
    }
    fun crossBreed(ctx: ExersiseContext, rhs: Exersise): Set<Exersise> {
        val result = Set<Exersise>()
        val groups = Set(listOf(group, rhs.group))
        val disciplines = Set(listOf(discipline, rhs.discipline))
        val places = Set(listOf(place, rhs.place))
        val teachers = Set(listOf(teacher, rhs.teacher))
        val replics = Math.floor(Math.random() * CrossBreedMax) as Int;
        for (i in 0 .. replics) {
            val element = Exersise(
                    groups.random(),
                    disciplines.random(),
                    places.random(),
                    teachers.random()
            )
            element.complete(ctx)
            result.add(element)
        }
        return result
    }
    fun mutate(ctx: ExersiseContext): Option<Exersise> {
        val groups = ctx.getGroups()
        if (groups.isEmpty())
            return Option.none()
        val result = copy(group = groups.random())
        result.complete(ctx, true)
        return Option.some(result)
    }

    private fun complete(ctx: ExersiseContext, random: Boolean = false) {
        val disciplines = ctx.getDisciplines(group);
        var change = false
        while (change || !disciplines.has(discipline)) {
            discipline = disciplines.random()
            val places = ctx.getPlaces(discipline)
            val teachers = ctx.getTeachers(discipline)
            if (places.isEmpty() || teachers.isEmpty())
                change = true
            else {
                if (random || !places.has(place))
                    place = places.random()
                if (random || !teachers.has(teacher))
                teacher = teachers.random()
                return
            }
        }
    }
}
