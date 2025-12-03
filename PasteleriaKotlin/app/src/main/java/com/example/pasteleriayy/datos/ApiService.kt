package com.example.pasteleriayy.datos

import com.example.pasteleriayy.modelos.UsuarioModelo
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // Crear usuario (POST)
    @POST("api/registro")
    suspend fun registrarUsuario(@Body usuario: UsuarioModelo): Response<UsuarioModelo>

    // Listar todos (GET)
    @GET("api/registro")
    suspend fun listarUsuarios(): List<UsuarioModelo>

    // Obtener por id (GET)
    @GET("api/registro/{id}")
    suspend fun obtenerUsuario(@Path("id") id: Long): UsuarioModelo

    // Actualizar (PUT)
    @PUT("api/registro/{id}")
    suspend fun actualizarUsuario(
        @Path("id") id: Long,
        @Body usuario: UsuarioModelo
    ): Response<UsuarioModelo>

    // Eliminar (DELETE)
    @DELETE("api/registro/{id}")
    suspend fun eliminarUsuario(@Path("id") id: Long): Response<Void>
}
