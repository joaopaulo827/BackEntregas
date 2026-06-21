/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backend.backentregas.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author joaop
 */
public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/db_gerenciador";
    private static final String usuario = "root";
    private static final String senha = "";
    private static Connection conn = null;

    private Conexao() {
    }

    public static synchronized Connection conectar() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, usuario, senha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }    
}
