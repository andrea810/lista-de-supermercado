package com.supermarket.lista_de_supermercado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermarket.lista_de_supermercado.model.ListaCompras;
import com.supermarket.lista_de_supermercado.repository.ListaComprasRepository;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ListaComprasService {

    @Autowired
    private ListaComprasRepository listaComprasRepository;

    public List<ListaCompras> findAll() {
        return listaComprasRepository.findAll();
    }

    public ListaCompras save(ListaCompras lista) {
        return listaComprasRepository.save(lista);
    }

    public ListaCompras findById(Long id) {
        return listaComprasRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Lista não encontrada"));
    }

    public void deleteById(Long id) {
        if (!listaComprasRepository.existsById(id)) {
            throw new EntityNotFoundException("Lista não encontrada");
        }
        listaComprasRepository.deleteById(id);
    }
}