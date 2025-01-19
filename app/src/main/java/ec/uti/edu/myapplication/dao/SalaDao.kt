package ec.edu.uti.juego.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ec.uti.edu.myapplication.obj.Sala

@Dao
interface SalaDao {

    @Query("SELECT * FROM sala")
    fun getAll(): List<Sala>

    @Query("SELECT * FROM sala WHERE id IN (:salaIds)")
    fun loadAllByIds(salaIds: IntArray): List<Sala>

    @Query("SELECT * FROM sala WHERE nombre LIKE :first LIMIT 1")
    fun findByName(first: String): Sala

    @Insert
    fun insertAll(vararg salas: Sala)

    @Delete
    fun delete(sala : Sala)
}