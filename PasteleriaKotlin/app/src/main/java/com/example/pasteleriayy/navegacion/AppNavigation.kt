package com.example.pasteleriayy.navegacion


import androidx.compose.runtime.Composable
import com.example.pasteleriayy.Pantallas.PantallaEditarUsuario
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pasteleriayy.Pantallas.ContactoScreen
import com.example.pasteleriayy.Pantallas.MenuProductosScreen
import com.example.pasteleriayy.Pantallas.FormularioValidacion
import com.example.pasteleriayy.Pantallas.PantallaCrud

object AppScreens {
    const val MENU = "menu"
    const val REGISTRO = "registro"
    const val CONTACTO = "contacto"
    const val PROMOCIONES = "promociones"
    const val API_RECETAS = "apiRecetas"
    const val CRUD_USUARIOS = "crud_usuarios"
}

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {

    NavHost(
        navController = navController,
        startDestination = AppScreens.MENU,
        modifier = modifier
    ) {
        composable(AppScreens.MENU) {
            MenuProductosScreen(navController = navController)
        }

        composable(AppScreens.REGISTRO) {
            FormularioValidacion(navController = navController)
        }

        composable(AppScreens.CONTACTO) {
            ContactoScreen(navController = navController)
        }


        composable(AppScreens.CRUD_USUARIOS) {
            PantallaCrud(navController = navController)
        }

        composable("editar_usuario/{id}") { backStack ->
            val id = backStack.arguments?.getString("id")!!.toLong()
            PantallaEditarUsuario(idUsuario = id, navController = navController)
        }



    }
}
