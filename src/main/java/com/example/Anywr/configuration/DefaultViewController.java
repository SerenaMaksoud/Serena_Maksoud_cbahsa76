package com.example.Anywr.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@RestController
@ConditionalOnProperty(value = "anywr.controller.swagger", havingValue = "true")
public class DefaultViewController {
    @RequestMapping("/")
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui/");
    }
}
