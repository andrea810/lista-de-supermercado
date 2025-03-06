package com.supermarket.lista_de_supermercado.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.supermarket.lista_de_supermercado.model.Item;
import com.supermarket.lista_de_supermercado.model.ListaCompras;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.itextpdf.text.pdf.draw.LineSeparator;

@Service
public class PdfService {
    
    public byte[] gerarPdf(ListaCompras lista) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
            PdfWriter.getInstance(document, out);
            document.open();
            
            // Título
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
            Paragraph title = new Paragraph(lista.getTitulo(), titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" ")); // Espaço
            
            // Agrupar itens por categoria
            Map<String, List<Item>> itensPorCategoria = lista.getItens().stream()
                    .collect(Collectors.groupingBy(item -> item.getCategoria().toString()));
            
            // Determinar o número de colunas com base no número de categorias
            int numColunas = itensPorCategoria.size();
            if (numColunas == 0) numColunas = 1; // Pelo menos uma coluna
            
            // Criar tabela com número de colunas igual ao número de categorias
            PdfPTable table = new PdfPTable(numColunas);
            table.setWidthPercentage(100);
            
            // Definir cores
            BaseColor corRoxa = new BaseColor(148, 58, 148);
            BaseColor corVerde = new BaseColor(0, 128, 0);
            BaseColor corAzul = new BaseColor(0, 102, 204);
            BaseColor[] cores = {corRoxa, corVerde, corAzul};
            
            int colorIndex = 0;
            
            // Adicionar cabeçalhos de categoria
            for (String categoria : itensPorCategoria.keySet()) {
                PdfPCell headerCell = new PdfPCell(new Phrase(categoria, new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.WHITE)));
                headerCell.setBackgroundColor(cores[colorIndex % cores.length]);
                headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                headerCell.setPadding(8);
                table.addCell(headerCell);
                colorIndex++;
            }
            
            // Encontrar a categoria com mais itens
            int maxItens = itensPorCategoria.values().stream()
                    .mapToInt(List::size)
                    .max()
                    .orElse(0);
            
            // Adicionar itens em cada coluna
            for (int i = 0; i < maxItens; i++) {
                for (List<Item> itens : itensPorCategoria.values()) {
                    PdfPCell cell = new PdfPCell();
                    cell.setPadding(5);
                    
                    if (i < itens.size()) {
                        Item item = itens.get(i);
                        Font itemFont = new Font(Font.FontFamily.HELVETICA, 12);
                        Paragraph p = new Paragraph();
                        p.add(new Chunk("o", FontFactory.getFont("ZapfDingbats", 12)));
                        p.add(new Chunk(" " + item.getDescricao(), itemFont));
                        cell.addElement(p);
                    }
                    
                    table.addCell(cell);
                }
            }
            
            document.add(table);
            
            // Adicionar seção de observações com linhas pautadas
            document.add(new Paragraph(" ")); // Espaço
            document.add(new Paragraph(" ")); // Espaço adicional
            
            Font obsFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Paragraph obsTitle = new Paragraph("Observações:", obsFont);
            document.add(obsTitle);
            
            // Adicionar linhas pautadas
            for (int i = 0; i < 4; i++) {
                Paragraph linha = new Paragraph(" ");
                linha.add(new Chunk(Chunk.NEWLINE));
                // Adicionar linha horizontal
                linha.add(new Chunk(new LineSeparator(0.5f, 100, BaseColor.LIGHT_GRAY, Element.ALIGN_LEFT, -2)));
                document.add(linha);
            }
        } finally {
            document.close();
        }
        
        return out.toByteArray();
    }
}