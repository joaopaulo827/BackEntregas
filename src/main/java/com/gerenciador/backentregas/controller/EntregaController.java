/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gerenciador.backentregas.controller;

import com.gerenciador.backentregas.model.EntregaDTO;
import com.gerenciador.backentregas.model.UserDTO;
import com.gerenciador.backentregas.service.EntregaService;
import com.gerenciador.backentregas.service.TokenService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class EntregaController {
    @Autowired
    private EntregaService entregaService;
    @Autowired
    private TokenService tokenService;
    @GetMapping("/entrega")
    public List<EntregaDTO> listaEntrega(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        return entregaService.listaEntrega(token);
    }
    @PostMapping("/entrega/criar")
    public String criarEntrega(@RequestBody EntregaDTO entregas, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        UserDTO usuarioLogado = tokenService.extrairClaim(token);
        entregaService.novoEntrega(entregas, usuarioLogado);
        return "Nova entrega adcionado com sucesso";
    }
    @GetMapping("/entrega/{id}")
    public EntregaDTO buscarEntrega(@PathVariable Long id) {
    return entregaService.buscarPorId(id);
}

    @PutMapping("/entrega/{id}")
    public String editarEntrega(@PathVariable Long id,@RequestBody EntregaDTO entrega) {
    entrega.setId(id);
    entregaService.atualizar(entrega);
    return "Entrega atualizada com sucesso.";
}    
}
