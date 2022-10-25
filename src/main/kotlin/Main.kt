import db.DataBaseManager
import db.createTables
import dto.DepartamentoDto
import dto.EmpleadoDto
import orm.DepartamentoMapper
import orm.EmpleadoMapper
import repositories.departamento.DepartamentoRepository
import repositories.empleado.EmpleadoRepository
import java.util.UUID.randomUUID

fun main(args: Array<String>) {
    DataBaseManager.open()
    DataBaseManager.createTables(createTables())
    DataBaseManager.close()

    val departamentoRepository = DepartamentoMapper(DepartamentoRepository(), EmpleadoRepository())
    val empleadosRepository = EmpleadoMapper(EmpleadoRepository(), departamentoRepository)


    val listaDepartamentos = mutableListOf(
        DepartamentoDto(randomUUID(), "Departamento 1", 1000),
        DepartamentoDto(randomUUID(), "Departamento 2", 2000),
    )

    val listaEmpleados = mutableListOf(
        EmpleadoDto(randomUUID(), "Empleado 1", listaDepartamentos[0]),
        EmpleadoDto(randomUUID(), "Empleado 2", listaDepartamentos[1]),
    )

    //insertar
    listaDepartamentos.forEach { departamentoRepository.insert(it) }
    listaEmpleados.forEach { empleadosRepository.insert(it) }

    //find all
    println("Departamentos: ${departamentoRepository.findAll()}")
    println("Empleados: ${empleadosRepository.findAll()}")
}