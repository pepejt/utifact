package ec.uti.edu.utifact.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ec.uti.edu.utifact.obj.Cliente

@Dao
interface ClienteDao {

    @Query("SELECT * FROM cliente")
    fun getAll(): List<Cliente>

    @Query("SELECT * FROM cliente WHERE id IN (:clienteIds)")
    fun loadAllByIds(clienteIds: IntArray): List<Cliente>

    @Query("SELECT * FROM cliente WHERE nombre LIKE :first LIMIT 1")
    fun findByName(first: String): Cliente

    @Query("SELECT * FROM cliente WHERE cedula LIKE :first LIMIT 1")
    fun findByCedula(first: String): Cliente

    @Insert
    fun insertAll(vararg salas: Cliente)

    @Delete
    fun delete(cliente : Cliente)

    @Update
    fun update(cliente : Cliente)

}
