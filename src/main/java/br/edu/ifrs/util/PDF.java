/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.util;

import br.edu.ifrs.modelo.bean.DocumentoOficial;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

/**
 *
 * @author gustavo
 */
public class PDF {
    //atributos para geração do documento
    private static Document doc;
    //local da geração
    //windows
    private static final String URL = "C:/Users/062013/Desktop/Módulo de emissão de documentos oficiais/web/";
    //linux
    //private static final String URL = "/home/gustavo/Área de Trabalho/java web/Módulo de emissão de documentos oficiais/web/";   
    //fonte para título
    private static final Font F = new Font(Font.FontFamily.COURIER, 20, Font.BOLD);
    
    /**
     * 
     * @param testo testo do parágrafo
     * @param alinhamento alinhamento
     * @param esp espaçamento de linhas
     * @param op 1 - título, demais para testo
     * @return parágrafo gerado
     */
    private static Paragraph  gerarParagrafo(String testo,int alinhamento,int esp,int op){
        //criar novo parágrafo
        Paragraph p = (op == 1)? new Paragraph(testo,F):new Paragraph(testo);
        //inserir alinhamento
        p.setAlignment(alinhamento);
        //espaçamento
        p.setSpacingAfter(esp);
        //retorna objeto 
        return p;
        
    }
    
    /**
     * 
     * @param url local da imagem
     * @return figura para inserir
     * @throws Exception 
     */
    private static Image inserirImagem(String url) throws Exception{
        
        Image figura = Image.getInstance(url);
        //alinhar imagem no centro
        figura.setAlignment(Image.ALIGN_CENTER);
        
        return figura;
        
    }
    
    /**
     * 
     * @param documentoOficial documento oficial a ser gerado no PDF
     * @return link do documento para download
     * @throws Exception
     */
    public static String gerarPDF(DocumentoOficial documentoOficial) throws Exception  {
        
        doc = new Document();
        
        String link = "pdf/documento"+documentoOficial.getNumero()+".pdf";
        //local do documento       
        PdfWriter.getInstance(doc, new FileOutputStream(URL + link));
        doc.open();
        //título
        doc.add(gerarParagrafo("Documento Oficial", Element.ALIGN_CENTER, 20, 1));
        //imagem
        doc.add(inserirImagem(URL + "img/logo.JPG"));
        //objeto
        doc.add(gerarParagrafo(documentoOficial.toString(),
                Element.ALIGN_LEFT, 0, 0));
                
        doc.close();
        
        return link;
        
    }
    
}//fecha classe
