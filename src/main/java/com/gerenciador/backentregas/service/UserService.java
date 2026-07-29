/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gerenciador.backentregas.service;

import com.gerenciador.backentregas.model.UserDTO;
import com.gerenciador.backentregas.model.UserRequestDTO;
import com.gerenciador.backentregas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author joaop
 */
@Service
public class UserService {
@Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    public void register(UserDTO user, String token) {
    UserDTO usuarioLogado = tokenService.extrairClaim(token);

    if (!"ADMIN".equals(usuarioLogado.getRole())
            && !"OPERADOR".equals(usuarioLogado.getRole())) {
        throw new ResponseStatusException(
                HttpStatusCode.valueOf(403),
                "Você não possui permissão para cadastrar usuários."
        );
    }
        String message = "";
        if (user.getNome().isEmpty()) {
            message = "Nome não preenchido";
        } else if (user.getEmail().isEmpty()) {
            message = "E-mail não preenchido";
        } else if (user.getSenha().isEmpty()) {
            message = "Senha não preenchida";
        }

        if (!message.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), message);
        }

        repository.register(user);
    }

    public String logar(UserRequestDTO user) {
        String message = "";
        if (user.getEmail().isEmpty()) {
            message = "E-mail não preenchido";
        } else if (user.getSenha().isEmpty()) {
            message = "Senha não preenchida";
        }

        if (!message.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), message);
        }

        UserDTO loggedData = repository.login(user.getEmail(), user.getSenha());
        return tokenService.gerarToken(loggedData);
    }
    public void atualizar(UserDTO user){
        if (user == null || user.getEmail() == null) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Email não encontrado.");
        }
        int linhas = repository.update(user);

    if (linhas == 0) {
        throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Usuário não encontrado.");
    }   
    }
}
