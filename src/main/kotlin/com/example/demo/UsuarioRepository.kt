package com.example.demo.repository

import com.example.demo.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Long> {
    fun findByEmail(email: String): Optional<Usuario>
    
    fun findByRut(rut: String): Optional<Usuario>

    fun findByEmailAndContrasena(email: String, contrasena: String): Optional<Usuario>
}