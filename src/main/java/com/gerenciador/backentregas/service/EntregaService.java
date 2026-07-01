/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gerenciador.backentregas.service;

import com.gerenciador.backentregas.model.EntregaDTO;
import com.gerenciador.backentregas.model.UserDTO;
import com.gerenciador.backentregas.repository.EntregaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author joaop
 */
@Service
public class EntregaService {
    @Autowired
    private EntregaRepository entregaRepository;
     @Autowired
    private TokenService tokenService;

    public void novoEntrega(EntregaDTO entrega, UserDTO usuarioLogado) {
        String message = "";
        if (entrega.getProduto().isEmpty()) {
            message += "Produto não preenchido!";
        }
        if (!message.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), message);
        }
        entrega.setStatus("NAO ENTREGUE");
        int rows = entregaRepository.registarEntrega(entrega);
        if (rows == 0) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(500),
                    "Erro ao criar entrega");
        }
    }

    public List<EntregaDTO> listaEntrega(String authHeader) {
        if (tokenService.validarToken(authHeader)) {
            return entregaRepository.listaEntregas();
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Token inválido!");
        }
    }
    public EntregaDTO buscarPorId(Long id) {
    return entregaRepository.buscarPorId(id);
}
    public void atualizar(EntregaDTO entrega){
        entregaRepository.update(entrega);
    }    
}
