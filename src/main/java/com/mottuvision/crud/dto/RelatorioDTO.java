package com.mottuvision.crud.dto;

import java.util.List;
import java.util.Map;

public class RelatorioDTO {
    
    private Long totalMotos;
    private Long totalManutencao;
    private Long totalAguardando;
    private Long totalIndisponivel;
    private Long totalPronta;
    
    private List<RelatorioZonaDTO> zonas;
    
    // Construtor padrão
    public RelatorioDTO() {}
    
    // Construtor com parâmetros
    public RelatorioDTO(Long totalMotos, Long totalManutencao, Long totalAguardando, 
                       Long totalIndisponivel, Long totalPronta, List<RelatorioZonaDTO> zonas) {
        this.totalMotos = totalMotos;
        this.totalManutencao = totalManutencao;
        this.totalAguardando = totalAguardando;
        this.totalIndisponivel = totalIndisponivel;
        this.totalPronta = totalPronta;
        this.zonas = zonas;
    }

    // Getters e Setters
    public Long getTotalMotos() {
        return totalMotos;
    }

    public void setTotalMotos(Long totalMotos) {
        this.totalMotos = totalMotos;
    }

    public Long getTotalManutencao() {
        return totalManutencao;
    }

    public void setTotalManutencao(Long totalManutencao) {
        this.totalManutencao = totalManutencao;
    }

    public Long getTotalAguardando() {
        return totalAguardando;
    }

    public void setTotalAguardando(Long totalAguardando) {
        this.totalAguardando = totalAguardando;
    }

    public Long getTotalIndisponivel() {
        return totalIndisponivel;
    }

    public void setTotalIndisponivel(Long totalIndisponivel) {
        this.totalIndisponivel = totalIndisponivel;
    }

    public Long getTotalPronta() {
        return totalPronta;
    }

    public void setTotalPronta(Long totalPronta) {
        this.totalPronta = totalPronta;
    }

    public List<RelatorioZonaDTO> getZonas() {
        return zonas;
    }

    public void setZonas(List<RelatorioZonaDTO> zonas) {
        this.zonas = zonas;
    }
    
    // Classe interna para dados por zona
    public static class RelatorioZonaDTO {
        private Long zonaId;
        private String zonaNome;
        private String zonaLetra;
        private Long totalZona;
        private Long manutencaoZona;
        private Long aguardandoZona;
        private Long indisponivelZona;
        private Long prontaZona;
        
        // Detalhes por status específico
        private Map<String, Long> detalhesManutencao;
        private Map<String, Long> detalhesAguardando;
        private Map<String, Long> detalhesIndisponivel;
        private Map<String, Long> detalhesPronta;
        
        // Construtor padrão
        public RelatorioZonaDTO() {}
        
        // Construtor com parâmetros
        public RelatorioZonaDTO(Long zonaId, String zonaNome, String zonaLetra, Long totalZona,
                               Long manutencaoZona, Long aguardandoZona, Long indisponivelZona, Long prontaZona) {
            this.zonaId = zonaId;
            this.zonaNome = zonaNome;
            this.zonaLetra = zonaLetra;
            this.totalZona = totalZona;
            this.manutencaoZona = manutencaoZona;
            this.aguardandoZona = aguardandoZona;
            this.indisponivelZona = indisponivelZona;
            this.prontaZona = prontaZona;
        }

        // Getters e Setters
        public Long getZonaId() {
            return zonaId;
        }

        public void setZonaId(Long zonaId) {
            this.zonaId = zonaId;
        }

        public String getZonaNome() {
            return zonaNome;
        }

        public void setZonaNome(String zonaNome) {
            this.zonaNome = zonaNome;
        }

        public String getZonaLetra() {
            return zonaLetra;
        }

        public void setZonaLetra(String zonaLetra) {
            this.zonaLetra = zonaLetra;
        }

        public Long getTotalZona() {
            return totalZona;
        }

        public void setTotalZona(Long totalZona) {
            this.totalZona = totalZona;
        }

        public Long getManutencaoZona() {
            return manutencaoZona;
        }

        public void setManutencaoZona(Long manutencaoZona) {
            this.manutencaoZona = manutencaoZona;
        }

        public Long getAguardandoZona() {
            return aguardandoZona;
        }

        public void setAguardandoZona(Long aguardandoZona) {
            this.aguardandoZona = aguardandoZona;
        }

        public Long getIndisponivelZona() {
            return indisponivelZona;
        }

        public void setIndisponivelZona(Long indisponivelZona) {
            this.indisponivelZona = indisponivelZona;
        }

        public Long getProntaZona() {
            return prontaZona;
        }

        public void setProntaZona(Long prontaZona) {
            this.prontaZona = prontaZona;
        }

        public Map<String, Long> getDetalhesManutencao() {
            return detalhesManutencao;
        }

        public void setDetalhesManutencao(Map<String, Long> detalhesManutencao) {
            this.detalhesManutencao = detalhesManutencao;
        }

        public Map<String, Long> getDetalhesAguardando() {
            return detalhesAguardando;
        }

        public void setDetalhesAguardando(Map<String, Long> detalhesAguardando) {
            this.detalhesAguardando = detalhesAguardando;
        }

        public Map<String, Long> getDetalhesIndisponivel() {
            return detalhesIndisponivel;
        }

        public void setDetalhesIndisponivel(Map<String, Long> detalhesIndisponivel) {
            this.detalhesIndisponivel = detalhesIndisponivel;
        }

        public Map<String, Long> getDetalhesPronta() {
            return detalhesPronta;
        }

        public void setDetalhesPronta(Map<String, Long> detalhesPronta) {
            this.detalhesPronta = detalhesPronta;
        }
    }
}