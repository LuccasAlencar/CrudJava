package com.mottuvision.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mottuvision.crud.dto.RelatorioDTO;
import com.mottuvision.crud.service.RelatorioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/relatorio")
@Tag(name = "Relatórios", description = "Endpoints para gerar relatórios de motos.")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping
    @Operation(summary = "Gera relatório de motos", 
               description = "Retorna um relatório completo com dados consolidados das motos por status e zonas.")
    public ResponseEntity<RelatorioDTO> gerarRelatorio() {
        try {
            RelatorioDTO relatorio = relatorioService.gerarRelatorio();
            return ResponseEntity.ok(relatorio);
        } catch (Exception e) {
            // Log do erro pode ser adicionado aqui
            return ResponseEntity.internalServerError().build();
        }
    }
}