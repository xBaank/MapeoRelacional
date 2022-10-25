import db.DataBaseManager
import db.createTables
import models.Departamento
import models.Empleado
import repositories.departamento.DepartamentoRepository
import repositories.empleado.EmpleadoRepository
import java.util.UUID.randomUUID

fun main(args: Array<String>) {
    DataBaseManager.open()
    DataBaseManager.createTables(createTables())
    DataBaseManager.close()

    val departamentoRepository = DepartamentoRepository()
    val empleadosRepository = EmpleadoRepository()


    val listaDepartamentos = mutableListOf(
        Departamento(randomUUID(), "Departamento 1", 1000),
        Departamento(randomUUID(), "Departamento 2", 2000),
    )

    val listaEmpleados = mutableListOf(
        Empleado(randomUUID(), "Empleado 1", listaDepartamentos[0].id),
        Empleado(randomUUID(), "Empleado 2", listaDepartamentos[1].id),
        Empleado(randomUUID(), "Empleado 3", null),
    )

    //insertar
    listaDepartamentos.forEach { departamentoRepository.insert(it) }
    listaEmpleados.forEach { empleadosRepository.insert(it) }

    //find all
    println("Departamentos: ${departamentoRepository.findAll()}")
    println("Empleados: ${empleadosRepository.findAll()}")
}