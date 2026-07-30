/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gerenciador.backentregas.service;



import com.gerenciador.backentregas.model.EnderecoDTO;
import com.gerenciador.backentregas.model.UserDTO;
import com.gerenciador.backentregas.repository.EnderecoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Aluno
 */
@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;
     @Autowired
    private TokenService tokenService;
     
    public void novoEndereco(EnderecoDTO endereco, UserDTO usuarioLogado) {
        String message = "";
        if (endereco.getCidade().isEmpty()) {
            message += "Cidade não preenchido!";
        }
        if (endereco.getRua().isEmpty()) {
            message += "Rua não preenchido!";
        }
        if(endereco.getNumero()==null){
            message += "Numero não preenchido!";
        }
        if (!message.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), message);
        }
        int rows = enderecoRepository.registarEndereco(endereco);
        if (rows == 0) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(500),
                    "Erro ao criar entrega");
        }
    }
    public List<EnderecoDTO> listaEndereco(String authHeader) {
        if (tokenService.validarToken(authHeader)) {
            return enderecoRepository.listaEndereco();
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Token inválido!");
        }
    }
    public EnderecoDTO buscarPorId(Long id) {
    return enderecoRepository.buscarPorId(id);
}
    public void atualizar(EnderecoDTO endereco){
        enderecoRepository.update(endereco);
    }    
}
