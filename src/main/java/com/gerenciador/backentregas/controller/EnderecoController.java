/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gerenciador.backentregas.controller;


import com.gerenciador.backentregas.model.EnderecoDTO;
import com.gerenciador.backentregas.model.UserDTO;
import com.gerenciador.backentregas.service.EnderecoService;
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
 * @author Aluno
 */
@RestController
@RequestMapping("/api/auth")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private TokenService tokenService;
    @GetMapping("/endereco")
    
    public List<EnderecoDTO> listaEndereco(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        return enderecoService.listaEndereco(token);
    }
    @PostMapping("/endereco/criar")
    public String criarEntrega(@RequestBody EnderecoDTO enderecos, @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        UserDTO usuarioLogado = tokenService.extrairClaim(token);
        enderecoService.novoEndereco(enderecos);
        return "Nova entrega adcionado com sucesso";
    }
    @GetMapping("/endereco/{id}")
    public EnderecoDTO buscarEntrega(@PathVariable Long id) {
    return enderecoService.buscarPorId(id);
}

    @PutMapping("/endereco/{id}")
    public String editarEntrega(@PathVariable Long id,@RequestBody EnderecoDTO enderecos) {
    enderecos.setId(id);
    enderecoService.atualizar(enderecos);
    return "Endereco atualizada com sucesso.";
}    
}
