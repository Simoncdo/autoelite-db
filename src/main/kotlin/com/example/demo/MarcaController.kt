package com.example.demo.controller

import com.example.demo.model.Marca
import com.example.demo.repository.MarcaRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/marcas")
class MarcaController(private val marcaRepository: MarcaRepository) {

    @GetMapping
    fun getAllMarcas(): List<Marca> = marcaRepository.findAll()

    @PostMapping
    fun createMarca(@RequestBody marca: Marca): Marca {
        return marcaRepository.save(marca)
    }
}