package ec.uti.edu.utifact.obj

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class Cliente(
    @PrimaryKey var id : Int,
    @ColumnInfo(name = "Nombre") var nombre : String,
    @ColumnInfo(name = "Apellido") var apellido : String,
    @ColumnInfo(name = "Fecha") var fecha : String,
    @ColumnInfo(name = "Ciudad") var ciudad : String,
    @ColumnInfo(name = "Correo") var correo : String,
    @ColumnInfo(name = "Telefono") var telefono : String,
    @ColumnInfo(name = "Direccion") var direccion : String,
    @ColumnInfo(name = "Cedula") var cedula : String,
){}


