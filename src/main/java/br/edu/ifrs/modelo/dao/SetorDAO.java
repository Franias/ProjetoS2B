/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.modelo.dao;

import br.edu.ifrs.modelo.bean.Setor;
import br.edu.ifrs.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DAP
 */
public class SetorDAO {
    public static void adicionar(Setor s) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            try {
                /* Conectar no banco de dados */
                con = Conexao.abrirConexao();

                /* Preprar a sentença SQL */
                pstmt = con.prepareStatement("insert into setores (nome, id_setor_pai) values (?, ?)");
                pstmt.setString(1, s.getNome());
                pstmt.setInt(2, s.getPai().getId());
                
                /* Executar a sentença no banco de dados */
                pstmt.execute();
            } catch (Exception e) {
                throw new Exception("Falha ao inserir o setor no Banco de Dados.<br><!--" + e.getMessage() + "-->");
            } finally {
                pstmt.close();
                con.close();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void atualizar(Setor s) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            try {
                /* Conectar no banco de dados */
                con = Conexao.abrirConexao();

                /* Preprar a sentença SQL */
                pstmt = con.prepareStatement("update setores set nome = ?, id_setor_pai = ? where id = ?");
                pstmt.setString(1, s.getNome());
                pstmt.setInt(2, s.getPai().getId());
                pstmt.setInt(3, s.getId());

                /* Executar a sentença no banco de dados */
                pstmt.execute();
            } catch (Exception e) {
                throw new Exception("Falha ao inserir o setor no Banco de Dados.<br><!--" + e.getMessage() + "-->");
            } finally {
                pstmt.close();
                con.close();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }
    
    public static Setor[] consultar (String nome) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Setor> lista = new ArrayList();
        
        try {
            try {
                con = Conexao.abrirConexao();

                /* Preprar a sentença SQL */
                pstmt = con.prepareStatement("select a.id as id, a.nome as nome, b.id as id_pai, b.nome as nome_pai from setores a, setores b where a.nome like ? and a.id_setor_pai = b.id");
                pstmt.setString(1, (nome == null ? "%":"%"+nome+"%"));
                
                /* Executar a sentença no banco de dados */
                rs = pstmt.executeQuery();
                while (rs.next() == true) {
                    Setor s = new Setor();
                    s.setId(rs.getInt("id"));
                    s.setNome(rs.getString("nome"));
                    
                    Setor pai = new Setor();
                    pai.setId(rs.getInt("id_pai"));
                    pai.setNome(rs.getString("nome_pai"));
                    
                    s.setPai(pai);
                    
                    lista.add(s);                    
                }
            } catch (Exception e) {
                throw new Exception("Falha ao inserir o setor no Banco de Dados.<br><!--" + e.getMessage() + "-->");
            } finally {
                pstmt.close();
                con.close();
            }
        } catch (Exception ex) {
            throw ex;
        }
        
        return lista.toArray(new Setor[0]);
    }
    
    public static Setor consultar (int id) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Setor s = new Setor();
        
        try {
            try {
                con = Conexao.abrirConexao();

                /* Preprar a sentença SQL */
                pstmt = con.prepareStatement("select * from setores where id = ?");
                pstmt.setInt(1, id);
                
                /* Executar a sentença no banco de dados */
                rs = pstmt.executeQuery();
                if (rs.next() == true) {
                    s.setId(rs.getInt("id"));
                    s.setNome(rs.getString("nome"));
                    
                    Setor pai = new Setor();
                    pai.setId(rs.getInt("id_setor_pai"));
                    
                    s.setPai(pai);
                }
            } catch (Exception e) {
                throw new Exception("Falha ao inserir o setor no Banco de Dados.<br><!--" + e.getMessage() + "-->");
            } finally {
                pstmt.close();
                con.close();
            }
        } catch (Exception ex) {
            throw ex;
        }
        
        return s;
    }
    
    public static void deletar(Setor s) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
            try {
                /* Conectar no banco de dados */
                con = Conexao.abrirConexao();

                /* Preprar a sentença SQL */
                pstmt = con.prepareStatement("delete from setores where id = ?");
                pstmt.setInt(1, s.getId());
                
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
