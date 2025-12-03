package com.example.pasteleriayy.componentes

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pasteleriayy.modelos.UsuarioModelo
import com.example.pasteleriayy.modelos.ProductoModelo

@Composable
fun ProductoItem(
    producto: ProductoModelo,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    // Colores
    val chocolateColor = Color(0xFF5D4037)
    val tarjetaRoja = Color(0xFFFB2C36)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 10.dp)
            .clickable { expanded = !expanded }
            .animateContentSize(),
        colors = CardDefaults.cardColors(containerColor = tarjetaRoja),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Imagen del producto
            Image(
                painter = painterResource(id = producto.imagenResId),
                contentDescription = producto.nombre,
                contentScale = ContentScale.Crop,
                modifier = if (expanded) Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                else Modifier.size(95.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Nombre del producto
            Text(
                text = producto.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = chocolateColor
            )

            // Precio
            Text(
                text = "$${String.format("%.2f", producto.precio)}",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                color = chocolateColor
            )

            // Descripción expandible
            if (expanded) {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = producto.descripcion,
                    fontSize = 15.sp,
                    color = chocolateColor.copy(alpha = 0.9f)
                )
            }
        }
    }
}
