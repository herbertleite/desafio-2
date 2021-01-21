package br.com.herbert.reserva.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import br.com.herbert.reserva.model.Usuario;

public class NovoMarcaDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String nome;

	public NovoMarcaDto() {
		super();
	}

	public NovoMarcaDto(Usuario usuario) {
		super();
		this.nome = usuario.getNome();

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
