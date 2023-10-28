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

    @Before("execution(* com.plus.taxiapp..*.*(..))")
    fun beforeMethodCall(joinPoint: JoinPoint) {
        val methodName = joinPoint.signature.name
        val args = joinPoint.args.joinToString(", ") // 모든 인자를 문자열로 변환

        log.info("[$methodName] called with args: $args")
    }

    @AfterReturning(pointcut = "execution(* com.plus.taxiapp..*.*(..))", returning = "result")
    fun afterMethodCall(joinPoint: JoinPoint, result: Any?) {
        val methodName = joinPoint.signature.name
        val args = joinPoint.args.joinToString(", ")

        log.info("[$methodName] called with args: $args, returned: $result")
    }

}
