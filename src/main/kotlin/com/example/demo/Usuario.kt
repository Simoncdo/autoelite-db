package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "usuarios")
data class Usuario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var nombre: String = "",

    @Column(unique = true)
    var email: String = "", // IMPORTANTE: Se llama 'email' igual que en tu Android

    var contrasena: String = "",

    var direccion: String = "", // Agregado

    var rut: String = "",       // Agregado

    var rol: String = "CLIENTE"
)