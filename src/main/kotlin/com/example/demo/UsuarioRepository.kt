package com.example.demo.repository

import com.example.demo.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Long> {
    // Buscar por email (Login y validación)
    fun findByEmail(email: String): Optional<Usuario>
    
    // Buscar por RUT (Validación para que no se repita)
    fun findByRut(rut: String): Optional<Usuario>

    // Login (Email + Contraseña)
    fun findByEmailAndContrasena(email: String, contrasena: String): Optional<Usuario>
}