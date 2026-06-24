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

    public void novoEdital(EntregaDTO entrega, UserDTO usuarioLogado) {
        String message = "";
        if (!usuarioLogado.getRole().equals("OPERADOR")) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(403),
                    "Acesso negado: apenas usuários com role OPERADOR podem adcionar novas entregas"
            );
        }
        if (entrega.getNome().isEmpty()) {
            message += "Nome não preenchido!";
        }
        if (entrega.getDescricao().isEmpty()) {
            message += "Descrição não preenchida!";
        }
        if (!message.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), message);
        }
        entrega.setStatus("NAO ENTREGUE");
        int rows = entregaRepository.register(entrega);
        if (rows == 0) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(500),
                    "Erro ao criar edital");
        }
    }

    public List<EntregaDTO> listaEntrega(String authHeader) {
        if (tokenService.validarToken(authHeader)) {
            return entregaRepository.listaEntregas();
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Token inválido!");
        }
    }
}
