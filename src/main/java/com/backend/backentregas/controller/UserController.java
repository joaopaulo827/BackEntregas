/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.backend.backentregas.controller;

import com.backend.backentregas.model.UserDTO;
import com.backend.backentregas.model.UserRequestDTO;
import com.backend.backentregas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public String registrar(@RequestBody UserDTO user) {
        userService.register(user);
        return "Cadastro realizado com sucesso!";
    }
    
    @PostMapping("/logar")
    public String logar(@RequestBody UserRequestDTO user) {
        userService.logar(user);
        return "login realizado com sucesso";
    } 
}
