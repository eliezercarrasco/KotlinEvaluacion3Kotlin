package com.example.pasteleriayy.vistaModelo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleriayy.datos.RecetaRepository
import com.example.pasteleriayy.modelos.Receta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductoVistaModelo : ViewModel() {

    private val repo = RecetaRepository()

    private val _recetas = MutableStateFlow<List<Receta>>(emptyList())
    val recetas: StateFlow<List<Receta>> = _recetas

    fun buscar(nombre: String) {
        viewModelScope.launch {
            try {
                val res = repo.buscar(nombre)
                _recetas.value = res.meals ?: emptyList()
            } catch (e: Exception) {
                _recetas.value = emptyList()
            }
        }
    }
}
