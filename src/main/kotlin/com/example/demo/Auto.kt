package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "autos")
data class Auto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null, 

    var marca: String = "",

    var modelo: String = "",

    var kilometraje: Double = 0.0,

    var tipoAuto: String = "",

    var esDeportivo: Boolean = false,

    var tipoMotor: String = "",

    var precio: Long = 0,

    @ElementCollection
    @CollectionTable(name = "auto_imagenes", joinColumns = [JoinColumn(name = "auto_id")])
    @Column(name = "url_imagen")
    var imagenes: MutableList<String> = mutableListOf()
)