package com.plus.taxiapp.kakao.repository

import com.plus.taxiapp.kakao.entity.Place
import org.springframework.data.jpa.repository.JpaRepository

interface PlaceRepository : JpaRepository<Place, Long> {
    fun findByUserId(userId: Long): List<Place>
}