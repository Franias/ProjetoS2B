/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.modelo.bean;

import br.edu.ifrs.util.ValidacaoServidor;

/**
 *
 * @author DTI
 */
public class Usuario {
    private String cpf;
    private String nome;
    private String matricula;
    private String sexo;
    private String endereco;
    private String email; 
    private String telefoneResidencial; 
    private String telefoneProfissional;
    private String telefoneCelular;
    private Perfil perfil;
    private Setor setor;
    private String username;
    private String senha; 
    private String situacao;
    private String observacoes;
    
    public Usuario() {
        this.cpf = "";
        this.nome = "Novo usuário";
        this.matricula = "0000000";
        this.sexo = "";
        this.endereco = "";
        this.email = "";
        this.telefoneResidencial = ""; 
        this.telefoneProfissional = "";
        this.telefoneCelular = "";
        this.perfil = new Perfil();
        this.setor = new Setor();
        this.username = "";
        this.senha = "";
        this.situacao = "";
        this.observacoes = "";
    }

    /**
     * 
     * @param cpf
     * @param nome
     * @param matricula
     * @param sexo
     * @param endereco
     * @param email
     * @param telefoneResidencial
     * @param telefoneProfissional
     * @param telefoneCelular
     * @param perfil
     * @param setor
     * @param username
     * @param senha
     * @param situacao
     * @param observacoes 
     * @author gustavo
     */
    public Usuario(String cpf, String nome, String matricula, String sexo, String endereco, String email, String telefoneResidencial, String telefoneProfissional, String telefoneCelular, Perfil perfil, Setor setor, String username, String senha, String situacao, String observacoes) {
        this.cpf = cpf;
        this.nome = nome;
        this.matricula = matricula;
        this.sexo = sexo;
        this.endereco = endereco;
        this.email = email;
        this.telefoneResidencial = telefoneResidencial;
        this.telefoneProfissional = telefoneProfissional;
        this.telefoneCelular = telefoneCelular;
        this.perfil = perfil;
        this.setor = setor;
        this.username = username;
        this.senha = senha;
        this.situacao = situacao;
        this.observacoes = observacoes;
    }

    /**
     * Login
     * @param username
     * @param senha 
     */
    public Usuario(String username, String senha) {
        this.username = username;
        this.senha = senha;
    }       

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) throws Exception {
        if(ValidacaoServidor.validarCPF(cpf)){
        this.cpf = cpf;
        }else{
            throw new Exception("CPF Inválido!");
        }
    
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
     * @return the matricula
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(String matricula) throws Exception {
        if(matricula.length() == 8){
        this.matricula = matricula;
        }else{
            throw new Exception("Matricula Inválida!");
        }
    }
    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the telefoneResidencial
     */
    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    /**
     * @param telefoneResidencial the telefoneResidencial to set
     */
    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    /**
     * @return the telefoneProfissional
     */
    public String getTelefoneProfissional() {
        return telefoneProfissional;
    }

    /**
     * @param telefoneProfissional the telefoneProfissional to set
     */
    public void setTelefoneProfissional(String telefoneProfissional) {
        this.telefoneProfissional = telefoneProfissional;
    }

    /**
     * @return the telefoneCelular
     */
    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    /**
     * @param telefoneCelular the telefoneCelular to set
     */
    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    /**
     * @return the perfil
     */
    public Perfil getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the setor
     */
    public Setor getSetor() {
        return setor;
    }

    /**
     * @param setor the setor to set
     */
    public void setSetor(Setor setor) {
        this.setor = setor;
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public void setSenha(String s, String cs) throws Exception {
        if (s == null || s.equals("")) {
            throw new Exception ("Senha deve ser preenchida com um valor numérico.");
        }
        if (!s.equals(cs)) {
            throw new Exception ("Os campos senha e confirmação de senha devem ser idênticos.");
        }
    
        setSenha(s);
    }

    /**
     * @return the situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the observacoes
     */
    public String getObservacoes() {
        return observacoes;
    }

    /**
     * @param observacoes the observacoes to set
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
