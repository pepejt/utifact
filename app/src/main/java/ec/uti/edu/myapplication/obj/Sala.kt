package ec.uti.edu.myapplication.obj

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sala(
    @PrimaryKey var id : Int,
    @ColumnInfo(name = "nombre") var nombre : String,
    @ColumnInfo(name = "apuesta") var apuesta : Int,
    @DrawableRes var icon : Int
){
    constructor():this(0, "", 0,0)
}
