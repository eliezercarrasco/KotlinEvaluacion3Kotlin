package com.example.pasteleriayy.vistaModelo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pasteleriayy.datos.UsuarioRepository
import com.example.pasteleriayy.modelos.UsuarioModelo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegistroVistaModelo : ViewModel() {

    private val repo = UsuarioRepository()

    private val _usuarios = MutableStateFlow<List<UsuarioModelo>>(emptyList())
    val usuarios: StateFlow<List<UsuarioModelo>> = _usuarios

    private val _mensaje = MutableStateFlow("")
    val mensaje: StateFlow<String> = _mensaje

    // ----------------------------
    // FUNCIÓN QUE TE FALTABA
    // ----------------------------
    fun actualizarMensaje(texto: String) {
        _mensaje.value = texto
    }

    fun cargarUsuarios() {
        viewModelScope.launch {
            try {
                _usuarios.value = repo.listar()
            } catch (e: Exception) {
                _mensaje.value = "Error al cargar usuarios"
            }
        }
    }

    fun registrarUsuario(usuario: UsuarioModelo) {
        viewModelScope.launch {
            try {
                repo.crear(usuario)
                _mensaje.value = "Usuario registrado con éxito"
                cargarUsuarios()
            } catch (e: Exception) {
                _mensaje.value = "Error al registrar usuario: ${e.message}"
            }
        }
    }

    fun obtenerUsuario(id: Long, onResult: (UsuarioModelo?) -> Unit) {
        viewModelScope.launch {
            try {
                val usuario = repo.obtener(id)
                onResult(usuario)
            } catch (e: Exception) {
                onResult(null)
            }
        }
    }

    fun actualizarUsuario(id: Long, usuario: UsuarioModelo, onFinish: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repo.actualizar(id, usuario)
                if (response.isSuccessful) {
                    _mensaje.value = "Usuario actualizado"
                    cargarUsuarios()
                    onFinish(true)
                } else {
                    _mensaje.value = "Error al actualizar usuario"
                    onFinish(false)
                }
            } catch (e: Exception) {
                _mensaje.value = "Error al conectar con el servidor"
                onFinish(false)
            }
        }
    }

    fun eliminar(id: Long) {
        viewModelScope.launch {
            try {
                repo.eliminar(id)
                cargarUsuarios()
                _mensaje.value = "Usuario eliminado"
            } catch (e: Exception) {
                _mensaje.value = "Error al eliminar"
            }
        }
    }
}
