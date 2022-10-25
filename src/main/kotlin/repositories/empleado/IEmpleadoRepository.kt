package repositories.empleado

import repositories.ICrudRepository
import java.util.*

interface IEmpleadoRepository<T> : ICrudRepository<T, UUID> {
    fun getEmpleadosByDepartamento(id_departamento: UUID): List<T>
}