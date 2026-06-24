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
        stmt= conn.prepareStatement("select * from entregas");
        rs= stmt.executeQuery();
        while (rs.next()) {
            EntregaDTO entregas = new EntregaDTO();
            entregas.setId(rs.getLong("id"));
            entregas.setCliente_id(rs.getLong("cliente_id"));
            entregas.setNome(rs.getString("nome"));
            entregas.setDescricao(rs.getString("descricao"));
            entregas.setStatus(rs.getString("status"));
            lista.add(entregas);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
        return lista;
}
    public int register(EntregaDTO entrega) {
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("insert into entregas (cliente_id, nome,descricao, status) values (?, ?, ?, ?)");
            stmt.setLong(1, entrega.getCliente_id());
            stmt.setString(2, entrega.getNome());
            stmt.setString(3, entrega.getDescricao());
            stmt.setString(4, entrega.getStatus());

            int AffectedRows = stmt.executeUpdate();
            if (AffectedRows == 0) {
                throw new SQLException("Falha na atualização - Nenhuma linha foi encontrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return 0;
    } 
}
