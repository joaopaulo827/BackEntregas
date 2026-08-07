/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gerenciador.backentregas.model;

/**
 *
 * @author Aluno
 */
public class EntEndDTO {
    private Long id;
    private String produto;
    private String descricao;
    private String status;
    private String rua;
    private String motorista;

    public EntEndDTO() {
    }

    public EntEndDTO(Long id, String produto, String descricao, String status, String rua, String motorista) {
        this.id = id;
        this.produto = produto;
        this.descricao = descricao;
        this.status = status;
        this.rua = rua;
        this.motorista = motorista;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }
        
}