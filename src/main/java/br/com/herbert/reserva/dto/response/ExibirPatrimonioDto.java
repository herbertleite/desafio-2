package br.com.herbert.reserva.dto.response;

import java.io.Serializable;

import br.com.herbert.reserva.model.Patrimonio;

public class ExibirPatrimonioDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long numeroTombo;

	private String nome;
	
	private String descricao;
	
	private Long marcaId;

	public ExibirPatrimonioDto() {
		super();
	}
	public ExibirPatrimonioDto(Patrimonio patrimonio) {
		super();
		this.numeroTombo = patrimonio.getNumeroTombo();
		this.descricao = patrimonio.getDescricao();
		this.nome = patrimonio.getNome();
		this.marcaId = patrimonio.getMarcaId().getMarcaId();
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

	public Long getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(Long marcaId) {
		this.marcaId = marcaId;
	}







}
