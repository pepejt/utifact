package ec.edu.uti.juego.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import ec.uti.edu.utifact.entity.Configuracion

@Database(entities = [Configuracion::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ConfiguracionDao(): ConfiguracionDao
}




