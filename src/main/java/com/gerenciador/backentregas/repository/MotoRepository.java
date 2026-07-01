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
public List<MotoDTO> listaMoto() {
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
    public int registerMototista(MotoDTO moto) {
         int linhas =0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("insert into motoristas (usuario_id, nome, status) values (?, ?, ?)");
            stmt.setLong(1, moto.getUsuario_id());
            stmt.setString(2, moto.getNome());
            stmt.setString(3, moto.getStatus());
            
            linhas = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return linhas;
    }
    public MotoDTO buscarPorId(Long id) {
        MotoDTO moto = null;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt =
            conn.prepareStatement("select * from motoristas where id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            moto = new MotoDTO();
            moto.setId(rs.getLong("id"));
            moto.setUsuario_id(rs.getLong("usuario_id"));
            moto.setNome(rs.getString("nome"));
            moto.setStatus(rs.getString("status"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return moto;
}
    public int deleteById(long id){
        int linhas =0;
     try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("delete from motoristas Where id=?");
            stmt.setLong(1, id);
            linhas=stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }   
     return linhas;
    }
public int update(MotoDTO moto) {
    int linhas = 0;
    try {
        Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement("update motoristas set usuario_id=?, nome=?, status=? where id=?");
            stmt.setLong(1, moto.getUsuario_id());
            stmt.setString(2, moto.getNome());
            stmt.setString(3, moto.getStatus());
            stmt.setLong(4, moto.getId());
            linhas = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return linhas;
    }    
}
