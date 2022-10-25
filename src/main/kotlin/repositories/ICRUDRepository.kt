package repositories

// Vamos a simular
// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
interface ICrudRepository<T, ID> {
    fun findAll(): List<T> // List<T> es una lista de T
    fun findById(id: ID): T?
    fun update(item: T)
    fun deleteById(id: ID)
    fun insert(item: T)
}