package br.com.herbert.reserva.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Marca {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long marcaId;

	private String nome;
	
	

	public Marca() {
		super();
	}

	public Marca(Long marcaId, String nome) {
		super();
		this.marcaId = marcaId;
		this.nome = nome;
	}

	public Long getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(Long marcaId) {
		this.marcaId = marcaId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
