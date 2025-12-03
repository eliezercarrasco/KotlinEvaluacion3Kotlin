package com.example.pasteleriayy.vistaModelo

import androidx.lifecycle.ViewModel
import com.example.pasteleriayy.datos.ProductosDataSource
import com.example.pasteleriayy.modelos.ProductoModelo

class MenuVistaModelo : ViewModel() {
    val productos: List<ProductoModelo> = ProductosDataSource.getListaProductos()
}