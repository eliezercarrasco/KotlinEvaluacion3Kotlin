package com.example.pasteleriayy.Pantallas

import androidx.compose.foundation.border
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.pasteleriayy.modelos.UsuarioModelo
import com.example.pasteleriayy.vistaModelo.RegistroVistaModelo
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioValidacion(navController: NavController, modifier: Modifier = Modifier) {

    val viewModel: RegistroVistaModelo = viewModel()
    val mensaje by viewModel.mensaje.collectAsState()
    var mostrarMenu by remember { mutableStateOf(false) }

    val context = LocalContext.current
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val permisoCamaraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { }

    val takePictureLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success -> if (!success) imageUri = null }

    fun createImageUri(): Uri {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val file = File(context.cacheDir, "IMG_$timeStamp.jpg")
        file.createNewFile()
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            file
        )
    }

    val backgroundColor = Color(0xFFFFC9C9)
    val chocolateColor = Color(0xFF5D4037)
    val buttonColor = Color.Black
    val imageBorderColor = Color(0xFFE7180B) // 🔴 Rojo del recuadro

    val coloresCampos = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = chocolateColor,
        unfocusedBorderColor = chocolateColor,
        cursorColor = chocolateColor,
        focusedLabelColor = chocolateColor,
        unfocusedLabelColor = chocolateColor,
        errorBorderColor = Color.Red,
        errorLabelColor = Color.Red
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro de Usuario", color = chocolateColor, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = chocolateColor)
                    }
                },
                actions = {
                    IconButton(onClick = { mostrarMenu = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Menú", tint = chocolateColor)
                    }
                    DropdownMenu(
                        expanded = mostrarMenu,
                        onDismissRequest = { mostrarMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Administrar Usuarios") },
                            onClick = {
                                mostrarMenu = false
                                navController.navigate("crud_usuarios")
                            }
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

        var nombre by remember { mutableStateOf("") }
        var correo by remember { mutableStateOf("") }
        var contrasena by remember { mutableStateOf("") }
        var repetirContrasena by remember { mutableStateOf("") }
        var telefono by remember { mutableStateOf("") }

        var nombreError by remember { mutableStateOf("") }
        var correoError by remember { mutableStateOf("") }
        var contrasenaError by remember { mutableStateOf("") }
        var repetirContrasenaError by remember { mutableStateOf("") }

        Column(
            modifier = modifier
                .padding(padding)
                .fillMaxSize()
                .background(backgroundColor)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()), // 🔹 Scroll vertical
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            // ---------------- FOTO ----------------
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .border(BorderStroke(3.dp, imageBorderColor), RoundedCornerShape(12.dp)) // 🔴 borde rojo
                    .clickable {
                        val permiso = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                        if (permiso != PackageManager.PERMISSION_GRANTED) {
                            permisoCamaraLauncher.launch(Manifest.permission.CAMERA)
                            return@clickable
                        }
                        val uri = createImageUri()
                        imageUri = uri
                        context.grantUriPermission(
                            "com.android.camera",
                            uri,
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
                        )
                        takePictureLauncher.launch(uri)
                    },
                contentAlignment = Alignment.Center
            ) {
                if (imageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = "Foto",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    IconButton(
                        onClick = { imageUri = null },
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Eliminar", tint = Color.White)
                    }
                } else {
                    Text("Tomar Foto", color = chocolateColor, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // CAMPOS DE TEXTO
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it; nombreError = "" },
                label = { Text("Nombre") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = chocolateColor) },
                isError = nombreError.isNotEmpty(),
                colors = coloresCampos,
                modifier = Modifier.fillMaxWidth()
            )
            if (nombreError.isNotEmpty()) Text(nombreError, color = Color.Red)

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it; correoError = "" },
                label = { Text("Correo electrónico") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = chocolateColor) },
                isError = correoError.isNotEmpty(),
                colors = coloresCampos,
                modifier = Modifier.fillMaxWidth()
            )
            if (correoError.isNotEmpty()) Text(correoError, color = Color.Red)

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = contrasena,
                onValueChange = { contrasena = it; contrasenaError = "" },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = chocolateColor) },
                isError = contrasenaError.isNotEmpty(),
                colors = coloresCampos,
                modifier = Modifier.fillMaxWidth()
            )
            if (contrasenaError.isNotEmpty()) Text(contrasenaError, color = Color.Red)

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = repetirContrasena,
                onValueChange = { repetirContrasena = it; repetirContrasenaError = "" },
                label = { Text("Repetir contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = chocolateColor) },
                isError = repetirContrasenaError.isNotEmpty(),
                colors = coloresCampos,
                modifier = Modifier.fillMaxWidth()
            )
            if (repetirContrasenaError.isNotEmpty()) Text(repetirContrasenaError, color = Color.Red)

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Teléfono (opcional)") },
                colors = coloresCampos,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            // BOTÓN NEGRO
            Button(
                onClick = {
                    nombreError = if (nombre.isBlank()) "Ingrese un nombre" else ""
                    correoError = when {
                        correo.isBlank() -> "Ingrese un correo"
                        !correo.contains("@") -> "Correo inválido"
                        else -> ""
                    }
                    contrasenaError = if (contrasena.length < 6) "Mínimo 6 caracteres" else ""
                    repetirContrasenaError =
                        if (repetirContrasena != contrasena) "Las contraseñas no coinciden" else ""

                    if (
                        nombreError.isEmpty() &&
                        correoError.isEmpty() &&
                        contrasenaError.isEmpty() &&
                        repetirContrasenaError.isEmpty()
                    ) {
                        val usuario = UsuarioModelo(
                            id = null,
                            nombre = nombre,
                            correo = correo,
                            contrasena = contrasena,
                            telefono = if (telefono.isNotBlank()) telefono else null,
                            fotoUrl = imageUri?.toString()
                        )
                        viewModel.registrarUsuario(usuario)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Registrar Usuario", fontSize = 18.sp, color = Color.White)
            }

            if (mensaje.isNotEmpty()) {
                Spacer(Modifier.height(18.dp))
                Text(
                    mensaje,
                    color = if (mensaje.contains("exito", ignoreCase = true))
                        Color(0xFF388E3C)
                    else
                        Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
