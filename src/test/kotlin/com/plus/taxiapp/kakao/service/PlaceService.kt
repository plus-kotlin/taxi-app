package com.plus.taxiapp.kakao.service

import com.plus.taxiapp.kakao.entity.Place
import com.plus.taxiapp.kakao.repository.PlaceRepository
import org.springframework.stereotype.Service

@Service
class PlaceService(private val placeRepository: PlaceRepository) {

    fun saveFavoritePlace(place: Place): Place {
        return placeRepository.save(place)
    }
}