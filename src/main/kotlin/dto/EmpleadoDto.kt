package dto

import java.util.*

data class EmpleadoDto(
    val id: UUID,
    val nombre: String,
    val departamentoDto: DepartamentoDto?
)
