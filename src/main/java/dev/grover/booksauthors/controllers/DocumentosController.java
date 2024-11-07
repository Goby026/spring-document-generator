package dev.grover.booksauthors.controllers;
import dev.grover.booksauthors.domain.Cargo;
import dev.grover.booksauthors.domain.Despacho;
import dev.grover.booksauthors.domain.Documento;
import dev.grover.booksauthors.domain.Tipodocumento;
import dev.grover.booksauthors.services.CargoService;
import dev.grover.booksauthors.services.DespachoService;
import dev.grover.booksauthors.services.DocumentoService;
import dev.grover.booksauthors.services.TipoDocumentoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DocumentosController {

    private final DocumentoService documentoService;
    private final CargoService cargoService;
    private final DespachoService despachoService;
    private  final TipoDocumentoService tipoDocumentoService;

    public DocumentosController(DocumentoService documentoService, CargoService cargoService, DespachoService despachoService, TipoDocumentoService tipoDocumentoService) {
        this.documentoService = documentoService;
        this.cargoService = cargoService;
        this.despachoService = despachoService;
        this.tipoDocumentoService = tipoDocumentoService;
    }

    @RequestMapping("/documentos")
    public String index(Model model) throws Exception {
/*
        System.out.println("FECHA --->: " + LocalDate.now());
        System.out.println("FECHA --->: " + Instant.now());
*/
        Documento documento = new Documento();
        documento.setId(2L);
        documento.setFecha(new Date());
        documento.setTitulo("OFICIO N° "+documento.getId()+"-2023-MP-FN-IMLCF-UML I-SC.");
        documento.setDestino_user("CARMEN SARMIENTO PUMARAYME");
        documento.setCargo(null);
        documento.setDespacho(null);
        documento.setCiudad("La merced");
        documento.setAsunto("LO QUE SE INDICA");
        documento.setNumero(25L);
        documento.setContenido("Se remite la copia certificada del PROTOCOLO DE PERICIA PSICOLOGICA N° 4501-2023-PSC, perteneciente a la agraviada menor de edad de identidad reservada R. E. A.");
        documento.setFirma("Especialista Informático - UML I Selva Central");
        documento.setReferido("OFICIO N° 139-2023-MP-2°FPPC-CHYO-SC");
        documento.setCasofiscal("1516-2022");


        List<Documento> documentos = this.documentoService.listar();

        model.addAttribute("documentos", documentos);
        model.addAttribute("documento", documento);

        return "pages/documentos/documentos";
    }

    @RequestMapping("/make-pdf/{id}")
    public String crearPdf(Model model){

        Documento oficio = new Documento();
        oficio.setId(1L);
        oficio.setFecha(new Date());
        oficio.setTitulo("OFICIO N° "+oficio.getId()+"-2023-MP-FN-IMLCF-UML I-SC.");
        oficio.setDestino_user("CARMEN SARMIENTO PUMARAYME");
        oficio.setCargo(null);
        oficio.setDespacho(null);
        oficio.setCiudad("La merced");
        oficio.setAsunto("LO QUE SE INDICA");
        oficio.setNumero(25L);
        oficio.setContenido("Se remite la copia certificada del PROTOCOLO DE PERICIA PSICOLOGICA N° 4501-2023-PSC, perteneciente a la agraviada menor de edad de identidad reservada R. E. A.");
        oficio.setFirma("Especialista Informático - UML I Selva Central");
        oficio.setReferido("OFICIO N° 139-2023-MP-2°FPPC-CHYO-SC");
        oficio.setCasofiscal("1516-2022");
        model.addAttribute("oficio", oficio );
        return "pages/documentos/documentos";
    }

    @RequestMapping("/documentos/registro")
    public String create(Documento oficio, BindingResult result, Model model, RedirectAttributes flash){
        System.out.println("oficio ->" + oficio.toString());
        return "pages/documentos/registro";
    }

    public String register(@Valid Documento doc, BindingResult result, Map<String, Object> model, RedirectAttributes flash){

        if (result.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach( err -> {
                errores.put(err.getField(), "El campo ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
            });

            model.put("titulo", "Inicio de Actividad");

            return "pages/documentos/registro";
        }

        return "redirect:/documentos";
    }

}
