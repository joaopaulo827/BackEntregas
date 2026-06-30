/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gerenciador.backentregas.controller;

import com.gerenciador.backentregas.model.MotoDTO;
import com.gerenciador.backentregas.service.MotoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class MotoController {

    @Autowired
    private MotoService motoService;
    @GetMapping("/motorista")
    public List<MotoDTO> listaMoto(
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        return motoService.listaMoto(token);
    }
    @GetMapping("/motorista/{id}")
    public MotoDTO buscarEntrega(@PathVariable Long id) {
    return motoService.buscarPorId(id);
}
@PutMapping("/motorista/{id}")
public String editarEntrega(@PathVariable Long id, @RequestBody MotoDTO moto) {
    try {
        moto.setId(id);
        motoService.atualizar(moto);
        return "OK";
    } catch (Exception e) {
        e.printStackTrace();
        throw e;
    }
}   
}