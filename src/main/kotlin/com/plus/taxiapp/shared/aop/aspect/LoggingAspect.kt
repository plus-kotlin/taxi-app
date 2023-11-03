package com.plus.taxiapp.shared.aop.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Aspect
@Component
class LoggingAspect {
    private val logger: Logger = LoggerFactory.getLogger(LoggingAspect::class.java)

    @Before("execution(* com.plus.taxiapp..*.*(..))")
    fun beforeMethodCall(joinPoint: JoinPoint) {
        val methodName = joinPoint.signature.name
        val args = joinPoint.args.joinToString(", ") // 모든 인자를 문자열로 변환

        logger.info("[$methodName] called with args: $args")
    }

    @AfterReturning(pointcut = "execution(* com.plus.taxiapp..*.*(..))", returning = "result")
    fun afterMethodCall(joinPoint: JoinPoint, result: Any?) {
        val methodName = joinPoint.signature.name
        val args = joinPoint.args.joinToString(", ")

        logger.info("[$methodName] called with args: $args, returned: $result")
    }

}
