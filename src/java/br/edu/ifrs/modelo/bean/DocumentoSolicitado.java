/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.modelo.bean;

import br.edu.ifrs.excecoes.DataInvalidaException;
import br.edu.ifrs.util.ValidacaoServidor;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Gustavo
 */
public class DocumentoSolicitado {

    //falta fazer validacao, chamar classe do pacote util
    private String objetivoDocumento;
    private Date dataSolicitacao;
    private Date dataDocumento;
    private String conteudoDocumento;
    private String anexos;
    private String situacao;
    private int id;
    private Usuario servidorSolicitante;

    public DocumentoSolicitado(String objetivoDocumento, Date dataSolicitacao,
            Date dataDocumento, String conteudoDocumento, Usuario servidorSolicitante,
            String anexos, String situacao) {
        this.objetivoDocumento = objetivoDocumento;
        this.dataSolicitacao = dataSolicitacao;
        this.dataDocumento = dataDocumento;
        this.conteudoDocumento = conteudoDocumento;
        this.anexos = anexos;
        this.situacao = situacao;
    }

    /**
     * Consulta e remoção
     *
     * @param id
     */
    public DocumentoSolicitado(int id) {
        this.id = id;
    }

    public DocumentoSolicitado() {
        //incializar atributos
        objetivoDocumento = "";
        conteudoDocumento = "";
        anexos = "";
        //situação inicial pendente
        situacao = "Pendente";
        id = 0;
        servidorSolicitante = new Usuario();
    }

    /**
     * @return the objetivoDocumento
     */
    public String getObjetivoDocumento() {
        return objetivoDocumento;
    }

    /**
     * @param objetivoDocumento the objetivoDocumento to set
     */
    public void setObjetivoDocumento(String objetivoDocumento) throws IllegalArgumentException {

        if (!objetivoDocumento.trim().isEmpty()) {
            this.objetivoDocumento = objetivoDocumento;
        } else {
            throw new IllegalArgumentException("Campo objetivo do documento não pode ficar em bracno!");
        }

    }

    /**
     * @return the dataSolicitacao
     */
    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    /**
     * @param dataSolicitacao the dataSolicitacao to set
     */
    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    /**
     * Busca do banco de dados
     *
     * @param dataDocumento
     */
    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
    }

    /**
     * @param dia
     * @param mes
     * @param ano
     * @throws br.edu.ifrs.excecoes.DataInvalidaException
     */
    public void setDataDocumento(String dia,
            String mes, String ano) throws DataInvalidaException, Exception {
        //para validação
        int diaDoc = Integer.parseInt(dia);
        int mesDoc = Integer.parseInt(mes);
        int anoDoc = Integer.parseInt(ano);

        //validando data
        if (!ValidacaoServidor.validarData(diaDoc, mesDoc, anoDoc)) {

            throw new DataInvalidaException("Data inválida");

        }

        if (!ValidacaoServidor.validarDataSolicitacao(diaDoc,
                mesDoc, anoDoc)) {

            throw new DataInvalidaException("Data do documento "
                    + "não deve ser inferior a data corrente da solicitação.");

        }

        //data para o banco de dados
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date data = sdf.parse(dia + "/" + mes + "/" + ano);

        this.dataDocumento = data;
    }

    /**
     * @return the dataDocumento
     */
    public Date getDataDocumento() {
        return dataDocumento;
    }

    /**
     * @return the conteudoDocumento
     */
    public String getConteudoDocumento() {
        return conteudoDocumento;
    }

    public void setConteudoDocumento(String conteudoDocumento) throws IllegalArgumentException {
        
        if (!conteudoDocumento.trim().isEmpty()) {
            this.conteudoDocumento = conteudoDocumento;
        } else {
            throw new IllegalArgumentException("Campo Conteudo Solicitado não pode ficar em bracno!");
        }
    }

    /**
     * @return the anexos
     */
    public String getAnexos() {
        return anexos;
    }

    public void setAnexos(String anexos) {
        this.anexos = anexos;
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
    public void setSituacao(String situacao) throws Exception {
        this.situacao = situacao;

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

    public Usuario getServidorSolicitante() {
        return servidorSolicitante;
    }

    public void setServidorSolicitante(Usuario servidorSolicitante) {
        this.servidorSolicitante = servidorSolicitante;
    }

    /**
     *
     * @return Envio do email de notificação
     */
    @Override
    public String toString() {
        return "Dados do documento solicidado: "
                + "\nObjetivo do documento: " + objetivoDocumento
                + "\nData do documento: " + dataDocumento
                + "\nConteúdo: "
                + conteudoDocumento
                + "\nAnexos: " + anexos
                + "\nSituacao: " + situacao
                + "\nCPF do servidor: "
                + servidorSolicitante.getCpf();
    }

}
