package com.example.pasteleriayy.datos

import com.example.pasteleriayy.modelos.RecetaResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiReceta {

    @GET("search.php")
    suspend fun buscarRecetas(@Query("s") nombre: String): RecetaResponse
}
