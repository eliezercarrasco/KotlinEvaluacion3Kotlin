package com.example.pasteleriayy.modelos

import androidx.annotation.DrawableRes

data class ProductoModelo(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    @DrawableRes val imagenResId: Int
)