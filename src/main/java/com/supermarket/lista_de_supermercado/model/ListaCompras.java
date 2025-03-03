package com.supermarket.lista_de_supermercado.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "listas_compras")
public class ListaCompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL)
    private List<Item> itens = new ArrayList<>();
    
}
