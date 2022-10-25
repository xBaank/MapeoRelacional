package models

import java.util.*

data class Empleado(
    val id: UUID,
    val nombre: String,
    val id_departamento: UUID
)