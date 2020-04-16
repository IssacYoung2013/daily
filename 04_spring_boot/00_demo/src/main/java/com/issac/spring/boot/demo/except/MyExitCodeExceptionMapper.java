package com.issac.spring.boot.demo.except;

import org.springframework.boot.ExitCodeExceptionMapper;
import org.springframework.boot.web.embedded.tomcat.ConnectorStartFailedException;
import org.springframework.boot.web.server.PortInUseException;
import org.springframework.stereotype.Component;

/**
 * @author: ywy
 * @date: 2020-03-16
 * @desc:
 */
@Component
public class MyExitCodeExceptionMapper implements ExitCodeExceptionMapper {
    @Override
    public int getExitCode(Throwable exception) {
        if (exception instanceof PortInUseException) {
            return 10;
        }
        return 0;
    }
}
