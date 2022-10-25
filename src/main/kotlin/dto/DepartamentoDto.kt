package dto

import java.util.*

data class DepartamentoDto(
    val id: UUID,
    val nombre: String,
    val presupuesto: Long,
    val empleados: List<EmpleadoDto> = emptyList()
)