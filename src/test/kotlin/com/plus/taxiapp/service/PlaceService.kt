package com.plus.taxiapp.service

import com.plus.taxiapp.entity.Place
import com.plus.taxiapp.repository.PlaceRepository
import org.springframework.stereotype.Service

@Service
class PlaceService(private val placeRepository: PlaceRepository) {

    fun saveFavoritePlace(place: Place): Place {
        return placeRepository.save(place)
    }
}