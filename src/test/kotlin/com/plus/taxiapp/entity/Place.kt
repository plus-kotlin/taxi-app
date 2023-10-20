package com.plus.taxiapp.entity

import jakarta.persistence.*


@Entity
@Table
data class Place(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "user_id", nullable = false)
    val userId: Long,

    @Column(name = "custom_place_name", nullable = false)
    var customPlaceName: String,

    @Column(name = "place_name", nullable = false)
    val placeName: String,

    @Column(name = "x_coordinate", nullable = false)
    val xCoordinate: Double,

    @Column(name = "y_coordinate", nullable = false)
    val yCoordinate: Double
)