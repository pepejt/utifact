package ec.edu.uti.juego.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ec.uti.edu.utifact.entity.Configuracion

@Dao
interface ConfiguracionDao {

    @Query("SELECT * FROM configuracion WHERE id = :id")
    fun getById(id: Int): Configuracion

    @Update
    fun update(configuracion: Configuracion)

    @Insert
    fun insert(configuracion: Configuracion)
}