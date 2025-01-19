package ec.edu.uti.juego.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import ec.uti.edu.myapplication.obj.Sala

@Database(entities = [Sala::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun salaDao(): SalaDao
}




