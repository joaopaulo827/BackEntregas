/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gerenciador.backentregas.controller;

import com.gerenciador.backentregas.model.UserDTO;
import com.gerenciador.backentregas.model.UserRequestDTO;
import com.gerenciador.backentregas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author joaop
 */
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registrar")
    public String registrar(
            @RequestBody UserDTO user,
            @RequestHeader("Authorization") String token) {

        userService.register(user, token);
        return "Cadastro realizado com sucesso!";
    }

    @PostMapping("/logar")
    public String logar(@RequestBody UserRequestDTO user) {
        return userService.logar(user);
    }

    @PutMapping("/atualizar")
    public void atualizar(@RequestBody UserDTO user) {
        userService.atualizar(user);
    }
}