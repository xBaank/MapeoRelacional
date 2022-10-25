package repositories.empleado

import db.DataBaseManager
import models.Departamento
import models.Empleado
import java.util.*

class EmpleadoRepository : IEmpleadoRepository {
    override fun getEmpleadosByDepartamento(id_departamento: UUID): List<Empleado> {
        val query = "SELECT * FROM empleado WHERE id_departamento = ?"
        try {
            DataBaseManager.open()
            val result = DataBaseManager.select(query, id_departamento)
            val list = mutableListOf<Empleado>()
            while (result?.next() == true) {
                list.add(
                    Empleado(
                        result.getObject("id") as UUID,
                        result.getString("nombre"),
                        result.getObject("id_departamento") as UUID
                    )
                )
            }
            return list
        } finally {
            DataBaseManager.close()
        }
    }

    override fun findAll(): List<Empleado> {
        DataBaseManager.open()
        val query = "Select * from Empleado"

        val list = mutableListOf<Empleado>()
        try {
            val result =
                DataBaseManager.select(query) ?: throw IllegalStateException("El select no puede devolver nulo")
            while (result.next()) {
                list.add(
                    Empleado(
                        result.getObject("id") as UUID,
                        result.getString("nombre"),
                        result.getObject("id_departamento") as UUID?
                    )
                )
            }

        } finally {
            DataBaseManager.close()
        }
        return list
    }

    override fun findById(id: UUID): Empleado? {
        //find by id
        DataBaseManager.open()
        val query = "Select * from Empleado where id = ?"
        var empleado: Empleado? = null
        try {
            val result =
                DataBaseManager.select(query, id) ?: throw IllegalStateException("El select no puede devolver nulo")
            while (result.next()) {
                empleado = Empleado(
                    result.getObject("id") as UUID,
                    result.getString("nombre"),
                    result.getObject("id_departamento") as UUID
                )
            }

        } finally {
            DataBaseManager.close()
        }
        return empleado
    }

    override fun update(item: Empleado) {
        //update empleado
        val query = "Update Empleado set nombre = ?, id_departamento = ? where id = ?"
        try {
            DataBaseManager.open()
            DataBaseManager.update(query, item.nombre, item.id_departamento, item.id)
        } finally {
            DataBaseManager.close()
        }
    }

    override fun deleteById(id: UUID) {
        //delete empleado by id
        val query = "Delete from Empleado where id = ?"
        try {
            DataBaseManager.open()
            DataBaseManager.delete(query, id)
        } finally {
            DataBaseManager.close()
        }
    }

    override fun insert(item: Empleado) {
        //insert empleado
        val query = "Insert into Empleado (id, nombre, id_departamento) values (?,?,?)"
        try {
            DataBaseManager.open()
            DataBaseManager.insert(query, item.id, item.nombre, item.id_departamento)
        } finally {
            DataBaseManager.close()
        }
    }

    private fun getDepartamento(id: UUID): Departamento {
        val departamentoQuery = "Select * from Departamento where id = ?"
        val departamentoResult = DataBaseManager.select(departamentoQuery, id) ?: throw IllegalStateException()

        return Departamento(
            departamentoResult.getObject("id") as UUID,
            departamentoResult.getString("nombre"),
            departamentoResult.getLong("presupuesto")
        )
    }
}