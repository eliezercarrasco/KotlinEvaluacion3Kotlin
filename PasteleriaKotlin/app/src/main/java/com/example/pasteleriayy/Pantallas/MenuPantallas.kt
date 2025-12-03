package com.example.pasteleriayy.Pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriayy.componentes.ProductoItem
import com.example.pasteleriayy.vistaModelo.MenuVistaModelo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuProductosScreen(
    navController: NavController,
    menuProductosViewModel: MenuVistaModelo = viewModel()
) {

    val productos = menuProductosViewModel.productos  // Traemos los productos
    val scrollState = rememberScrollState()           // Estado para el scroll

    val backgroundColor = Color(0xFFFFC9C9)       // Rosa pastel
    val chocolateColor = Color(0xFF5D4037)        // Color chocolate

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)           // Habilita scroll vertical
            .background(backgroundColor)
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {

        Text(
            text = "Bienvenido a Pastelería Mil Sabores",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = chocolateColor,
            textAlign = TextAlign.Center,
            lineHeight = 36.sp
        )

        Text(
            text = "Pastelería Mil Sabores celebra su 50 aniversario como un referente en la repostería chilena. " +
                    "Famosa por su participación en un récord Guinness en 1995, cuando colaboró en la creación de la " +
                    "torta más grande del mundo, la pastelería busca renovar su sistema de ventas online para ofrecer " +
                    "una experiencia de compra moderna y accesible para sus clientes.",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            fontSize = 18.sp,
            color = chocolateColor,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp
        )

        // Lista de productos
        productos.forEach { producto ->
            ProductoItem(
                producto = producto,
                modifier = Modifier
                    .padding(vertical = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
