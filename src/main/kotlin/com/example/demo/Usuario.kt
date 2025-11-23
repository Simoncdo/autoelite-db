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
    var email: String = "", 
    var contrasena: String = "",

    var direccion: String = "", 

    var rut: String = "",       

    var rol: String = "CLIENTE"
)