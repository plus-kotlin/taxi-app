package com.plus.taxiapp.repository

import com.plus.taxiapp.entity.Place
import org.springframework.data.jpa.repository.JpaRepository

interface PlaceRepository : JpaRepository<Place, Long> {
    fun findByUserId(userId: Long): List<Place>
}