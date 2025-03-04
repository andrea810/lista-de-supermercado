package com.supermarket.lista_de_supermercado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermarket.lista_de_supermercado.model.Item;
import com.supermarket.lista_de_supermercado.repository.ItemRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item save(Item item) {
        if (item.getLista() == null) {
            throw new IllegalArgumentException("Item must belong to a list");
        }
        return itemRepository.save(item);
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    public Item marcarComoComprado(Long id) {
        Item item = itemRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Item não encontrado"));
        item.setComprado(!item.isComprado());
        return itemRepository.save(item);
    }
}