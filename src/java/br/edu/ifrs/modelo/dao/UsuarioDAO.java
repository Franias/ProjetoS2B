/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.modelo.dao;

import br.edu.ifrs.modelo.bean.Perfil;
import br.edu.ifrs.modelo.bean.Setor;
import br.edu.ifrs.modelo.bean.Usuario;
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
public class UsuarioDAO {

    public static void adicionar(Usuario u) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            try {
                /* Conectar no banco de dados */
                con = Conexao.abrirConexao();

                /* Preprar a sentença SQL */
                pstmt = con.prepareStatement("insert into usuarios (cpf, siape, nome, sexo, endereco, email, telefone, username, senha, situacao, observacoes, id_perfil, id_setor) values (?, ?, ?, ?, ?, ?, ?, ?, MD5(?), ?, ?, ?, ?)");
                pstmt.setString(1, u.getCpf());
                pstmt.setString(2, u.getMatricula());
                pstmt.setString(3, u.getNome());
                pstmt.setString(4, u.getSexo());
                pstmt.setString(5, u.getEndereco());
                pstmt.setString(6, u.getEmail());
                pstmt.setString(7, u.getTelefoneResidencial());
                pstmt.setString(8, u.getUsername());
                pstmt.setString(9, u.getSenha());
                pstmt.setString(10, u.getSituacao());
                pstmt.setString(11, u.getObservacoes());
                pstmt.setInt(12, u.getPerfil().getId());
                pstmt.setInt(13, u.getSetor().getId());

                /* Executar a sentença no banco de dados */
                pstmt.execute();
            } catch (Exception e) {
                throw new Exception("Falha ao inserir o usuário no Banco de Dados. " + e.getMessage());
            } finally {
                pstmt.close();
                con.close();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static void atualizar(Usuario u) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            try {
                /* Conectar no banco de dados */
                con = Conexao.abrirConexao();

                /* Preprar a sentença SQL */
                pstmt = con.prepareStatement("update usuarios set siape = ?, nome = ?, sexo = ?, endereco = ?, email = ?, telefone = ?, username = ?, situacao = ?, observacoes = ?, id_perfil = ?, id_setor = ? where cpf = ?");
                pstmt.setString(1, u.getMatricula());
                pstmt.setString(2, u.getNome());
                pstmt.setString(3, u.getSexo());
                pstmt.setString(4, u.getEndereco());
                pstmt.setString(5, u.getEmail());
                pstmt.setString(6, u.getTelefoneResidencial());
                pstmt.setString(7, u.getUsername());
                pstmt.setString(8, u.getSituacao());
                pstmt.setString(9, u.getObservacoes());
                pstmt.setInt(10, u.getPerfil().getId());
                pstmt.setInt(11, u.getSetor().getId());
                pstmt.setString(12, u.getCpf());

                /* Executar a sentença no banco de dados */
                pstmt.execute();
            } catch (Exception e) {
                throw new Exception("Falha ao atualizar o usuário no Banco de Dados." + e.getMessage());
            } finally {
                pstmt.close();
                con.close();
            }
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static Usuario[] pesquisar(String nome) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Usuario> lista = new ArrayList();

        try {
            try {
                con = Conexao.abrirConexao();

                /* Preprar a sentença SQL */
                pstmt = con.prepareStatement("select * from usuarios where nome like ?");
                pstmt.setString(1, (nome == null ? "%" : "%" + nome + "%"));

                /* Executar a sentença no banco de dados */
                rs = pstmt.executeQuery();
                while (rs.next() == true) {
                    //username, senha, situacao, observacoes, id_perfil
                    Usuario u = new Usuario();
                    u.setCpf(rs.getString("cpf"));
                    u.setMatricula(rs.getString("siape"));
                    u.setNome(rs.getString("nome"));
                    u.setSexo(rs.getString("sexo"));
                    u.setEndereco(rs.getString("endereco"));
                    u.setEmail(rs.getString("email"));
                    u.setTelefoneResidencial(rs.getString("telefone"));
                    u.setUsername(rs.getString("username"));
                    u.setSenha(rs.getString("senha"));
                    u.setObservacoes(rs.getString("observacoes"));
                    u.setSituacao(rs.getString("situacao"));
                    Perfil p = new Perfil();
                    p.setId(rs.getInt("id_perfil"));
                    u.setPerfil(p);
                    Setor s = new Setor();
                    s.setId(rs.getInt("id_setor"));
                    u.setSetor(s);

                    lista.add(u);
                }
            } catch (Exception e) {
                throw new Exception("Falha ao consultar o usuário no Banco de Dados." + e.getMessage());
            } finally {
                pstmt.close();
                con.close();
            }
        } catch (Exception ex) {
            throw ex;
        }

        return lista.toArray(new Usuario[0]);
    }

