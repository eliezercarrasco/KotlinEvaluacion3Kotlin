package com.example.pasteleriayy.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap

data class NavItemTop(
    val route: String,
    val label: String
)

private val topNavItems = listOf(
    NavItemTop("menu", "Menú"),
    NavItemTop("contacto", "Contacto"),
    NavItemTop("registro", "Registro")
)

@Composable
fun TopNavMenu(
    navController: NavController,
    currentRoute: String? = null
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE7180B))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Pastelería Mil Sabores",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier.weight(1f)
            )

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clickable { expanded = !expanded }
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val centerX = size.width / 2
                    val centerY = size.height / 2
                    val spacing = 10f
                    val lineLength = size.width * 0.6f
                    val lineColor = Color.White
                    val strokeWidth = 4f

                    if (!expanded) {
                        drawLine(lineColor, Offset(centerX - lineLength / 2, centerY - spacing),
                            Offset(centerX + lineLength / 2, centerY - spacing), strokeWidth, cap = StrokeCap.Round)
                        drawLine(lineColor, Offset(centerX - lineLength / 2, centerY),
                            Offset(centerX + lineLength / 2, centerY), strokeWidth, cap = StrokeCap.Round)
                        drawLine(lineColor, Offset(centerX - lineLength / 2, centerY + spacing),
                            Offset(centerX + lineLength / 2, centerY + spacing), strokeWidth, cap = StrokeCap.Round)
                    } else {
                        drawLine(lineColor,
                            Offset(centerX - lineLength / 2, centerY - lineLength / 2),
                            Offset(centerX + lineLength / 2, centerY + lineLength / 2),
                            strokeWidth, cap = StrokeCap.Round
                        )
                        drawLine(lineColor,
                            Offset(centerX - lineLength / 2, centerY + lineLength / 2),
                            Offset(centerX + lineLength / 2, centerY - lineLength / 2),
                            strokeWidth, cap = StrokeCap.Round
                        )
                    }
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    offset = DpOffset(x = (-120).dp, y = 0.dp), // despliega hacia la izquierda del botón
                    modifier = Modifier
                        .background(Color(0xFFE7180B))
                ) {
                    topNavItems.forEach { item ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    item.label,
                                    color = if (currentRoute == item.route) Color.White else Color.Black,
                                    fontSize = 16.sp
                                )
                            },
                            onClick = {
                                expanded = false
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
