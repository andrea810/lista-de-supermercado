package com.supermarket.lista_de_supermercado.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.supermarket.lista_de_supermercado.model.Item;
import com.supermarket.lista_de_supermercado.model.ListaCompras;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {
    
    public byte[] gerarPdf(ListaCompras lista) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
            PdfWriter.getInstance(document, out);
            document.open();
            
            // Título
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            document.add(new Paragraph(lista.getTitulo(), titleFont));
            document.add(new Paragraph(" ")); // Espaço
            
            // Itens
            Font itemFont = new Font(Font.FontFamily.HELVETICA, 12);
            Font symbolFont = FontFactory.getFont("ZapfDingbats", 12);
            
            for (Item item : lista.getItens()) {
                Paragraph p = new Paragraph();
                // Checkbox usando ZapfDingbats
                Chunk checkbox = new Chunk(item.isComprado() ? "4" : "o", symbolFont);
                Chunk texto = new Chunk(" " + item.getDescricao() + " - " + 
                             item.getQuantidade(), itemFont);
                
                p.add(checkbox);
                p.add(texto);
                document.add(p);
            }
            
        } finally {
            document.close();
        }
        
        return out.toByteArray();
    }
}