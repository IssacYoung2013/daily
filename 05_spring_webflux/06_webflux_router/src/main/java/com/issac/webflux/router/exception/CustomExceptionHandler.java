package com.issac.webflux.router.exception;

import org.reactivestreams.Publisher;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * @author: ywy
 * @date: 2019-11-06
 * @desc:
 */
@Component
@Order(-99)
public class CustomExceptionHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.BAD_REQUEST);
        response.getHeaders().setContentType(MediaType.TEXT_PLAIN);
        String msg = this.formatMsg(ex);

        Publisher<? extends DataBuffer> data = Mono.just(response.bufferFactory().wrap(msg.getBytes()));
        return response.writeWith(data);
    }

    private String formatMsg(Throwable ex) {
        String msg = "发生异常：" + ex.getMessage();
        if (ex instanceof StudentException) {
            StudentException esEx = (StudentException) ex;
            msg += "【" + esEx.getErrField() + ":" + esEx.getErrMsg() + "】";
        }
        return msg;
    }
}
