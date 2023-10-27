package com.plus.taxiapp.shared.aop.aspect

import lombok.extern.slf4j.Slf4j
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.hibernate.query.sqm.tree.SqmNode.log
import org.springframework.stereotype.Component

@Aspect
@Component
@Slf4j
class LoggingAspect {

    @Before("execution(* com.example..*.*(..))")
    fun beforeMethodCall(joinPoint: JoinPoint) {
        val methodName = joinPoint.signature.name
        val args = joinPoint.args

        val threadId = Thread.currentThread().id
        val timestamp = System.currentTimeMillis()
        val instanceId = "[미정]" // TODO 인프라 환경 구축에 따라 인스턴스 정의 방법 재정의하기

        args.firstOrNull()?.let {
            val traceId = "$instanceId-$timestamp-$threadId"
            log.info("[$traceId] [${System.currentTimeMillis()}] [$methodName] call $it")
        }
    }

    @AfterReturning(pointcut = "execution(* com.example..*.*(..))", returning = "result")
    fun afterMethodCall(joinPoint: JoinPoint, result: Any) {
        val args = joinPoint.args

        val threadId = Thread.currentThread().id
        val timestamp = System.currentTimeMillis()
        val instanceId = "[미정]" // TODO 인프라 환경 구축에 따라 인스턴스 정의 방법 재정의하기

        args.firstOrNull()?.let {
            val traceId = "$instanceId-$timestamp-$threadId"
            log.info("[$traceId] [${System.currentTimeMillis()}] [$it] $it")
        }
    }
}
