package db

fun createTables() = """
    CREATE TABLE IF NOT EXISTS Departamento (
        id UUID PRIMARY KEY,
        nombre VARCHAR(255) NOT NULL,
        presupuesto BIGINT NOT NULL
    );

    CREATE TABLE IF NOT EXISTS Empleado (
        id UUID PRIMARY KEY,
        nombre VARCHAR(255) NOT NULL,
        id_departamento UUID,
        CONSTRAINT FK_ID_DEPARTAMENTO foreign key (id_departamento) references Departamento(id)
    )
    """.trimIndent()

//Cambiar id_departamento a nulo?