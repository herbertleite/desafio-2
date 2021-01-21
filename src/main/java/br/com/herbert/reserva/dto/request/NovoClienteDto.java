package br.com.herbert.reserva.dto.request;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotEmpty;

import br.com.herbert.reserva.model.Usuario;
import br.com.herbert.reserva.model.enuns.Role;

public class NovoClienteDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String nome;

	@NotEmpty(message = "Preenchimento obrigatório")
	private String senha;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email informado e invalido")
	private String email;

	private Set<Role> perfies;

	public NovoClienteDto() {
		super();
	}

	public NovoClienteDto(Usuario usuario) {
		super();
		this.nome = usuario.getNome();
		this.senha = usuario.getSenha();
		this.email = usuario.getEmail();
		this.perfies = usuario.getPerfis();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getPerfies() {
		return perfies;
	}

	public void setPerfies(Set<Role> perfies) {
		this.perfies = perfies;
	}

}
