package com.plus.taxiapp.infra.store.taxiDriver

import org.springframework.data.jpa.repository.JpaRepository

interface DriverJpaRepository: JpaRepository<DriverEntity, Long> {

}