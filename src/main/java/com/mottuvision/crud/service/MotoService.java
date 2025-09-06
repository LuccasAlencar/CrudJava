package com.mottuvision.crud.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mottuvision.crud.model.Moto;
import com.mottuvision.crud.model.PatioFinalizadas;
import com.mottuvision.crud.repository.MotoRepository;
import com.mottuvision.crud.repository.PatioFinalizadasRepository;

@Service
@Transactional
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;
    
    @Autowired
    private PatioFinalizadasRepository patioFinalizadasRepository;

    public List<Moto> findAll() {
        return motoRepository.findAll();
    }

    public Optional<Moto> findById(Long id) {
        return motoRepository.findById(id);
    }

    public Moto save(Moto moto) {
        // O lado dono do relacionamento 1-1 é Moto (JoinColumn em Moto),
        // portanto não é necessário setar back-reference no Identificador.
        return motoRepository.save(moto);
    }

    public void deleteById(Long id) {
        motoRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return motoRepository.existsById(id);
    }
    
    /**
     * Finaliza uma moto: registra como finalizada no pátio e remove do banco
     */
    @Transactional
    public void finalizarMoto(Long id) {
        // Buscar a moto
        Optional<Moto> motoOpt = motoRepository.findById(id);
        if (motoOpt.isEmpty()) {
            throw new RuntimeException("Moto não encontrada com o ID: " + id);
        }
        
        Moto moto = motoOpt.get();
        
        // Criar registro de finalização
        PatioFinalizadas patioFinalizadas = new PatioFinalizadas();
        patioFinalizadas.setPatio(moto.getPatio());
        patioFinalizadas.setDataFinalizacao(LocalDateTime.now());
        patioFinalizadas.setIdentificadorMoto(moto.getIdentificador().getTipo() + ": " + moto.getIdentificador().getValor());
        patioFinalizadas.setModeloNome(moto.getModelo().getNome());
        
        // Salvar o registro de finalização
        patioFinalizadasRepository.save(patioFinalizadas);
        
        // Remover a moto do banco
        motoRepository.deleteById(id);
    }
}