/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.modelo.dao;

import br.edu.ifrs.modelo.bean.Perfil;
import br.edu.ifrs.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DTI
 */
public class PerfilDAO {
    
    public static void adicionar(Perfil p) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            try {
                /* Conectar no banco de dados */
                con = Conexao.abrirConexao();

                /* Preprar a sentença SQL */
                pstmt = con.prepareStatement("insert into perfil (nome, descricao, situacao) values (?, ?, ?)");
                pstmt.setString(1, p.getNome());
                pstmt.setString(2, p.getDescricao());
                pstmt.setString(3, p.getSituacao());

                /* Executar a sentença no banco de dados */
                pstmt.execute();
            } catch (Exception e) {
                throw new Exception("Falha ao inserir o perfil no Banco de Dados.<br><!--" + e.getMessage() + "-->");
            } finally {
                pstmt.close();
                con.close();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void atualizar(Perfil p) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            try {
                /* Conectar no banco de dados */
                con = Conexao.abrirConexao();

                /* Preprar a sentença SQL */
                pstmt = con.prepareStatement("update perfil set nome = ?, descricao = ?, situacao = ? where id = ?");
                pstmt.setString(1, p.getNome());
                pstmt.setString(2, p.getDescricao());
                pstmt.setString(3, p.getSituacao());
                pstmt.setInt(4, p.getId());

                /* Executar a sentença no banco de dados */
                pstmt.execute();
            } catch (Exception e) {
                throw new Exception("Falha ao inserir o perfil no Banco de Dados.<br><!--" + e.getMessage() + "-->");
            } finally {
                pstmt.close();
                con.close();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public static Perfil[] consultar (String nome) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Perfil> lista = new ArrayList();
        
        try {
            try {
                con = Conexao.abrirConexao();

                /* Preprar a sentença SQL */
                pstmt = con.prepareStatement("select * from perfil where nome like ?");
                pstmt.setString(1, (nome == null ? "%":"%"+nome+"%"));
                
                /* Executar a sentença no banco de dados */
                rs = pstmt.executeQuery();
                while (rs.next() == true) {
                    Perfil p = new Perfil();
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setDescricao(rs.getString("descricao"));
                    p.setSituacao(rs.getString("situacao"));
                    
                    lista.add(p);                    
                }
            } catch (Exception e) {
                throw new Exception("Falha ao inserir o perfil no Banco de Dados.<br><!--" + e.getMessage() + "-->");
            } finally {
                pstmt.close();
                con.close();
            }
        } catch (Exception ex) {
            throw ex;
        }
        
        return lista.toArray(new Perfil[0]);
    }
    
    public static Perfil consultar (int id) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Perfil p = new Perfil();
        
        try {
            try {
                con = Conexao.abrirConexao();

                /* Preprar a sentença SQL */
                pstmt = con.prepareStatement("select * from perfil where id = ?");
                pstmt.setInt(1, id);
                
                /* Executar a sentença no banco de dados */
                rs = pstmt.executeQuery();
                if (rs.next() == true) {
                    p.setId(rs.getInt("id"));
                    p.setNome(rs.getString("nome"));
                    p.setDescricao(rs.getString("descricao"));
                    p.setSituacao(rs.getString("situacao"));
                }
            } catch (Exception e) {
                throw new Exception("Falha ao inserir o perfil no Banco de Dados.<br><!--" + e.getMessage() + "-->");
            } finally {
                pstmt.close();
                con.close();
            }
        } catch (Exception ex) {
            throw ex;
        }
        
        return p;
    }
    
    public static void deletar(Perfil p) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            try {
                /* Conectar no banco de dados */
                con = Conexao.abrirConexao();

                /* Preprar a sentença SQL */
                pstmt = con.prepareStatement("delete from perfil where id = ?");
                pstmt.setInt(1, p.getId());
                
                /* Executar a sentença no banco de dados */
                pstmt.execute();
            } catch (Exception e) {
                throw new Exception("Falha ao inserir o perfil no Banco de Dados.<br><!--" + e.getMessage() + "-->");
            } finally {
                pstmt.close();
                con.close();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

}
