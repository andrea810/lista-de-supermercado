package com.supermarket.lista_de_supermercado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.itextpdf.text.DocumentException;
import com.supermarket.lista_de_supermercado.model.ListaCompras;
import com.supermarket.lista_de_supermercado.service.ListaComprasService;
import com.supermarket.lista_de_supermercado.service.PdfService;

@Controller
@RequestMapping("/listas")
public class ListaController {

    @Autowired
    private ListaComprasService listaComprasService;

    @Autowired
    private PdfService pdfService;

    @GetMapping
    public String listarTodas(Model model) {
        model.addAttribute("listas", listaComprasService.findAll());
        return "listas/index";
    }

    @GetMapping("/nova")
    public String formulario(Model model) {
        model.addAttribute("lista", new ListaCompras());
        return "listas/form";
    }

    @GetMapping("/{id}")
    public String visualizar(@PathVariable Long id, Model model) {
        model.addAttribute("lista", listaComprasService.findById(id));
        return "listas/visualizar";
    }

    @PostMapping
    public String salvar(@ModelAttribute ListaCompras lista) {
        listaComprasService.save(lista);
        return "redirect:/listas";
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> exportarPdf(@PathVariable Long id) {
        try {
            ListaCompras lista = listaComprasService.findById(id);
            byte[] pdf = pdfService.gerarPdf(lista);
            
            return ResponseEntity.ok()
                    .header("Content-Type", "application/pdf")
                    .header("Content-Disposition", "attachment; filename=\"" + lista.getTitulo() + ".pdf\"")
                    .body(pdf);
        } catch (DocumentException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}