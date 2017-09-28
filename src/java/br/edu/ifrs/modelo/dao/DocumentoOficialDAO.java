/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.modelo.dao;

import br.edu.ifrs.modelo.bean.DocumentoOficial;
import br.edu.ifrs.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author gustavo
 */
public class DocumentoOficialDAO {
    
    /**
     * 
     * @param d objeto do documento oficial
     * @throws Exception 
     */
    public static void /*adicionar*/ enviarDocumento(DocumentoOficial d) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            try {
                /* Conectar no banco de dados */
                con = Conexao.abrirConexao();

                /* Preprar a sentença SQL */
                pstmt = con.prepareStatement("insert into documentos_oficiais "
                        + "(numero, ano, tipo, data_emissao, conteudo, anexos, cpf_emissor ) "
                        + "values (?, ?, ?, ?, ?, ?, ?); ");
                
                pstmt.setInt(1, d.getNumero());
                pstmt.setInt(2, d.getAno());
                pstmt.setString(3, d.getTipo());
                //Pega a data do sistema e insere no sistema
                pstmt.setDate(4, new java.sql.Date(d.getDataEmissao().getTime()));                
                pstmt.setString(5, d.getConteudo());
                pstmt.setString(6, d.getAnexo());
                pstmt.setString(7, d.getUsuario().getCpf());
                
                
                /* Executar a sentença no banco de dados */
                pstmt.execute();
            } catch (Exception e) {
                throw new Exception("Falha ao Enviar Oficial para o Banco de Dados." + e.getMessage());
            } finally {
                pstmt.close();
                con.close();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    /**
     * 
     * @param palavraChave
     * @param periodoInicial
     * @param periodoFinal
     * @param tipo
     * @return
     * @throws Exception 
     */
    public static DocumentoOficial[] consultar(String palavraChave, String periodoInicial,
            String periodoFinal, String tipo) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<DocumentoOficial> lista = new ArrayList();
                
        try {
            try {
                con = Conexao.abrirConexao();
                
                pstmt = con.prepareStatement("select * " +
                                            "from documentos_oficiais d " +
                                            "where d.conteudo like ? " +
                                            "and d.data_emissao >= ? " +
                                            "and d.data_emissao <= ? " +
                                            "or d.tipo = ?; " ); 
                         
                pstmt.setString(1, ("%"+palavraChave+"%"));
                pstmt.setString(2, periodoInicial);
                pstmt.setString(3, periodoFinal);
                pstmt.setString(4, (tipo));
                //executar consulta
                rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    DocumentoOficial d = new DocumentoOficial();
                    //número, ano, data de emissão, tipo do documento
                    d.setNumero(rs.getInt("numero"));                    
                    d.setAno(rs.getInt("ano"));
                    d.setDataEmissao(rs.getDate("data_emissao"));
                    d.setTipo(rs.getString("tipo"));
                    lista.add(d);
                }//fecha while
                
            } catch (Exception e) {
                throw new Exception ("Falha ao buscar o documento no banco de dados." + e.getMessage());
            } finally {
                pstmt.close();
                con.close();
            }
        } catch (Exception ex) {
            throw ex;
        }
        
        return lista.toArray(new DocumentoOficial[0]);
    }//fecha método
    
}//fecha classe
