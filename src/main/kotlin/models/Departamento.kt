package models

import java.util.*

data class Departamento(
    val id: UUID,
    val nombre: String,
    val presupuesto: Long
)