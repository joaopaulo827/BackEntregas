/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gerenciador.backentregas.repository;


import com.gerenciador.backentregas.model.EnderecoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Aluno
 */
@Repository
public class EnderecoRepository {
public List<EnderecoDTO> listaEndereco() {
    List<EnderecoDTO> lista = new ArrayList<>();
    try {
        Connection conn = Conexao.conectar();
        PreparedStatement stmt =null;
        ResultSet rs = null;
        stmt= conn.prepareStatement("select * from endereco");
        rs= stmt.executeQuery();
        while (rs.next()) {
            EnderecoDTO enderecos = new EnderecoDTO();
            enderecos.setId(rs.getLong("id"));
            enderecos.setRua(rs.getString("rua"));
            enderecos.setNumero(rs.getLong("numero"));
            enderecos.setCidade(rs.getString("cidade"));
            lista.add(enderecos);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
        return lista;
}
public List<EnderecoDTO> listaIdEndereco(Long id) {
    List<EnderecoDTO> lista = new ArrayList<>();
    try {
        Connection conn = Conexao.conectar();
        PreparedStatement stmt =null;
        ResultSet rs = null;
        stmt= conn.prepareStatement("SELECT * FROM endereco where id=?");
        stmt.setLong(1, id);
        rs= stmt.executeQuery();
        while (rs.next()) {
            EnderecoDTO enderecos = new EnderecoDTO();
            enderecos.setId(rs.getLong("id"));
            enderecos.setRua(rs.getString("rua"));
            enderecos.setNumero(rs.getLong("numero"));
            enderecos.setCidade(rs.getString("cidade"));
            lista.add(enderecos);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
        return lista;
}
    public int registarEndereco(EnderecoDTO enderecos) {
         int linhas =0;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("insert into endereco ( rua, numero, cidade) values ( ?, ?, ?)");
            stmt.setString(1, enderecos.getRua());
            stmt.setLong(2, enderecos.getNumero());
            stmt.setString(3, enderecos.getCidade());

              linhas = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return linhas;
    }
    public EnderecoDTO buscarPorId(Long id) {
        EnderecoDTO enderecos = null;
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt =
            conn.prepareStatement("select * from endereco where id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            enderecos = new EnderecoDTO();
            enderecos.setId(rs.getLong("id"));
            enderecos.setRua(rs.getString("rua"));
            enderecos.setNumero(rs.getLong("numero"));
            enderecos.setCidade(rs.getString("cidade"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return enderecos;
}
    public int deleteById(long id){
        int linhas =0;
     try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("delete from endereco Where id=?");
            stmt.setLong(1, id);
            linhas=stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }   
     return linhas;
    }
    public int update(EnderecoDTO enderecos){
        int linhas =0;
     try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement("update endereco set rua=?, numero=?, cidade=? where id=?");
            stmt.setString(1, enderecos.getRua());
            stmt.setLong(2, enderecos.getNumero());
            stmt.setString(3, enderecos.getCidade());
            stmt.setLong(4, enderecos.getId());
            linhas=stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
     return linhas;
    }    
}
