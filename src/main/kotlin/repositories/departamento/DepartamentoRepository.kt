package repositories.departamento

import db.DataBaseManager
import models.Departamento
import java.util.*

class DepartamentoRepository : IDepartamentoRepository {

    override fun findAll(): List<Departamento> {
        val query = "Select * from Departamento"
        val list = mutableListOf<Departamento>()
        try {
            DataBaseManager.open()
            val result =
                DataBaseManager.select(query) ?: throw IllegalStateException("El select no puede devolver nulo")
            while (result.next()) {
                list.add(
                    Departamento(
                        result.getObject("id") as UUID,
                        result.getString("nombre"),
                        result.getLong("presupuesto")
                    )
                )
            }

        } finally {
            DataBaseManager.close()
        }
        return list
    }

    override fun findById(id: UUID): Departamento? {
        //find by id
        val query = "Select * from Departamento where id = ?"
        var departamento: Departamento? = null
        try {
            DataBaseManager.open()
            val result =
                DataBaseManager.select(query, id) ?: throw IllegalStateException("El select no puede devolver nulo")
            while (result.next()) {
                departamento = Departamento(
                    result.getObject("id") as UUID,
                    result.getString("nombre"),
                    result.getLong("presupuesto")
                )
            }

        } finally {
            DataBaseManager.close()
        }
        return departamento
    }

    override fun update(item: Departamento) {
        //update departamento
        val query = "Update Departamento set nombre = ?, presupuesto = ? where id = ?"
        try {
            DataBaseManager.open()
            DataBaseManager.update(query, item.nombre, item.presupuesto, item.id)
        } finally {
            DataBaseManager.close()
        }
    }

    override fun deleteById(id: UUID) {
        //delete by id
        val query = "Delete from Departamento where id = ?"
        try {
            DataBaseManager.open()
            DataBaseManager.update(query, id)
        } finally {
            DataBaseManager.close()
        }
    }

    override fun insert(item: Departamento) {
        val query = "Insert into Departamento (id, nombre, presupuesto) values (?,?,?)"

        try {
            DataBaseManager.open()
            DataBaseManager.insert(query, item.id, item.nombre, item.presupuesto)
        } finally {
            DataBaseManager.close()
        }
    }
}