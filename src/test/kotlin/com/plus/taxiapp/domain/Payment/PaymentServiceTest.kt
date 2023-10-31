package com.plus.taxiapp.domain.Payment

import com.plus.taxiapp.domain.member.*
import com.plus.taxiapp.domain.payment.PaymentMethodRepository
import com.plus.taxiapp.domain.payment.PaymentService
import com.plus.taxiapp.infra.client.PaymentClient
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class PaymentServiceTest {

    @Mock
    private lateinit var paymentMethodRepository: PaymentMethodRepository

    @Mock
    private lateinit var paymentClient: PaymentClient

    @InjectMocks
    private lateinit var paymentService: PaymentService
}