package br.com.herbert.reserva.dto.response;

import java.io.Serializable;

import br.com.herbert.reserva.model.Marca;

public class ExibirMarcaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long marcaId;

	private String nome;

	public ExibirMarcaDto() {
		super();
	}
	
	public ExibirMarcaDto(Marca marca) {
		this.marcaId = marca.getMarcaId();
		this.nome = marca.getNome();
	}

	public ExibirMarcaDto(Long marcaId, String nome) {
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
