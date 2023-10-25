package com.plus.taxiapp.shared.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.extern.slf4j.Slf4j
import org.hibernate.query.sqm.tree.SqmNode.log
import org.springframework.web.servlet.HandlerInterceptor


@Slf4j
class LoggingInterceptor : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        // TODO: 인터셉터에 뭐 넣지 ??
        // 필터 말고 여기서 요청값 로깅해도 되나?
        // 특정 요청을 구분지을 시작 값 ??
        return true
    }

    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        // TODO: 인터셉터에 뭐 넣지 ?? 2
        // 응답값 ?
        // 특정 요청을 구분지을 종료 값 ??
    }
}
