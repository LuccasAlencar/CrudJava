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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "moto")
public class Moto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "moto_seq_gen")
	@SequenceGenerator(name = "moto_seq_gen", sequenceName = "moto_seq", allocationSize = 1)
	private Long id;

	@Column(name = "placa", nullable = false, unique = true)
	@NotBlank(message = "A placa não pode ser vazia")
	@Size(min = 7, max = 10, message = "A placa deve ter entre 7 e 10 carcteres")
	private String placa;
	
	@Column(name = "chassi", nullable = false, unique = true)
	@NotBlank(message = "O chassi não pode ser vazia")
	@Size(min = 7, max = 10, message = "O chassi deve ter entre 17 e 20 carcteres")
	private String chassi;

	@Column(name = "qr_code")
	private String qrCode;
	
	@Column(name = "data_entrada", nullable = false)
	@NotNull(message = "A data de entrada não pode ser nula")
	private LocalDateTime dataEntrada;
	
	@Column(name = "previsão_entrega")
	private LocalDateTime previsaoEntrega;
	
	@Column(name = "fotos")
	private String fotos;
	
	@ManyToOne
	@JoinColumn(name = "zona_id", nullable = false)
	@NotNull(message = "A zona não pode ser nulo")
	private Zona zona;
	
	@ManyToOne
	@JoinColumn(name = "patio_id", nullable = false)
	@NotNull(message = "O pátio não pode ser nulo")
	private Patio patio;
	
	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	@NotNull(message = "O status não pode ser nulo")
	private Status status;
	
	@Column(name = "observacoes", columnDefinition = "CLOB")
	private String observacoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDateTime getPrevisaoEntrega() {
		return previsaoEntrega;
	}

	public void setPrevisaoEntrega(LocalDateTime previsaoEntrega) {
		this.previsaoEntrega = previsaoEntrega;
	}

	public String getFotos() {
		return fotos;
	}

	public void setFotos(String fotos) {
		this.fotos = fotos;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public Patio getPatio() {
		return patio;
	}

	public void setPatio(Patio patio) {
		this.patio = patio;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
}
