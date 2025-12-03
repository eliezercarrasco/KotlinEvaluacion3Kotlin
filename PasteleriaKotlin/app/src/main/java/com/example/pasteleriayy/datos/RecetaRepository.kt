package com.example.pasteleriayy.datos

import com.example.pasteleriayy.modelos.RecetaResponse

class RecetaRepository {

    private val api = RetrofitRecetaClient.instance.create(ApiReceta::class.java)

    suspend fun buscar(nombre: String): RecetaResponse {
        return api.buscarRecetas(nombre)
    }
}
