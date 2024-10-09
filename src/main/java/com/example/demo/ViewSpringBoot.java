package com.example.demo;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

// TODO: Datenbank mit JPA
// TODO: Non Blocking (Webflux)
// TODO: Web MVC

@SpringBootApplication
@Controller
public class ViewSpringBoot implements HttpSessionListener {

    private final ServletContext servletContext;

    public static void main(String[] args) {
        SpringApplication.run(ViewSpringBoot.class, args);
    }

    ViewSpringBoot(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public void sessionCreated(HttpSessionEvent sessionEvent) {
        Integer activeSessions = (Integer) sessionEvent
            .getSession()
            .getServletContext()
            .getAttribute("activeSessions");

        if (activeSessions == null) {
            activeSessions = 0;
        }

        sessionEvent
            .getSession()
            .getServletContext()
            .setAttribute("activeSessions", activeSessions + 1);
    }

    @GetMapping({"/"})
    public String get(
        HttpSession session,
        org.springframework.ui.Model ui_model
    ) {
        Model model = new Model();
        model.getGameState();
        session.setAttribute("model", model);

        ui_model.addAttribute("activeSessions", servletContext.getAttribute("activeSessions"));

        return "start";
    }

    @PostMapping({"/"})
    public String post(
        @RequestParam("guess") String last_guess,
        HttpSession session,
        org.springframework.ui.Model ui_model
    ) {
        Model model = (Model) session.getAttribute("model");
        model.recomputeBasedOnInput(last_guess);
        ui_model.addAttribute("model", model);
        GameState state = model.getGameState();
        ui_model.addAttribute("gameState", state);

        if (state == GameState.SOLVED) {
            return "win";
        } else {
            return "playing";
        }
    }
}
