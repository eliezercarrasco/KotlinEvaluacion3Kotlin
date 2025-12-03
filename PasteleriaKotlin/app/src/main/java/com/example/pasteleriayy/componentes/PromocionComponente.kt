package com.example.pasteleriayy.componentes

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import com.example.pasteleriayy.modelos.PromocionModelo
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun PromocionItem(
    promocion: PromocionModelo,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    // Colores estilo pastel
    val chocolateColor = Color(0xFF5D4037)     // marrón chocolate
    val tarjetaRoja = Color(0xFFFB2C36)        // rojo pastel solicitado

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .clickable { expanded = !expanded }
            .animateContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = tarjetaRoja
        ),
        shape = RoundedCornerShape(12.dp) // estilo más bonito
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = promocion.imagenResId),
                contentDescription = promocion.nombre,
                contentScale = ContentScale.Crop,
                modifier = if (expanded) Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                else Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Nombre del producto
            Text(
                text = promocion.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = chocolateColor
            )

            // Precio
            Text(
                text = "Precio: ${String.format("$%.2f", promocion.precio)}",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = chocolateColor
            )

            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))

                // Descripción
                Text(
                    text = promocion.descripcion,
                    fontSize = 15.sp,
                    color = chocolateColor
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Fecha de vencimiento
                Text(
                    text = "Vence: ${promocion.fechaFin.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))}",
                    fontSize = 14.sp,
                    color = chocolateColor.copy(alpha = 0.85f)
                )
            }
        }
    }
}
