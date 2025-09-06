package com.mottuvision.crud.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mottuvision.crud.dto.RelatorioDTO;
import com.mottuvision.crud.dto.RelatorioDTO.RelatorioZonaDTO;
import com.mottuvision.crud.model.Moto;
import com.mottuvision.crud.model.Zona;
import com.mottuvision.crud.repository.MotoRepository;
import com.mottuvision.crud.repository.ZonaRepository;

@Service
public class RelatorioService {

    @Autowired
    private MotoRepository motoRepository;
    
    @Autowired
    private ZonaRepository zonaRepository;

    public RelatorioDTO gerarRelatorio() {
        // Buscar todas as motos
        List<Moto> todasMotos = motoRepository.findAll();
        
        // Calcular totais gerais
        Long totalMotos = (long) todasMotos.size();
        Long totalManutencao = contarPorGrupoStatus(todasMotos, "Manutenção");
        Long totalAguardando = contarPorGrupoStatus(todasMotos, "Aguardando");
        Long totalIndisponivel = contarPorGrupoStatus(todasMotos, "Indisponível");
        Long totalPronta = contarPorGrupoStatus(todasMotos, "Pronta");
        
        // Gerar dados por zona
        List<RelatorioZonaDTO> dadosZonas = gerarDadosPorZona(todasMotos);
        
        return new RelatorioDTO(totalMotos, totalManutencao, totalAguardando, 
                               totalIndisponivel, totalPronta, dadosZonas);
    }
    
    private Long contarPorGrupoStatus(List<Moto> motos, String grupoStatus) {
        return motos.stream()
                .filter(moto -> moto.getStatus() != null && 
                               moto.getStatus().getStatusGrupo() != null &&
                               moto.getStatus().getStatusGrupo().getNome().equals(grupoStatus))
                .count();
    }
    
    private List<RelatorioZonaDTO> gerarDadosPorZona(List<Moto> todasMotos) {
        List<RelatorioZonaDTO> dadosZonas = new ArrayList<>();
        
        // Agrupar motos por zona
        Map<Zona, List<Moto>> motosPorZona = todasMotos.stream()
                .filter(moto -> moto.getZona() != null)
                .collect(Collectors.groupingBy(Moto::getZona));
        
        // Para cada zona, calcular os dados
        for (Map.Entry<Zona, List<Moto>> entry : motosPorZona.entrySet()) {
            Zona zona = entry.getKey();
            List<Moto> motosZona = entry.getValue();
            
            RelatorioZonaDTO dadosZona = new RelatorioZonaDTO();
            dadosZona.setZonaId(zona.getId());
            dadosZona.setZonaNome(zona.getNome());
            dadosZona.setZonaLetra(zona.getLetra());
            dadosZona.setTotalZona((long) motosZona.size());
            dadosZona.setManutencaoZona(contarPorGrupoStatus(motosZona, "Manutenção"));
            dadosZona.setAguardandoZona(contarPorGrupoStatus(motosZona, "Aguardando"));
            dadosZona.setIndisponivelZona(contarPorGrupoStatus(motosZona, "Indisponível"));
            dadosZona.setProntaZona(contarPorGrupoStatus(motosZona, "Pronta"));
            
            // Calcular detalhes por status específico
            dadosZona.setDetalhesManutencao(contarPorStatusEspecifico(motosZona, "Manutenção"));
            dadosZona.setDetalhesAguardando(contarPorStatusEspecifico(motosZona, "Aguardando"));
            dadosZona.setDetalhesIndisponivel(contarPorStatusEspecifico(motosZona, "Indisponível"));
            dadosZona.setDetalhesPronta(contarPorStatusEspecifico(motosZona, "Pronta"));
            
            dadosZonas.add(dadosZona);
        }
        
        // Buscar zonas que não têm motos para incluir no relatório com valores zero
        List<Zona> todasZonas = zonaRepository.findAll();
        for (Zona zona : todasZonas) {
            boolean zonaJaIncluida = dadosZonas.stream()
                    .anyMatch(dadosZona -> dadosZona.getZonaId().equals(zona.getId()));
            
            if (!zonaJaIncluida) {
                RelatorioZonaDTO dadosZonaVazia = new RelatorioZonaDTO();
                dadosZonaVazia.setZonaId(zona.getId());
                dadosZonaVazia.setZonaNome(zona.getNome());
                dadosZonaVazia.setZonaLetra(zona.getLetra());
                dadosZonaVazia.setTotalZona(0L);
                dadosZonaVazia.setManutencaoZona(0L);
                dadosZonaVazia.setAguardandoZona(0L);
                dadosZonaVazia.setIndisponivelZona(0L);
                dadosZonaVazia.setProntaZona(0L);
                dadosZonaVazia.setDetalhesManutencao(new HashMap<>());
                dadosZonaVazia.setDetalhesAguardando(new HashMap<>());
                dadosZonaVazia.setDetalhesIndisponivel(new HashMap<>());
                dadosZonaVazia.setDetalhesPronta(new HashMap<>());
                
                dadosZonas.add(dadosZonaVazia);
            }
        }
        
        // Ordenar por nome da zona
        dadosZonas.sort((a, b) -> a.getZonaNome().compareTo(b.getZonaNome()));
        
        return dadosZonas;
    }
    
    private Map<String, Long> contarPorStatusEspecifico(List<Moto> motos, String grupoStatus) {
        Map<String, Long> contagem = new HashMap<>();
        
        Map<String, Long> statusCount = motos.stream()
                .filter(moto -> moto.getStatus() != null && 
                               moto.getStatus().getStatusGrupo() != null &&
                               moto.getStatus().getStatusGrupo().getNome().equals(grupoStatus))
                .collect(Collectors.groupingBy(
                    moto -> moto.getStatus().getNome(),
                    Collectors.counting()
                ));
        
        contagem.putAll(statusCount);
        
        return contagem;
    }
}