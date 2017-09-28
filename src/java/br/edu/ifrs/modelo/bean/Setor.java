/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.modelo.bean;

/**
 *
 * @author DAP
 */
public class Setor {
    private int id;
    private String nome;
    private Setor pai;

    /**
     * @author gustavo
     * @param id
     * @param nome
     * @param pai 
     */
    public Setor(int id, String nome, Setor pai) {
        this.id = id;
        this.nome = nome;
        this.pai = pai;
    }   

    public Setor() {
        this.id = 0;
        this.nome = "";
        this.pai = null;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the pai
     */
    public Setor getPai() {
        return pai;
    }

    /**
     * @param pai the pai to set
     */
    public void setPai(Setor pai) {
        this.pai = pai;
    }
}
