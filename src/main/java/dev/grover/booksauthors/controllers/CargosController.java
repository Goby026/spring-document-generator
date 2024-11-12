package dev.grover.booksauthors.controllers;

import dev.grover.booksauthors.domain.Cargo;
import dev.grover.booksauthors.services.CargoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

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
        List<Cargo> cargos = this.cargoService.listar();

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

//    @RequestMapping(value = "/cargos/save", method = RequestMethod.POST)
//    public String save(@Valid Cargo cargo, Model model) throws Exception {
//
//        cargo.setEstado(1);
//        this.cargoService.registrar(cargo);
//
//        return "redirect:/cargos";
//    }

    @RequestMapping(value = "/cargos/save", method = RequestMethod.POST)
    public String guardar(@Valid Cargo cargo, BindingResult result, Model model, RedirectAttributes flash) throws Exception {

        // 👀 Binding result, siempre va junto al objeto que se envia, en este caso cargo
        if (result.hasErrors()){
            model.addAttribute("titulo", "Registrar cargo");
            return "pages/cargos/formulario";
        }

        String mensaje = ( cargo.getId() != null ) ? "Cargo modificado correctamente." : "Cargo creado exitosamente.";

        this.cargoService.registrar(cargo);
        flash.addFlashAttribute("success", mensaje );
        return "redirect:/cargos";
    }

    @RequestMapping(value = "/cargos/formulario/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) throws Exception {
        Cargo cargo = null;
        if (id>0){
            cargo = this.cargoService.obtener(id);
            if (cargo == null){
                flash.addFlashAttribute("error", "No se puede cargar el dato seleccionado 👀");
            }else{
                flash.addFlashAttribute("success", "Cargo modificado correctamente.");
            }
        }else{
            flash.addFlashAttribute("error", "No existe el id seleccionado.");
            return "redirect:/cargos";
        }

        model.put("cargo", cargo);
        model.put("titulo", "Registrar cargo");

        return "pages/cargos/formulario";
    }

}
