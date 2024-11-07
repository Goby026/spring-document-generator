package dev.grover.booksauthors.controllers;

import dev.grover.booksauthors.domain.Cargo;
import dev.grover.booksauthors.services.CargoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CargosController {

    private final CargoService cargoService;

    public CargosController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @RequestMapping(value = "/cargos")
    public String index(Model model) throws Exception {
/*
        System.out.println("FECHA --->: " + LocalDate.now());
        System.out.println("FECHA --->: " + Instant.now());
*/
        String titulo = "GESTION DE CARGOS";
        List<Cargo> cargos = cargoService.listar();

        model.addAttribute("titulo", titulo);
        model.addAttribute("cargos", cargos);

        return "pages/cargos/cargos";
    }


    @RequestMapping(value = "/cargos/formulario")
    public String register(Model model) throws Exception {
        String titulo = "REGISTRAR NUEVO CARGO";

        Cargo cargo = new Cargo();

        model.addAttribute("titulo", titulo);
        model.addAttribute("cargo", cargo);

        return "pages/cargos/formulario";
    }

    @RequestMapping(value = "/cargos/save", method = RequestMethod.POST)
    public String save(@Valid Cargo cargo, Model model) throws Exception {

        cargo.setEstado(1);
        cargoService.registrar(cargo);

        return "redirect:/cargos";
    }
}
