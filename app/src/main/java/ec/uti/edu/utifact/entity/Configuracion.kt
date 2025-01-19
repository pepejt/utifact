package ec.uti.edu.utifact.entity

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Configuracion(
    @PrimaryKey var id : Int,
    @ColumnInfo(name = "ruc") var ruc : String,
    @ColumnInfo(name = "empresa") var empresa : String,
    @ColumnInfo(name = "punto_emision") var puntoEmision : String,
){
    constructor(id: Int):this(id, "", "","")
}
