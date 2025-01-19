package ec.edu.uti.juego.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import ec.uti.edu.utifact.dao.ClienteDao
import ec.uti.edu.utifact.entity.Configuracion
import ec.uti.edu.utifact.obj.Cliente


@Database(entities = [Configuracion::class, Cliente::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clienteDao(): ClienteDao
    abstract fun configuracionDao(): ConfiguracionDao
}
