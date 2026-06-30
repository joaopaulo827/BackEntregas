/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gerenciador.backentregas.service;

import com.gerenciador.backentregas.model.MotoDTO;
import com.gerenciador.backentregas.repository.MotoRepository;
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
public class MotoService {
    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private TokenService tokenService;

    public List<MotoDTO> listaMoto(String authHeader) {
        if (tokenService.validarToken(authHeader)) {
            return motoRepository.listaMoto();
        } else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(401), "Token inválido!");
        }
    }
    public MotoDTO buscarPorId(Long id) {
    return motoRepository.buscarPorId(id);
}
    public void atualizar(MotoDTO moto){
        motoRepository.update(moto);
    }     
}
