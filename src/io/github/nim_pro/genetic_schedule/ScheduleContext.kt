package io.github.nim_pro.genetic_schedule

data class ScheduleContext(
        private val groups: Set<Group>,
        private val disciplines: Set<Discipline>,
        private val places: Set<Place>,
        private val teachers: Set<Teacher>
) : ExersiseContext {
    override fun getGroups(): Set<Group> = groups

    override fun getDisciplines(g: Group?): Set<Discipline> {
        if (g == null)
            return disciplines
        return disciplines.filter { g.disciplines.has(it.id) }
    }

    override fun getPlaces(d: Discipline?): Set<Place> {
        if (d == null)
            return places
        return places.filter { d.places.has(it.id) }
    }

    override fun getTeachers(d: Discipline?): Set<Teacher> {
        if (d == null)
            return teachers
        return teachers.filter { d.teachers.has(it.id) }
    }
}
