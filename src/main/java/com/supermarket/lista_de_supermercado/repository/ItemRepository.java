package com.supermarket.lista_de_supermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supermarket.lista_de_supermercado.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // Métodos básicos de CRUD já são fornecidos pelo JpaRepository
    // findAll(), findById(), save(), deleteById(), etc.
}