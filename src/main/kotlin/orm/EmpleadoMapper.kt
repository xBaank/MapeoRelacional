package orm

import dto.EmpleadoDto
import repositories.empleado.IEmpleadoRepository
import java.util.*

class EmpleladoMapper(
    private val repository: IEmpleadoRepository<EmpleadoDto>
) : IEmpleadoRepository<EmpleadoDto> {
    override fun getEmpleadosByDepartamento(id_departamento: UUID): List<EmpleadoDto> {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<EmpleadoDto> {
        TODO("Not yet implemented")
    }

    override fun findById(id: UUID): EmpleadoDto? {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: UUID) {
        TODO("Not yet implemented")
    }

    override fun insert(item: EmpleadoDto) {
        TODO("Not yet implemented")
    }

    override fun update(item: EmpleadoDto) {
        TODO("Not yet implemented")
    }
}