/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.modelo.dao;

import br.edu.ifrs.modelo.bean.DocumentoSolicitado;
import br.edu.ifrs.modelo.bean.Usuario;
import br.edu.ifrs.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Calendar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gustavo
 */
public class DocumentoSolicitadoDAO {

    /**
     * 
     * @param d objeto para inserir
     * @throws Exception 
     */
    public static void /*adicionar*/ enviarSolicitacao(
                    DocumentoSolicitado d) throws Exception {

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            try {
                /* Conectar no banco de dados */
                con = Conexao.abrirConexao();

                /* Preprar a sentença SQL */
                pstmt = con.prepareStatement("insert into solicitacao_documentos "
                        + "(objetivo, data_solicitacao, data_documento, conteudo_documento,"
                        + " situacao, anexos, cpf_servidor ) "
                        + "values (?, ?, ?, ?, ?, ?, ?)");

                pstmt.setString(1, d.getObjetivoDocumento());
                pstmt.setDate(2, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
                pstmt.setDate(3, new java.sql.Date(d.getDataDocumento().getTime()));
                pstmt.setString(4, d.getConteudoDocumento());
                pstmt.setString(5, d.getSituacao());
                pstmt.setString(6, d.getAnexos());
                //buscar cpf do servidor
                pstmt.setString(7, d.getServidorSolicitante().getCpf());

                /* Executar a sentença no banco de dados */
                pstmt.execute();
            } catch (SQLException | ClassNotFoundException e) {
                throw new Exception("Falha ao Enviar Solicitação para o Banco de Dados." + e.getMessage());
            } finally {
                pstmt.close();
                con.close();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }//fecha método

    /**
     * Campos da busca
     * @param nome
     * @param dataInicio
     * @param dataFim
     * @param situacao
     * @return
     * @throws Exception 
     */
    public static DocumentoSolicitado[] consultar(String nome, String dataInicio,
            String dataFim, String situacao) throws Exception {

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        //array com os dados
        List<DocumentoSolicitado> lista = new ArrayList();

        try {
            try {
                con = Conexao.abrirConexao();

                pstmt = con.prepareStatement("select * "
                        + "from solicitacao_documentos s "
                        + "join usuarios u "
                        + "on s.cpf_servidor = u.cpf "
                        + "where u.nome like ? "
                        + "and data_solicitacao >= ? "
                        + "and data_solicitacao <= ? "
                        + "and s.situacao like ?;");
                //testa se o nome não esta vazio
                pstmt.setString(1, (nome == null ? "%" : "%" + nome + "%"));
                pstmt.setString(2, dataInicio);
                pstmt.setString(3, dataFim);
                pstmt.setString(4, "%" + situacao + "%");

                rs = pstmt.executeQuery();
                // número da solicitação, nome do solicitante, data da solicitação, situação (pendente, concluída, cancelada), objetivo do documento e conteúdo do documento.
                while (rs.next()) {
                    DocumentoSolicitado d = new DocumentoSolicitado();
                    d.setId(rs.getInt("id_solicitacao"));
                    Usuario user = new Usuario();
                    user.setNome(rs.getString("nome"));
                    d.setServidorSolicitante(user);
                    d.setDataSolicitacao(rs.getDate("data_solicitacao"));
                    d.setSituacao(rs.getString("situacao"));
                    d.setObjetivoDocumento(rs.getString("objetivo"));
                    d.setConteudoDocumento(rs.getString("conteudo_documento"));
                    d.setAnexos(rs.getString("anexos"));
                    lista.add(d);
                    //System.out.print(d.getServidorSolicitante().getNome());
                }
            } catch (Exception e) {
                throw new Exception("Falha ao buscar o documento no banco de dados." + e.getMessage());
            } finally {
                pstmt.close();
                con.close();
            }
        } catch (Exception ex) {
            throw ex;
        }

        return lista.toArray(new DocumentoSolicitado[0]);
    }

    /**
     * Consultar Solicitacao documento expecífico
     * Usado para poder cadastrar o documento oficial
     * @param documentoSolicitado
     * @return
     * @throws Exception
     */
    public static DocumentoSolicitado consultar(DocumentoSolicitado documentoSolicitado) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs;
        //objeto que vai retornar
        DocumentoSolicitado d = new DocumentoSolicitado();

        try {
            try {
                con = Conexao.abrirConexao();

                pstmt = con.prepareStatement("select * "
                        + "from solicitacao_documentos s "
                        + "join usuarios u "
                        + "on s.cpf_servidor = u.cpf "
                        + "where  s.id_solicitacao = ?;");
                //id específica
                pstmt.setInt(1, documentoSolicitado.getId());
                //executa consulta
                rs = pstmt.executeQuery();

                // número da solicitação, nome do solicitante, data da solicitação, situação (pendente, concluída, cancelada), objetivo do documento e conteúdo do documento.
                if(rs.next()) {//testa se existe documento
                    d.setId(rs.getInt("id_solicitacao"));
                    Usuario user = new Usuario();
                    user.setNome(rs.getString("nome"));
                    d.setServidorSolicitante(user);
                    d.setDataSolicitacao(rs.getDate("data_solicitacao"));
                    d.setDataDocumento(rs.getDate("data_documento"));
                    d.setSituacao(rs.getString("situacao"));
                    d.setObjetivoDocumento(rs.getString("objetivo"));
                    d.setConteudoDocumento(rs.getString("conteudo_documento"));
                    d.setAnexos(rs.getString("anexos"));
                }
                
            } catch (Exception e) {
                throw new Exception("Falha ao buscar o documento no banco de dados." + e.getMessage());
            } finally {
                pstmt.close();
                con.close();
            }
            
        } catch (Exception ex) {
            throw ex;
        }
        return d;

    }//fecha método

    /**
     * Alterar Situação do Documento
     *
     * @param documentoSolicitado
     * @throws Exception
     */
    public static void alterarSituacao(DocumentoSolicitado documentoSolicitado) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DocumentoSolicitado d = new DocumentoSolicitado();

        try {
            try {
                con = Conexao.abrirConexao();

                pstmt = con.prepareStatement("UPDATE solicitacao_documentos SET situacao = "
                        + "'Concluida' WHERE id_solicitacao = ? ;");

                pstmt.setInt(1, documentoSolicitado.getId());

                pstmt.executeUpdate();

            } catch (Exception e) {
                throw new Exception("Falha ao alterar estatus." + e.getMessage());
            } finally {
                pstmt.close();
                con.close();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }//fecha método

    /**
     * Alterar Dados do cadastro da solicitacao
     *
     * @param documentoSolicitado
     * @throws Exception
     */
    public static void alterarSolicitacao(DocumentoSolicitado documentoSolicitado) throws Exception {

        Connection con = null;
        PreparedStatement pstmt = null;
        DocumentoSolicitado d = new DocumentoSolicitado();

        try {
            try {

                con = Conexao.abrirConexao();

                pstmt = con.prepareStatement("UPDATE solicitacao_documentos "
                        + "SET objetivo = ?,data_documento = ?,conteudo_documento = ?,anexos = ?"
                        + " WHERE id_solicitacao = ? ;");

                pstmt.setString(1, documentoSolicitado.getObjetivoDocumento());
                pstmt.setDate(2, new java.sql.Date(documentoSolicitado.getDataDocumento().getTime()));
                pstmt.setString(3, documentoSolicitado.getConteudoDocumento());
                pstmt.setString(4, documentoSolicitado.getAnexos());
                pstmt.setInt(5, documentoSolicitado.getId());

                pstmt.executeUpdate();

            } catch (Exception e) {
                throw new Exception("Falha ao alterar estatus." + e.getMessage());
            } finally {
                pstmt.close();
                con.close();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }//fecha método

}//fecha classe
