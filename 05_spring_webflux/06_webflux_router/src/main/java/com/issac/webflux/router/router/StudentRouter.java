package com.issac.webflux.router.router;

import com.issac.webflux.router.handler.StudentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 *
 *
 * @author: ywy
 * @date: 2019-11-06
 * @desc:
 */
@Configuration
public class StudentRouter {

    @Bean
    public RouterFunction<ServerResponse> customRouter(StudentHandler handler) {
        return RouterFunctions
                .nest(RequestPredicates.path("/student"),
                        RouterFunctions.route(RequestPredicates.GET("/all"), handler::findAllHandle)
                        .andRoute(RequestPredicates.POST("/save2")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),handler::saveHandle)
                        .andRoute(RequestPredicates.DELETE("/del/{id}"),handler::delHandle)
                        .andRoute(RequestPredicates.PUT("/update/{id}"),handler::updateHandle)
                );
    }
}
