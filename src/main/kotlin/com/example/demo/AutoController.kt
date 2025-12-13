package com.example.demo.controller

import com.example.demo.model.Auto
import com.example.demo.repository.AutoRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/autos")
class AutoController(private val autoRepository: AutoRepository) {

    @GetMapping("/marca/{nombreMarca}")
    fun getAutosByMarca(@PathVariable nombreMarca: String): List<Auto> {
        return autoRepository.findByMarca(nombreMarca)
    }

    @GetMapping
    fun getAllAutos(): List<Auto> = autoRepository.findAll()

    @GetMapping("/{id}")
    fun getAutoById(@PathVariable id: Long): ResponseEntity<Auto> {
        return autoRepository.findById(id)
            .map { auto -> ResponseEntity.ok(auto) }
            .orElse(ResponseEntity.notFound().build())
    }

    @PostMapping
    fun createAuto(@RequestBody auto: Auto): Auto {
        return autoRepository.save(auto)
    }

    @PutMapping("/{id}")
    fun updateAuto(@PathVariable id: Long, @RequestBody autoDetalles: Auto): ResponseEntity<Auto> {
        return autoRepository.findById(id).map { autoExistente ->
            val autoActualizado = autoExistente.copy(
                marca = autoDetalles.marca,
                modelo = autoDetalles.modelo, 
                kilometraje = autoDetalles.kilometraje,
                tipoAuto = autoDetalles.tipoAuto,
                esDeportivo = autoDetalles.esDeportivo,
                tipoMotor = autoDetalles.tipoMotor,
                precio = autoDetalles.precio,
                imagenes = autoDetalles.imagenes
            )
            ResponseEntity.ok(autoRepository.save(autoActualizado))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deleteAuto(@PathVariable id: Long): ResponseEntity<Void> {
        return autoRepository.findById(id).map { auto ->
            autoRepository.delete(auto)
            ResponseEntity<Void>(org.springframework.http.HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}