package com.example.pasteleriayy.modelos

data class UsuarioModelo(
    val id: Long? = null,
    val nombre: String,
    val correo: String,
    val contrasena: String,
    val telefono: String? = null,
    val fotoUrl: String? = null
)