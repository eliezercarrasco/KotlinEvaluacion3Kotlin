package com.example.pasteleriayy.Pantallas

import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriayy.componentes.ProductoItem
import com.example.pasteleriayy.vistaModelo.NovedadesVistaModelo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductosScreen(
    navController: NavController,
    novedadesViewModel: NovedadesVistaModelo = viewModel()
) {

    val productos = novedadesViewModel.productos

    val backgroundColor = Color(0xFFFFC9C9)       // Rosa pastel
    val chocolateColor = Color(0xFF5D4037)        // Color chocolate

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {

        Text(
            text = "Nuestros Productos",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp, bottom = 20.dp),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = chocolateColor,
            textAlign = TextAlign.Center,
            lineHeight = 36.sp
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            items(productos.size) { index ->
                val producto = productos[index]
                ProductoItem(
                    producto = producto,
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                )
            }
        }
    }
}
