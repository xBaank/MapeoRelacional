package repositories

import models.Empleado
import java.util.UUID

interface IEmpleadoRepository : ICrudRepository<Empleado,UUID>