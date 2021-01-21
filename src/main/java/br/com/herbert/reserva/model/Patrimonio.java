package br.com.herbert.reserva.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Patrimonio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long numeroTombo;

	private String nome;

	private String descricao;

	@ManyToOne
	private Marca marcaId;

	public Patrimonio() {
		super();
	}

	public Patrimonio(String nome, String descricao, Marca marcaId) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.marcaId = marcaId;
	}

	public Long getNumeroTombo() {
		return numeroTombo;
	}

	public void setNumeroTombo(Long numeroTombo) {
		this.numeroTombo = numeroTombo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Marca getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(Marca marcaId) {
		this.marcaId = marcaId;
	}

}
