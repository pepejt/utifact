package ec.uti.edu.utifact.product.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int = 0,
    val name: String,
    val quantity: Int,
    val price: Double,
    val additionalInfo: String? = null
) : Parcelable