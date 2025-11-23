package com.example.demo.controller

import com.example.demo.model.Usuario
import com.example.demo.repository.UsuarioRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping 
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable 
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/usuarios")
class UsuarioController(private val usuarioRepository: UsuarioRepository) {

    @GetMapping
    fun getAllUsuarios(): List<Usuario> {
        return usuarioRepository.findAll()
    }
    
    @DeleteMapping("/{id}")
    fun deleteUsuario(@PathVariable id: Long): ResponseEntity<Void> {
        return usuarioRepository.findById(id).map { usuario ->
            usuarioRepository.delete(usuario)
            ResponseEntity<Void>(org.springframework.http.HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }

    // === REGISTRO ===
    @PostMapping("/registro")
    fun registrar(@RequestBody usuario: Usuario): ResponseEntity<Any> {
        if (usuarioRepository.findByEmail(usuario.email).isPresent) {
            return ResponseEntity.badRequest().body(mapOf("mensaje" to "El email ya está registrado"))
        }
        if (usuarioRepository.findByRut(usuario.rut).isPresent) {
            return ResponseEntity.badRequest().body(mapOf("mensaje" to "El RUT ya está registrado"))
        }
        val nuevoUsuario = usuarioRepository.save(usuario)
        return ResponseEntity.ok(nuevoUsuario)
    }

    // === LOGIN ===
    @PostMapping("/login")
    fun login(@RequestBody loginData: Map<String, String>): ResponseEntity<Any> {
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