package com.plus.taxiapp.shared.config

import com.plus.taxiapp.shared.filter.TraceIdFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FilterConfig {

    @Bean
    fun traceIdFilterRegistration(filter: TraceIdFilter): FilterRegistrationBean<*> {
        val registration = FilterRegistrationBean<TraceIdFilter>()
        registration.filter = filter
        registration.addUrlPatterns("/*")
        registration.setName("traceIdFilter")
        registration.order = 1
        return registration
    }
}
