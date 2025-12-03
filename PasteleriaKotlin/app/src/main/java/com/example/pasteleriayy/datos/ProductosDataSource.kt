package com.example.pasteleriayy.datos

import com.example.pasteleriayy.R
import com.example.pasteleriayy.modelos.ProductoModelo

object ProductosDataSource {

    fun getListaProductos(): List<ProductoModelo> = listOf(
        // Productos originales
        ProductoModelo("TC001", "Torta Cuadrada de Chocolate",
            "Torta Cuadrada de Chocolate: Deliciosa torta de chocolate con capas de ganache y un toque de avellanas. Personalizable con mensajes especiales.",
            45000.0, R.drawable.tc001),
        ProductoModelo("TC002", "Torta Cuadrada de Frutas",
            "Torta Cuadrada de Frutas: Una mezcla de frutas frescas y crema chantilly sobre un suave bizcocho de vainilla, ideal para celebraciones.",
            50000.0, R.drawable.tc002),
        ProductoModelo("TT001", "Torta Circular de Vainilla",
            "Torta Circular de Vainilla: Bizcocho de vainilla clásico relleno con crema pastelera y cubierto con un glaseado dulce, perfecto para cualquier ocasión.",
            40000.0, R.drawable.tt001),
        ProductoModelo("TT002", "Torta Circular de Manjar",
            "Torta Circular de Manjar: Torta tradicional chilena con manjar y nueces, un deleite para los amantes de los sabores dulces y clásicos.",
            42000.0, R.drawable.tt002),
        ProductoModelo("PI001", "Mousse de Chocolate",
            "Mousse de Chocolate: Postre individual cremoso y suave, hecho con chocolate de alta calidad, ideal para los amantes del chocolate.",
            5000.0, R.drawable.pi001),
        ProductoModelo("PI002", "Tiramisú Clásico",
            "Tiramisú Clásico: Un postre italiano individual con capas de café, mascarpone y cacao, perfecto para finalizar cualquier comida.",
            5500.0, R.drawable.pi002),
        ProductoModelo("PSA001", "Torta Sin Azúcar de Naranja",
            "Torta Sin Azúcar de Naranja: Torta ligera y deliciosa, endulzada naturalmente, ideal para quienes buscan opciones más saludables.",
            48000.0, R.drawable.psa001),
        ProductoModelo("PSA002", "Cheesecake Sin Azúcar",
            "Cheesecake Sin Azúcar: Suave y cremoso, este cheesecake es una opción perfecta para disfrutar sin culpa.",
            47000.0, R.drawable.psa002),

        // Productos adicionales del segundo archivo
        ProductoModelo("PT001", "Empanada de Manzana",
            "Empanada de Manzana: Pastelería tradicional rellena de manzanas especiadas, perfecta para un dulce desayuno o merienda.",
            3000.0, R.drawable.pt001),
        ProductoModelo("PT002", "Tarta de Santiago",
            "Tarta de Santiago: Pastelería tradicional rellena de manzanas especiadas, perfecta para un dulce desayuno o merienda.",
            6000.0, R.drawable.pt002),
        ProductoModelo("PG001", "Brownie Sin Gluten",
            "Brownie Sin Gluten: Rico y denso, este brownie es perfecto para quienes necesitan evitar el gluten sin sacrificar el sabor.",
            4000.0, R.drawable.pg001),
        ProductoModelo("PG002", "Pan Sin Gluten",
            "Pan Sin Gluten: Suave y esponjoso, ideal para sándwiches o para acompañar cualquier comida.",
            3500.0, R.drawable.pg002),
        ProductoModelo("PV001", "Torta Vegana de Chocolate",
            "Torta Vegana de Chocolate: Torta de chocolate húmeda y deliciosa, hecha sin productos de origen animal, perfecta para veganos.",
            50000.0, R.drawable.pv001),
        ProductoModelo("PV002", "Galletas Veganas de Avena",
            "Galletas Veganas de Avena: Crujientes y sabrosas, estas galletas son una excelente opción para un snack saludable y vegano.",
            4500.0, R.drawable.pv002),
        ProductoModelo("TE001", "Torta Especial de Cumpleaños",
            "Torta Especial de Cumpleaños: Diseñada especialmente para celebraciones, personalizable con decoraciones y mensajes únicos.",
            55000.0, R.drawable.te001),
        ProductoModelo("TE002", "Torta Especial de Boda",
            "Torta Especial de Boda: Elegante y deliciosa, esta torta está diseñada para ser el centro de atención en cualquier boda.",
            60000.0, R.drawable.te002)
    )
}
