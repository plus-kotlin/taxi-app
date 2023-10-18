package com.plus.taxiapp.infra.store.base

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class TimeEntity(
    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected var createDtm: LocalDateTime = LocalDateTime.MIN,
    @LastModifiedDate
    @Column(nullable = false)
    protected var modifyDtm: LocalDateTime = LocalDateTime.MIN,
)