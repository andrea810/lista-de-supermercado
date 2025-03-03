package com.supermarket.lista_de_supermercado.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.supermarket.lista_de_supermercado.model.enums.Categoria;
import com.supermarket.lista_de_supermercado.model.enums.Prioridade;
import com.supermarket.lista_de_supermercado.model.enums.UnidadeMedida;

import lombok.Data;

@Data
@Entity
@Table(name = "itens")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String descricao;

    @NotNull
    @Min(1)
    private Integer quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lista_id")
    private ListaCompras lista;

    @Enumerated(EnumType.STRING)
    private UnidadeMedida unidadeMedida;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private boolean comprado;
}