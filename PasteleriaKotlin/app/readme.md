# 📱 Pastelería App -- Kotlin + Jetpack Compose

Aplicación móvil creada en **Kotlin** utilizando **Jetpack Compose**,
conectada a un microservicio propio desarrollado en Spring Boot.\
La app muestra productos, promociones y permite la interacción con datos
desde el backend.

## 👥 Integrantes del equipo

-   **Eliezer Carrasco Lobos**\
-   **María José Velásquez**

## 📌 Descripción del proyecto

La aplicación incluye:

-   Pantallas construidas con Jetpack Compose\
-   Listado de productos y promociones\
-   Arquitectura **MVVM**\
-   Fuentes de datos locales (DataSource)\
-   Comunicación con el microservicio mediante **Retrofit**\
-   APK firmado para entrega

## 🧩 Conexión con el microservicio

La app consume los endpoints REST del backend:

-   Crear usuario\
-   Listar usuarios\
-   Obtener usuario\
-   Actualizar\
-   Eliminar

El manejo de datos se realiza desde un `Repository` que comunica
ViewModel ↔ API.

## ▶ Cómo ejecutar la App

1.  Abrir Android Studio\
2.  Cargar el proyecto\
3.  Ejecutar en un emulador o dispositivo físico

APK firmado disponible en:

    app/build/outputs/apk/release/

## ✔ Estado del Proyecto

-   App funcional\
-   Conexión lista con el backend\
-   UI desarrollada con Compose\
-   Código ordenado y modular
