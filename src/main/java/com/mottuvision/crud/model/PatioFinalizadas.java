package com.mottuvision.crud.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patio_finalizadas")
public class PatioFinalizadas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patio_finalizadas_seq_gen")
    @SequenceGenerator(name = "patio_finalizadas_seq_gen", sequenceName = "patio_finalizadas_seq", allocationSize = 1)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "patio_id", nullable = false)
    @NotNull(message = "O pátio não pode ser nulo")
    private Patio patio;
    
    @Column(name = "data_finalizacao", nullable = false)
    @NotNull(message = "A data de finalização não pode ser nula")
    private LocalDateTime dataFinalizacao;
    
    @Column(name = "identificador_moto", nullable = false, length = 100)
    @NotNull(message = "O identificador da moto é obrigatório")
    private String identificadorMoto;
    
    @Column(name = "modelo_nome", nullable = false, length = 120)
    @NotNull(message = "O nome do modelo é obrigatório")
    private String modeloNome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patio getPatio() {
        return patio;
    }

    public void setPatio(Patio patio) {
        this.patio = patio;
    }

    public LocalDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public String getIdentificadorMoto() {
        return identificadorMoto;
    }

    public void setIdentificadorMoto(String identificadorMoto) {
        this.identificadorMoto = identificadorMoto;
    }

    public String getModeloNome() {
        return modeloNome;
    }

    public void setModeloNome(String modeloNome) {
        this.modeloNome = modeloNome;
    }
}