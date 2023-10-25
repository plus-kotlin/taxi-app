package com.plus.taxiapp.infra.store.taxiDriver

import com.plus.taxiapp.domain.member.PaymentType
import com.plus.taxiapp.domain.taxiDriver.TaxiDriverType
import com.plus.taxiapp.domain.taxiDriver.TaxiStatus
import com.plus.taxiapp.infra.store.base.TimeEntity
import com.plus.taxiapp.infra.store.member.MemberEntity
import jakarta.persistence.*

@Entity
class TaxiDriverEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taxidriver_id")
    val id: Long? = null,
    @OneToOne(mappedBy = "taxiDriverEntity", fetch = FetchType.LAZY)
    val member: MemberEntity,
    val driverId: String,
    val taxiNumber: String,
    val taxiType: TaxiDriverType,
    val taxiModel: String,
    @Enumerated(EnumType.STRING)
    var taxiStatus: TaxiStatus? = TaxiStatus.NOTDRIVING
) : TimeEntity()