package com.senac.culturacine.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true")
@RestController
@RequestMapping("/api/theme")
public class TemaController {

    private static final Logger logger = LoggerFactory.getLogger(TemaController.class);

    @PostMapping("/set")
    public ResponseEntity<Void> setTheme(@RequestParam String theme, HttpServletResponse response) {
        Cookie themeCookie = new Cookie("theme", theme);
        themeCookie.setMaxAge(60 * 60 * 24 * 30); // Expira em 30 dias
        themeCookie.setPath("/"); // Disponível em toda a aplicação
        themeCookie.setSecure(true); // Ativa Secure para SameSite=None
        themeCookie.setHttpOnly(false); // Pode ser acessado via JavaScript

        // Define o cabeçalho Set-Cookie com SameSite=None e Secure
        response.addHeader("Set-Cookie", themeCookie.getName() + "=" + themeCookie.getValue()
                + "; Max-Age=" + themeCookie.getMaxAge()
                + "; Path=" + themeCookie.getPath()
                + "; SameSite=None; Secure");

        // Adiciona o cookie para o cliente
        response.addCookie(themeCookie);

        logger.info("Cookie 'theme' configurado com valor: " + theme); // Log do valor configurado
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true")
    @GetMapping("/get")
    public ResponseEntity<String> getTheme(@CookieValue(name = "theme", defaultValue = "dark") String theme, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("Nome do cookie: " + cookie.getName() + ", Valor: " + cookie.getValue());
            }
        }
        System.out.println("Valor retornado do theme: " + theme);
        return ResponseEntity.ok(theme);
    }
}