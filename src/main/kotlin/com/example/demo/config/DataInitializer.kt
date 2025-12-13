package com.example.demo.config

import com.example.demo.model.Auto
import com.example.demo.model.Marca
import com.example.demo.repository.AutoRepository
import com.example.demo.repository.MarcaRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    private val marcaRepository: MarcaRepository,
    private val autoRepository: AutoRepository
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        if (marcaRepository.count() == 0L) {
            val marcas = listOf(
                Marca(
                    nombre = "Lamborghini", 
                    urlLogo = "https://upload.wikimedia.org/wikipedia/de/thumb/9/97/Lamborghini_Logo.svg/1200px-Lamborghini_Logo.svg.png", 
                    descripcion = "Superdeportivos italianos."
                ),
                Marca(
                    nombre = "Ferrari", 
                    urlLogo = "https://upload.wikimedia.org/wikipedia/de/c/c0/Scuderia_Ferrari_Logo.svg", 
                    descripcion = "El cavallino rampante."
                ),
                Marca(
                    nombre = "McLaren", 
                    urlLogo = "https://upload.wikimedia.org/wikipedia/en/6/67/McLaren_logo.svg", 
                    descripcion = "Tecnología de F1."
                ),
                Marca(
                    nombre = "Bugatti", 
                    urlLogo = "https://upload.wikimedia.org/wikipedia/commons/6/60/Bugatti_logo.svg", 
                    descripcion = "Lujo y velocidad extrema."
                )
            )
            marcaRepository.saveAll(marcas)
            println("--- MARCAS CARGADAS ---")
        }

        if (autoRepository.count() == 0L) {
            val autos = listOf(
                Auto(
                    marca = "Lamborghini", 
                    modelo = "Aventador SVJ",
                    tipoAuto = "Superdeportivo",
                    precio = 500000,
                    imagenes = mutableListOf("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7b/Aventador_SVJ_63.jpg/1200px-Aventador_SVJ_63.jpg")
                ),
                Auto(
                    marca = "Ferrari",
                    modelo = "F8 Tributo",
                    tipoAuto = "Superdeportivo",
                    precio = 280000,
                    imagenes = mutableListOf("https://upload.wikimedia.org/wikipedia/commons/1/13/Ferrari_F8_Tributo_Geneva_International_Motor_Show_2019_IMG_0083.jpg")
                ),
                Auto(
                    marca = "McLaren",
                    modelo = "720S",
                    tipoAuto = "Superdeportivo",
                    precio = 300000,
                    imagenes = mutableListOf("https://upload.wikimedia.org/wikipedia/commons/1/12/McLaren_720S.jpg")
                ),
                Auto(
                    marca = "Bugatti",
                    modelo = "Chiron",
                    tipoAuto = "Hiperdeportivo",
                    precio = 3000000,
                    imagenes = mutableListOf("https://upload.wikimedia.org/wikipedia/commons/6/62/Bugatti_Chiron_1.jpg")
                )
            )
            autoRepository.saveAll(autos)
            println("--- AUTOS CARGADOS ---")
        }
    }
}