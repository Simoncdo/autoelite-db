package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "marcas")
data class Marca(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(unique = true)
    var nombre: String = "",

    var descripcion: String = "",

    var urlLogo: String = ""