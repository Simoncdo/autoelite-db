package com.example.demo.repository

import com.example.demo.model.Auto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AutoRepository : JpaRepository<Auto, Long>