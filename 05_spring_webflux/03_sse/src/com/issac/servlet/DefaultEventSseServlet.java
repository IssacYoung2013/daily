package com.issac.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author: ywy
 * @date: 2019-11-04
 * @desc:
 */
@WebServlet(value = "/sse/default")
public class DefaultEventSseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        for (int i = 0; i < 10; i++) {
            out.print("data:" + i + "\n");
            out.print("\r\n");
            out.flush();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
        }
    }
}
