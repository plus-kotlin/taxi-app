package com.plus.taxiapp.infra.store.member

import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository: JpaRepository<MemberEntity, Long> {
}