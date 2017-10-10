/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifrs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *Fazer essa classe primeiro
 * @author gustavo
 */
public class Conexao {
    //dados para conexão do banco
    private static final String URL = "jdbc:mysql://localhost:3306/gabinete";//local do banco de dados
    private static final String USER = "root";//usuário e senha
    private static final String PASSWORD = "root";

    /**
     * 
     * @return conexão com o banco
     * @throws SQLException Exceção SQL
     * @throws ClassNotFoundException 
     */
    public static Connection abrirConexao() throws SQLException,
            ClassNotFoundException {

        //registra drive SQL
        Class.forName("com.mysql.jdbc.Driver");

        Connection c = null;

        try {
            //cria conexão
            c = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (Exception e) {
            throw new SQLException("Erro ao conectar: " + e.getMessage());
        }

        return c;
    }

}
