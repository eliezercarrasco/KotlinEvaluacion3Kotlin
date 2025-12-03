package com.example.pasteleriayy.datos

import com.example.pasteleriayy.modelos.UsuarioModelo

class UsuarioRepository {

    private val api = RetrofitClient.instance.create(ApiService::class.java)

    suspend fun listar(): List<UsuarioModelo> = api.listarUsuarios()

    suspend fun obtener(id: Long): UsuarioModelo = api.obtenerUsuario(id)

    suspend fun crear(usuario: UsuarioModelo) = api.registrarUsuario(usuario)

    suspend fun actualizar(id: Long, usuario: UsuarioModelo) =
        api.actualizarUsuario(id, usuario)

    suspend fun eliminar(id: Long) = api.eliminarUsuario(id)
}