package com.plus.taxiapp.shared.filter

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.slf4j.MDC
import org.springframework.stereotype.Component
import java.util.*

@Component
class TraceIdFilter : Filter {

    companion object {
        private val logger = LoggerFactory.getLogger(TraceIdFilter::class.java)
    }

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val traceId = UUID.randomUUID().toString()
        MDC.put("traceId", traceId)

        if (isHttp(request, response)) {
            val httpRequest = request as HttpServletRequest
            val httpResponse = response as HttpServletResponse

            logger.info("REQUEST TRACING_ID: {}, Method: {}, URI: {}", traceId, httpRequest.method, httpRequest.requestURI)
            chain.doFilter(request, response)
            System.out.println("##### TraceIdFilter is called!")
            logger.info("RESPONSE TRACING_ID: {}, Status: {}", traceId, httpResponse.status)
        } else {
            chain.doFilter(request, response)
        }

        MDC.clear()
    }

    private fun isHttp(request: ServletRequest, response: ServletResponse): Boolean {
        return request is HttpServletRequest && response is HttpServletResponse
    }
}
