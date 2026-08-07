/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gerenciador.backentregas.repository;


import com.gerenciador.backentregas.model.EntEndDTO;
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
            entregas.setEnderecoId(rs.getLong("endereco_id"));
            entregas.setMotoristaId(rs.getLong("motorista_id"));
            lista.add(entregas);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
        return lista;
}
public int registarEntrega(EntregaDTO entrega) {

    int linhas = 0;
    try {
        Connection conn = Conexao.conectar();
        PreparedStatement stmt = conn.prepareStatement("insert into entrega (produto, descricao, status, endereco_id, motorista_id) values (?, ?, ?, ?, ?)");
        stmt.setString(1, entrega.getProduto());
        stmt.setString(2, entrega.getDescricao());
        stmt.setString(3, entrega.getStatus());
        stmt.setLong(4, entrega.getEnderecoId());

        if (entrega.getMotoristaId() == null) {
            stmt.setNull(5, java.sql.Types.BIGINT);
        } else {
            stmt.setLong(5, entrega.getMotoristaId());
        }

        linhas = stmt.executeUpdate();

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
            entrega.setEnderecoId(rs.getLong("endereco_id"));
            entrega.setMotoristaId(rs.getLong("motorista_id"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return entrega;
}
public List<EntEndDTO> listaEntrElistarEnd() {
    List<EntEndDTO> lista = new ArrayList<>();
    try {
    Connection conn = Conexao.conectar();
    PreparedStatement stmt =null;
    ResultSet rs = null;
    stmt= conn.prepareStatement("SELECT e.id, " +
            "e.produto, " +
            "e.descricao, " +
            "e.status, " +
            "en.rua, " +
            "m.nome AS motorista " +
            "FROM entrega e " +
            "JOIN endereco en ON e.endereco_id = en.id " +
            "LEFT JOIN motoristas m ON e.motorista_id = m.id");
        rs= stmt.executeQuery();
        while (rs.next()) {
        EntEndDTO entregas = new EntEndDTO();
            entregas.setId(rs.getLong("id"));
            entregas.setProduto(rs.getString("produto"));
            entregas.setDescricao(rs.getString("descricao"));
            entregas.setStatus(rs.getString("status"));
            entregas.setRua(rs.getString("rua"));
            entregas.setMotorista(rs.getString("motorista"));
            lista.add(entregas);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
return lista;
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
            PreparedStatement stmt = conn.prepareStatement("update entrega set produto=?, descricao=?, endereco_id=? where id=?");
            stmt.setString(1, entrega.getProduto());
            stmt.setString(2, entrega.getDescricao());
            stmt.setLong(3, entrega.getEnderecoId());
            stmt.setLong(4, entrega.getId());
            linhas=stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
     return linhas;
    }
    public int updateStatus(EntregaDTO entrega){
        int linhas =0;
     try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("update entrega set status=?  where id=?");
            stmt.setString(1, entrega.getStatus());
            stmt.setLong(2, entrega.getId());
            linhas=stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
     return linhas;
    }    
    public int updateIDMotorista(EntregaDTO entrega) {
        int linhas = 0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("UPDATE entrega SET motorista_id=? WHERE id=?");
            if (entrega.getMotoristaId() == null) {
                stmt.setNull(1, java.sql.Types.BIGINT);
            } else {
                stmt.setLong(1, entrega.getMotoristaId());
            }
            stmt.setLong(2, entrega.getId());
            linhas = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return linhas;
    }    
}
