package com.example.demo.repository

import com.example.demo.model.Marca
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MarcaRepository : JpaRepository<Marca, Long> {
    fun findByNombre(nombre: String): Marca?
}