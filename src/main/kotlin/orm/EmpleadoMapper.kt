package orm

import dto.DepartamentoDto
import dto.EmpleadoDto
import models.Empleado
import repositories.departamento.IDepartamentoRepository
import repositories.empleado.IEmpleadoRepository
import java.util.*

class EmpleadoMapper(
    private val repository: IEmpleadoRepository<Empleado>,
    private val departamentoMapper: IDepartamentoRepository<DepartamentoDto, EmpleadoDto>
) : IEmpleadoRepository<EmpleadoDto> {
    override fun getEmpleadosByDepartamento(id_departamento: UUID): List<EmpleadoDto> {
        return repository.getEmpleadosByDepartamento(id_departamento).map {
            EmpleadoDto(
                it.id,
                it.nombre,
                departamentoMapper.findById(it.id_departamento)
            )
        }
    }

    override fun findAll(): List<EmpleadoDto> = repository.findAll().map {
        EmpleadoDto(
            it.id,
            it.nombre,
            departamentoMapper.findById(it.id_departamento)
        )
    }

    override fun findById(id: UUID): EmpleadoDto? = repository.findById(id)?.let {
        EmpleadoDto(
            it.id,
            it.nombre,
            departamentoMapper.findById(it.id_departamento)
        )
    }

    override fun deleteById(id: UUID) = repository.deleteById(id)

    override fun insert(item: EmpleadoDto) = repository.insert(
        Empleado(
            item.id,
            item.nombre,
            item.departamentoDto?.id ?: throw Exception("No se puede insertar un empleado sin departamento")
        )
    )

    override fun update(item: EmpleadoDto) = repository.update(
        Empleado(
            item.id,
            item.nombre,
            item.departamentoDto?.id ?: throw Exception("No se puede actualizar el empleado sin departamento")
        )
    )
}