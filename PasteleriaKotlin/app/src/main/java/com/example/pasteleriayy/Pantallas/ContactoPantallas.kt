package com.example.pasteleriayy.Pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.pasteleriayy.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactoScreen(navController: NavController, modifier: Modifier = Modifier) {

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }

    var nombreError by remember { mutableStateOf("") }
    var correoError by remember { mutableStateOf("") }
    var telefonoError by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    var mensajeExito by remember { mutableStateOf("") }

    val backgroundColor = Color(0xFFFFC9C9)
    val chocolateColor = Color(0xFF5D4037)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Contacto", color = chocolateColor, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = chocolateColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor
                )
            )
        },
        containerColor = backgroundColor
    ) { padding ->

        Column(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
                .background(backgroundColor)
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(140.dp)
                    .padding(top = 16.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Formulario de Contacto",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = chocolateColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomTextField(
                value = nombre,
                onValueChange = { nombre = it; nombreError = "" },
                label = "Nombre",
                isError = nombreError.isNotEmpty()
            )
            if (nombreError.isNotEmpty()) {
                Text(nombreError, color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = correo,
                onValueChange = { correo = it; correoError = "" },
                label = "Correo",
                isError = correoError.isNotEmpty()
            )
            if (correoError.isNotEmpty()) {
                Text(correoError, color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = telefono,
                onValueChange = { telefono = it; telefonoError = "" },
                label = "Teléfono",
                isError = telefonoError.isNotEmpty()
            )
            if (telefonoError.isNotEmpty()) {
                Text(telefonoError, color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            CustomTextField(
                value = mensaje,
                onValueChange = { mensaje = it; mensajeError = "" },
                label = "Mensaje",
                isError = mensajeError.isNotEmpty(),
                singleLine = false,
                height = 120.dp
            )
            if (mensajeError.isNotEmpty()) {
                Text(mensajeError, color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ⭐ BOTÓN NEGRO
            Button(
                onClick = {
                    nombreError = if (nombre.isBlank()) "El nombre no puede estar vacío" else ""
                    correoError =
                        if (correo.isBlank()) "El correo no puede estar vacío"
                        else if (!correo.contains("@")) "Correo inválido" else ""
                    telefonoError =
                        if (telefono.isBlank()) "El teléfono no puede estar vacío" else ""
                    mensajeError =
                        if (mensaje.isBlank()) "Debe escribir un mensaje" else ""

                    if (
                        nombreError.isEmpty() &&
                        correoError.isEmpty() &&
                        telefonoError.isEmpty() &&
                        mensajeError.isEmpty()
                    ) {
                        mensajeExito = "Mensaje enviado correctamente 🎉"
                        nombre = ""
                        correo = ""
                        telefono = ""
                        mensaje = ""
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black    // ⭐ Aquí se cambia a negro
                ),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text(
                    "Enviar",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            if (mensajeExito.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    mensajeExito,
                    color = Color(0xFF388E3C),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean = false,
    singleLine: Boolean = true,
    height: Dp = 56.dp
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        singleLine = singleLine,
        isError = isError,
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
    )
}
