package com.supermarket.lista_de_supermercado.model.enums;

public enum UnidadeMedida {
    UNIDADE("un"),
    QUILOGRAMA("kg"),
    LITRO("l");
    private final String abreviacao;
    UnidadeMedida(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public String formatarQuantidade(double quantidade) {
        switch (this) {
            case QUILOGRAMA:
                return String.format("%.2f kg", quantidade);
            case LITRO:
                return String.format("%.2f l", quantidade);
            case UNIDADE:
            default:
                return String.format("%d un", (int) quantidade);
        }
    }

    public double converterParaUnidadeBase(double quantidade) {
        switch (this) {
            case QUILOGRAMA:
                return quantidade * 1000; // converte para gramas
            case LITRO:
                return quantidade * 1000; // converte para mililitros
            case UNIDADE:
            default:
                return quantidade;
        }
    }
}