    public static Usuario consultar(String cpf) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Usuario u = new Usuario();

        try {
            try {
                con = Conexao.abrirConexao();

                /* Preprar a sentença SQL */
                pstmt = con.prepareStatement("select * from usuarios where cpf = ?");
                pstmt.setString(1, cpf);

                /* Executar a sentença no banco de dados */
                rs = pstmt.executeQuery();
                if (rs.next() == true) {
                    u.setCpf(rs.getString("cpf"));
                    u.setMatricula(rs.getString("siape"));
                    u.setNome(rs.getString("nome"));
                    u.setSexo(rs.getString("sexo"));
                    u.setEndereco(rs.getString("endereco"));
                    u.setEmail(rs.getString("email"));
                    u.setTelefoneResidencial(rs.getString("telefone"));
                    u.setUsername(rs.getString("username"));
                    u.setSenha(rs.getString("senha"));
                    u.setObservacoes(rs.getString("observacoes"));
                    u.setSituacao(rs.getString("situacao"));
                    Perfil p = new Perfil();
                    p.setId(rs.getInt("id_perfil"));
                    u.setPerfil(p);
                    Setor s = new Setor();
                    s.setId(rs.getInt("id_setor"));
                    u.setSetor(s);
                }
            } catch (Exception e) {
                throw new Exception("Falha ao pesquisar o usuário no Banco de Dados." + e.getMessage());
            } finally {
                pstmt.close();
                con.close();
            }
        } catch (Exception ex) {
            throw ex;
        }

        return u;
    }

    public static void deletar(Usuario u) throws Exception {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            try {
                /* Conectar no banco de dados */
                con = Conexao.abrirConexao();

                /* Preprar a sentença SQL */
                pstmt = con.prepareStatement("delete from usuarios where cpf = ?");
                pstmt.setString(1, u.getCpf());

                /* Executar a sentença no banco de dados */
                pstmt.execute();
            } catch (Exception e) {
                throw new Exception("Falha ao deletar o usuário no Banco de Dados." + e.getMessage());
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
     * @param login login do usuário
     * @return erro de exceção
     * @throws Exception 
     */
    public static Usuario autenticar(Usuario login) throws Exception {

        Connection con = null;
        PreparedStatement ps = null;
        Usuario u = null;

        try {

            try {
                
                con = Conexao.abrirConexao();
                //senha criptografada no banco de dados
                String sql = "select * from usuarios where username = ? and senha = MD5(?)";
                
                ps = con.prepareStatement(sql);
                //usuário e senha são os parâmetros consulta
                ps.setString(1, login.getUsername());
                ps.setString(2, login.getSenha());
                
                //vamos preencher um objeto do usuário
                ResultSet rs = ps.executeQuery();
                
                if(rs.next()){
                    //objeto preenchido
                    u = new Usuario();
                    
                    u.setCpf(rs.getString("cpf"));
                    u.setMatricula(rs.getString("siape"));
                    u.setNome(rs.getString("nome"));
                    u.setSexo(rs.getString("sexo"));
                    u.setEndereco(rs.getString("endereco"));
                    u.setEmail(rs.getString("email"));
                    u.setTelefoneResidencial(rs.getString("telefone"));
                    u.setUsername(rs.getString("username"));
                    u.setSenha(rs.getString("senha"));
                    u.setObservacoes(rs.getString("observacoes"));
                    u.setSituacao(rs.getString("situacao"));
                    //objeto do perfil e do setor
                    Perfil p = new Perfil();
                    p = PerfilDAO.consultar(rs.getInt("id_perfil"));
                    u.setPerfil(p);
                    
                    Setor s = new Setor();
                    s =  SetorDAO.consultar(rs.getInt("id_setor"));
                    u.setSetor(s);
                    
                }

            } catch (Exception e) {

                throw new Exception("Falha ao autenticar o usuário no Banco de Dados." 
                        + e.getMessage());
                
            } finally {
                ps.close();
                con.close();
            }

        } catch (Exception ex) {
            throw ex;
        }

        return u;

    }
}
