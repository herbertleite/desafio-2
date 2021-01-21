package br.com.herbert.reserva.dto.request;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class LoginDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Email(message = "O Email informado é inválido")
	private String email;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String senha;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
