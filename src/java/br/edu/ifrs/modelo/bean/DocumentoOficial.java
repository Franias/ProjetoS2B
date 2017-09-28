/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.modelo.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author cassiano
 */
public class DocumentoOficial {

    private int numero;
    private int ano;
    private Date dataEmissao;
    private String tipo;
    private String conteudo;
    private String anexo;
    private Usuario usuario;

    /**
     * Uso na busca do banco de dados
     *
     * @param numero
     * @param ano
     * @param dataEmissao
     * @param tipo
     * @param conteudo
     * @param anexo
     * @param usuario
     */
    public DocumentoOficial(int numero, int ano, Date dataEmissao, String tipo,
            String conteudo, String anexo, Usuario usuario) {
        this.numero = numero;
        this.ano = ano;
        this.dataEmissao = dataEmissao;
        this.tipo = tipo;
        this.conteudo = conteudo;
        this.anexo = anexo;
        this.usuario = usuario;
    }

    public DocumentoOficial() throws Exception{
        
        this.numero = 1;
        this.ano = 2017;                     
        this.dataEmissao = new Date();       
        this.tipo = "";
        this.conteudo = "";
        this.anexo = "";
        this.usuario = new Usuario();
        
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConteudo() {
        return conteudo;
    }

    /**
     *
     * @param conteudo conteúdo do documento
     */
    public void setConteudo(String conteudo) {

        if (!conteudo.trim().isEmpty()) {
            this.conteudo = conteudo;
        } else {
            throw new IllegalArgumentException("Campo conteúdo é obrigatório");
        }

    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * 
     * @return objeto do documento para exibir no PDF
     */
    @Override
    public String toString() {
        
        //Criando uma nova data para PDF
        Date dataAtual = new Date();
        //definindo o formado da data
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");       
        //Isso é o que será exibido no PDF
        return "\nDados do documento:"
                + "\nNúmero do documento: " + numero
                + "\nAno: " + ano
                + "\nData de emissao: " + formatador.format(dataAtual)
                + "\nTipo: " + tipo
                + "\nConteudo: " + conteudo
                + "\nAnexos: " + anexo;
    }

}
