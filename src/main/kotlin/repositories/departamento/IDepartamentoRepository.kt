package repositories.departamento

import repositories.ICrudRepository
import java.util.*

interface IDepartamentoRepository<T, E> : ICrudRepository<T, UUID> {
    fun getEmpleadosByDepartamento(id_departamento: UUID): List<E>
}