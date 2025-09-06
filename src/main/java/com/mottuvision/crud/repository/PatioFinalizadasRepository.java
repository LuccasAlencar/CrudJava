package com.mottuvision.crud.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mottuvision.crud.model.PatioFinalizadas;

@Repository
public interface PatioFinalizadasRepository extends JpaRepository<PatioFinalizadas, Long> {
    
    // Contar total de motos finalizadas por pátio
    @Query("SELECT COUNT(pf) FROM PatioFinalizadas pf WHERE pf.patio.id = :patioId")
    Long countByPatioId(@Param("patioId") Long patioId);
    
    // Contar motos finalizadas por pátio em um período
    @Query("SELECT COUNT(pf) FROM PatioFinalizadas pf WHERE pf.patio.id = :patioId AND pf.dataFinalizacao BETWEEN :dataInicio AND :dataFim")
    Long countByPatioIdAndPeriodo(@Param("patioId") Long patioId, 
                                  @Param("dataInicio") LocalDateTime dataInicio, 
                                  @Param("dataFim") LocalDateTime dataFim);
    
    // Buscar todas as motos finalizadas por pátio
    @Query("SELECT pf FROM PatioFinalizadas pf WHERE pf.patio.id = :patioId ORDER BY pf.dataFinalizacao DESC")
    List<PatioFinalizadas> findByPatioIdOrderByDataFinalizacaoDesc(@Param("patioId") Long patioId);
    
    // Contar total geral de motos finalizadas
    @Query("SELECT COUNT(pf) FROM PatioFinalizadas pf")
    Long countTotal();
}