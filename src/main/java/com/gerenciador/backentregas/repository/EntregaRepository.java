/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gerenciador.backentregas.repository;


import com.gerenciador.backentregas.model.EntregaDTO;
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
public class EntregaRepository {
public List<EntregaDTO> listaEntregas() {
    List<EntregaDTO> lista = new ArrayList<>();
    try {
        Connection conn = Conexao.conectar();
        PreparedStatement stmt =null;
        ResultSet rs = null;
        stmt= conn.prepareStatement("select * from entrega");
        rs= stmt.executeQuery();
        while (rs.next()) {
            EntregaDTO entregas = new EntregaDTO();
            entregas.setId(rs.getLong("id"));
            entregas.setProduto(rs.getString("produto"));
            entregas.setDescricao(rs.getString("descricao"));
            entregas.setStatus(rs.getString("status"));
            lista.add(entregas);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
        return lista;
}
    public int registarEntrega(EntregaDTO entrega) {
         int linhas =0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("insert into entrega ( produto,descricao, status) values ( ?, ?, ?)");
            stmt.setString(1, entrega.getProduto());
            stmt.setString(2, entrega.getDescricao());
            stmt.setString(3, entrega.getStatus());

            int AffectedRows = stmt.executeUpdate();
            if (AffectedRows == 0) {
                throw new SQLException("Falha na atualização - Nenhuma linha foi encontrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return linhas;
    }
    public EntregaDTO buscarPorId(Long id) {
        EntregaDTO entrega = null;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt =
            conn.prepareStatement("select * from entrega where id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            entrega = new EntregaDTO();
            entrega.setId(rs.getLong("id"));
            entrega.setProduto(rs.getString("produto"));
            entrega.setDescricao(rs.getString("descricao"));
            entrega.setStatus(rs.getString("status"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return entrega;
}
    public int deleteById(long id){
        int linhas =0;
     try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("delete from entrega Where id=?");
            stmt.setLong(1, id);
            linhas=stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }   
     return linhas;
    }
    public int update(EntregaDTO entrega){
        int linhas =0;
     try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("update entrega set produto=?,descricao=?,status=? where id=?");
            stmt.setString(1, entrega.getProduto());
            stmt.setString(2, entrega.getDescricao());
            stmt.setString(3, entrega.getStatus());
            stmt.setLong(4, entrega.getId());
            linhas=stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
     return linhas;
    }
}
