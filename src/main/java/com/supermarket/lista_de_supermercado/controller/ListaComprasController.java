package com.supermarket.lista_de_supermercado.controller;

import com.supermarket.lista_de_supermercado.model.ListaCompras;
import com.supermarket.lista_de_supermercado.service.ListaComprasService;
import com.supermarket.lista_de_supermercado.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/listas")
public class ListaComprasController {

    @Autowired
    private ListaComprasService listaComprasService;
    
    @Autowired
    private PdfService pdfService;
    
    
    @GetMapping("/{id}/imprimir")
    public String imprimirLista(@PathVariable Long id, Model model) {
        // The findById method already throws an exception if not found
        ListaCompras lista = listaComprasService.findById(id);
    
        model.addAttribute("lista", lista);
        return "listas/imprimir";
    }
    
    
}