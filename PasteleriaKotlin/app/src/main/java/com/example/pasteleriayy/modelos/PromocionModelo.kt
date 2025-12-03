package com.example.pasteleriayy.modelos

import androidx.annotation.DrawableRes
import java.time.LocalDate

data class PromocionModelo(
    val id:String,
    val nombre: String,
    val descripcion: String,
    val fechaFin: LocalDate,
    val precio: Double,
    @DrawableRes val imagenResId: Int
)