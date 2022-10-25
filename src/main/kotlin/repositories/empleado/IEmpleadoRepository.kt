package repositories.empleado

import models.Empleado
import repositories.ICrudRepository
import java.util.*

interface IEmpleadoRepository : ICrudRepository<Empleado, UUID> {
    fun getEmpleadosByDepartamento(id_departamento: UUID): List<Empleado>
}