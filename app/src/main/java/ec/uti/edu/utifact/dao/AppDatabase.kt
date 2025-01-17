package ec.uti.edu.utifact.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import ec.uti.edu.utifact.obj.Cliente


@Database(entities = [Cliente::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun clienteDao(): ClienteDao
}
