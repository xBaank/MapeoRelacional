package orm

import dto.DepartamentoDto
import dto.EmpleadoDto
import models.Departamento
import models.Empleado
import repositories.departamento.IDepartamentoRepository
import repositories.empleado.IEmpleadoRepository
import java.util.*

class DepartamentoMapper(
    private val repository: IDepartamentoRepository<Departamento, Empleado>,
    private val empleadoRepository: IEmpleadoRepository<Empleado>
) :
    IDepartamentoRepository<DepartamentoDto, EmpleadoDto> {
    override fun getEmpleadosByDepartamento(id_departamento: UUID): List<EmpleadoDto> {
        return repository.getEmpleadosByDepartamento(id_departamento).map {
            EmpleadoDto(
                it.id,
                it.nombre,
                findById(it.id_departamento)
            )
        }
    }

    override fun findAll(): List<DepartamentoDto> = repository.findAll().map {
        DepartamentoDto(
            it.id,
            it.nombre,
            it.presupuesto,
            getEmpleadosByDepartamento(it.id)
        )
    }

    override fun findById(id: UUID): DepartamentoDto? = repository.findById(id)?.let {
        DepartamentoDto(
            it.id,
            it.nombre,
            it.presupuesto,
            getEmpleadosByDepartamento(it.id)
        )
    }

    override fun deleteById(id: UUID) = repository.deleteById(id)

    override fun insert(item: DepartamentoDto) = repository.insert(
        Departamento(
            item.id,
            item.nombre,
            item.presupuesto
        )
    )

    override fun update(item: DepartamentoDto) = repository.update(
        Departamento(
            item.id,
            item.nombre,
            item.presupuesto
        )
    )
}