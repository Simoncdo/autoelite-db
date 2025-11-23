package com.example.demo.controller

import com.example.demo.model.Usuario
import com.example.demo.repository.UsuarioRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping // Asegúrate de que este import esté
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/usuarios")
class UsuarioController(private val usuarioRepository: UsuarioRepository) {

    // === OBTENER TODOS LOS USUARIOS (PARA EL ADMIN) ===
    // Este es el nuevo método que soluciona el error 404
    @GetMapping
    fun getAllUsuarios(): List<Usuario> {
        return usuarioRepository.findAll()
    }

    // === REGISTRO ===
    @PostMapping("/registro")
    fun registrar(@RequestBody usuario: Usuario): ResponseEntity<Any> {
        // 1. Validar si el EMAIL ya existe
        if (usuarioRepository.findByEmail(usuario.email).isPresent) {
            return ResponseEntity.badRequest().body(mapOf("mensaje" to "El email ya está registrado"))
        }
        
        // 2. Validar si el RUT ya existe
        if (usuarioRepository.findByRut(usuario.rut).isPresent) {
            return ResponseEntity.badRequest().body(mapOf("mensaje" to "El RUT ya está registrado"))
        }

        // 3. Guardar el usuario
        val nuevoUsuario = usuarioRepository.save(usuario)
        return ResponseEntity.ok(nuevoUsuario)
    }

    // === LOGIN ===
    @PostMapping("/login")
    fun login(@RequestBody loginData: Map<String, String>): ResponseEntity<Any> {
        // Android enviará un JSON con "email" y "contrasena"
        val email = loginData["email"]
        val pass = loginData["contrasena"]

        if (email.isNullOrEmpty() || pass.isNullOrEmpty()) {
            return ResponseEntity.badRequest().body(mapOf("mensaje" to "Faltan datos"))
        }

        val usuario = usuarioRepository.findByEmailAndContrasena(email, pass)

        return if (usuario.isPresent) {
            ResponseEntity.ok(usuario.get())
        } else {
            ResponseEntity.status(401).body(mapOf("mensaje" to "Credenciales incorrectas"))
        }
    }
}