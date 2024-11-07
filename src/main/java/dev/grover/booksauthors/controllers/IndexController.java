package dev.grover.booksauthors.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(Model model){
        String titulo = "LIBROS Y AUTORES";
        model.addAttribute("titulo",titulo);
        return "index";
    }
}
