package com.example.cm.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class cmBasicappController {
    @GetMapping(value="/")
    public void main(HttpServletResponse response) throws IOException{
        response.sendRedirect("/html/main/main.html");
    }
    @GetMapping(value="/login")
    public void login(HttpServletResponse response) throws IOException{
        response.sendRedirect("/html/login/login.html");
    }
}
