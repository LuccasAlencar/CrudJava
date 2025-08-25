package com.mottuvision.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.mottuvision.crud.model.Moto;
import com.mottuvision.crud.repository.MotoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/motos")
@Tag(name = "Motos", description = "Endpoints para gerenciamento de motos.")
public class MotoController {

	@Autowired
	private MotoRepository motoRepository;
	
	// GET - Endpoint para buscar todas as motos
	@GetMapping
	@Operation(summary = "Lista todas as motos", description = "Retorna uma lista de todas as motos cadastradas.")
	public List<Moto> getAllMotos() {
		return motoRepository.findAll();
	}
	
	
	// GET - Endpoint para buscar por id = /api/motos/{id}, retorna 404 se não existir
	@GetMapping("/{id}")
	@Operation(summary = "Busca uma moto por ID", description = "Retorna uma única moto com base no ID fornecido.")
	public ResponseEntity<Moto> getMotoById(@PathVariable Long id) {
		return motoRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto não encontrada com o ID: " + id));
	}
	
	
	// POST - Endpoint para criar uma nova moto
	@PostMapping
	@Operation(summary = "Cria uma nova moto", description = "Cria e salva uma nova moto no banco de dados.")
	public ResponseEntity<Moto> createMoto(@Valid @RequestBody Moto moto){
		Moto savedMoto = motoRepository.save(moto);
		return new ResponseEntity<>(savedMoto, HttpStatus.CREATED);
	}
	
	
	// DELETE  - Endpoint para deletar uma moto por id
	@DeleteMapping("/{id}")
	@Operation(summary = "Deleta uma moto", description = "Remove uma moto do banco de dados com base no ID fornecido.")
	public ResponseEntity<Void> deleteMoto(@PathVariable Long id){
		if (!motoRepository.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto não encontrada com o ID: " + id);
		} motoRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	
	// PUT - Endpoint para alterar uma moto por id
	@PutMapping("/{id}")
	@Operation(summary = "Atualiza uma moto existente", description = "Atualiza os dados de uma moto com base no ID e no corpo da requisição.")
	public ResponseEntity<Moto> updateMoto(@PathVariable Long id, @Valid @RequestBody Moto motoDetails){
		
		Moto moto = motoRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto não encontrada pelo id: " + id));
		
        moto.setPlaca(motoDetails.getPlaca());
        moto.setChassi(motoDetails.getChassi());
        moto.setQrCode(motoDetails.getQrCode());
        moto.setDataEntrada(motoDetails.getDataEntrada());
        moto.setPrevisaoEntrega(motoDetails.getPrevisaoEntrega());
        moto.setFotos(motoDetails.getFotos());
        moto.setZona(motoDetails.getZona());
        moto.setPatio(motoDetails.getPatio());
        moto.setStatus(motoDetails.getStatus());
        moto.setObservacoes(motoDetails.getObservacoes());
        
        Moto  updateMoto = motoRepository.save(moto);
        return ResponseEntity.ok(updateMoto);
		
	}
	
}
