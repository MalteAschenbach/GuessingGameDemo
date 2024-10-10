package com.example.demo;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// TODO: Non Blocking (Webflux)
// TODO: Web MVC

@SpringBootApplication
@AutoConfiguration
@Controller
public class ViewSpringBoot implements HttpSessionListener {

    private final ServletContext servletContext;

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

    @GetMapping({"/start"})
    public String start(
        HttpSession session,
        org.springframework.ui.Model ui_model
    ) {
        Model model = new Model();
        model.getGameState();
        session.setAttribute("model", model);

        ui_model.addAttribute("activeSessions", servletContext.getAttribute("activeSessions"));

        ui_model.addAttribute("winners", model.getWinners());

        return "start";
    }

    @GetMapping({"/playing"})
    public String play(
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

    @GetMapping({"/saved"})
    public String save(
        @RequestParam("name") String name,
        HttpSession session,
        org.springframework.ui.Model ui_model
    ) {
        Model model = (Model) session.getAttribute("model");
        model.saveWithName(name);
        return "redirect:start";
    }
}
