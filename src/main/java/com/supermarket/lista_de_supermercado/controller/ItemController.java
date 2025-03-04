package com.supermarket.lista_de_supermercado.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.ArrayList;

import com.supermarket.lista_de_supermercado.model.Item;
import com.supermarket.lista_de_supermercado.model.ListaCompras;
import com.supermarket.lista_de_supermercado.service.ItemService;
import com.supermarket.lista_de_supermercado.service.ListaComprasService;

@Controller
@RequestMapping("/listas/{listaId}/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ListaComprasService listaComprasService;

    @PostMapping
    public String adicionarItem(@PathVariable Long listaId, @ModelAttribute Item item) {
        ListaCompras lista = listaComprasService.findById(listaId);
        if (lista != null) {
            item.setLista(lista);
            itemService.save(item);
        }
        return "redirect:/listas/" + listaId;
    }

    @PutMapping("/{id}/comprado")
    public String marcarComoComprado(@PathVariable Long listaId, @PathVariable Long id) {
        itemService.marcarComoComprado(id);
        return "redirect:/listas/" + listaId;
    }
    @GetMapping("/{id}/delete")
    public String excluirItem(@PathVariable Long listaId, @PathVariable Long id) {
        itemService.deleteById(id);
        return "redirect:/listas/" + listaId;
    }
}