package com.example.pasteleriayy.Pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pasteleriayy.modelos.UsuarioModelo
import com.example.pasteleriayy.vistaModelo.RegistroVistaModelo
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCrud(
    navController: NavController,
    viewModel: RegistroVistaModelo = viewModel()
) {
    val usuarios by viewModel.usuarios.collectAsState()
    val mensaje by viewModel.mensaje.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarUsuarios()
    }

    val backgroundColor = Color(0xFFFFC9C9)
    val textColor = Color.Black
    val cardColor = Color(0xFFFFF085)
    val buttonColor = Color.Black

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar Usuarios", color = textColor) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = textColor
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
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(backgroundColor)
                .padding(16.dp)
        ) {

            if (mensaje.isNotEmpty()) {
                Text(
                    text = mensaje,
                    color = if (mensaje.contains("exito", true)) Color(0xFF2E7D32) else Color.Red
                )
                Spacer(Modifier.height(8.dp))
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(usuarios) { usuario ->
                    UsuarioCard(
                        usuario = usuario,
                        onEliminar = { id -> viewModel.eliminar(id) },
                        onEditar = { id -> navController.navigate("editar_usuario/$id") },
                        cardColor = cardColor,
                        buttonColor = buttonColor,
                        textColor = textColor
                    )
                }
            }
        }
    }
}

@Composable
fun UsuarioCard(
    usuario: UsuarioModelo,
    onEliminar: (Long) -> Unit,
    onEditar: (Long) -> Unit,
    cardColor: Color,
    buttonColor: Color,
    textColor: Color
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .size(120.dp)
            ) {
                if (!usuario.fotoUrl.isNullOrEmpty()) {
                    Image(
                        painter = rememberAsyncImagePainter(usuario.fotoUrl),
                        contentDescription = "Foto usuario",
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black, RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text(
                        "Sin foto",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.DarkGray
                    )
                }
            }

            Spacer(Modifier.height(12.dp))

            Text("ID: ${usuario.id}", color = textColor)
            Text("Nombre: ${usuario.nombre}", color = textColor)
            Text("Correo: ${usuario.correo}", color = textColor)
            Text("Teléfono: ${usuario.telefono ?: "No registrado"}", color = textColor)

            Spacer(Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { onEditar(usuario.id!!) },
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Editar", color = Color.White)
                }

                Button(
                    onClick = { onEliminar(usuario.id!!) },
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Eliminar", color = Color.White)
                }
            }
        }
    }
}
