package com.supermarket.lista_de_supermercado.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        try {
            System.out.println("=== DEBUG: Iniciando adição de item ===");
            System.out.println("Lista ID: " + listaId);
            System.out.println("Item descrição: " + item.getDescricao());
            System.out.println("Item quantidade: " + item.getQuantidade());
            System.out.println("Item categoria: " + item.getCategoria());
            
            ListaCompras lista = listaComprasService.findById(listaId);
            System.out.println("Lista encontrada: " + (lista != null ? lista.getTitulo() : "null"));
            
            if (lista != null) {
                item.setLista(lista);
                // Set default values if null
                if (item.getCategoria() == null) {
                    System.out.println("Categoria é null, definindo como GERAL");
                    item.setCategoria(com.supermarket.lista_de_supermercado.model.enums.Categoria.GERAL);
                }
                System.out.println("Salvando item...");
                itemService.save(item);
                System.out.println("Item salvo com sucesso!");
            } else {
                System.err.println("Lista não encontrada com ID: " + listaId);
            }
        } catch (Exception e) {
            System.err.println("=== ERRO DETALHADO ===");
            System.err.println("Mensagem: " + e.getMessage());
            System.err.println("Classe: " + e.getClass().getName());
            e.printStackTrace();
            System.err.println("=== FIM DO ERRO ===");
        }
        return "redirect:/listas/" + listaId;
    }

    // Change from @PutMapping to @GetMapping
    @PostMapping("/{id}/comprado")
    public String marcarComoComprado(@PathVariable Long listaId, @PathVariable Long id) {
        itemService.marcarComoComprado(id);
        return "redirect:/listas/" + listaId;
    }
    @GetMapping("/{id}/delete")
    public String excluirItem(@PathVariable Long listaId, @PathVariable Long id) {
        itemService.deleteById(id);
        return "redirect:/listas/" + listaId;
    }

    @PutMapping("/{id}/quantidade")
    @ResponseBody
    public ResponseEntity<?> editarQuantidade(@PathVariable Long listaId, @PathVariable Long id, @RequestBody QuantidadeRequest request) {
        try {
            itemService.editarQuantidade(id, request.getQuantidade());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/categoria")
    @ResponseBody
    public ResponseEntity<?> editarCategoria(@PathVariable Long listaId, @PathVariable Long id, @RequestBody CategoriaRequest request) {
        try {
            itemService.editarCategoria(id, request.getCategoria());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Classes para receber os dados JSON
    public static class QuantidadeRequest {
        private Integer quantidade;
        
        public Integer getQuantidade() {
            return quantidade;
        }
        
        public void setQuantidade(Integer quantidade) {
            this.quantidade = quantidade;
        }
    }

    public static class CategoriaRequest {
        private String categoria;
        
        public String getCategoria() {
            return categoria;
        }
        
        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }
    }
}