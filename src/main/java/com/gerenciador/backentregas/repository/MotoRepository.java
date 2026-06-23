/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gerenciador.backentregas.repository;


import com.gerenciador.backentregas.model.MotoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joaop
 */
@Repository
public class MotoRepository {
public List<MotoDTO> listaUsuarios() {
    List<MotoDTO> lista = new ArrayList<>();
    try {
        Connection conn = Conexao.conectar();
        PreparedStatement stmt =null;
        ResultSet rs = null;
        stmt= conn.prepareStatement("select * from motoristas");
        rs= stmt.executeQuery();
        while (rs.next()) {
            MotoDTO motoristas = new MotoDTO();
            motoristas.setId(rs.getLong("id"));
            motoristas.setUsuario_id(rs.getLong("usuario_id"));
            motoristas.setNome(rs.getString("nome"));
            motoristas.setStatus(rs.getString("status"));
            lista.add(motoristas);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
        return lista;
}
    public void register(MotoDTO moto) {
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("insert into motoristas (usuario_id, nome, status) values (?, ?, ?)");
            stmt.setLong(1, moto.getUsuario_id());
            stmt.setString(2, moto.getNome());
            stmt.setString(3, moto.getStatus());

            int AffectedRows = stmt.executeUpdate();
            if (AffectedRows == 0) {
                throw new SQLException("Falha na atualização - Nenhuma linha foi encontrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    
}
