package br.com.herbert.reserva.dto.response;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.Email;

import br.com.herbert.reserva.model.Usuario;
import br.com.herbert.reserva.model.enuns.Role;

public class ExibirClienteDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long clienteId;

	private String nome;

	@Email
	private String email;

	private Set<Role> perfies;

	public ExibirClienteDto() {
		super();
	}

	public ExibirClienteDto(Usuario usuario) {
		super();
		this.clienteId = usuario.getUsuarioId();
		this.email = usuario.getEmail();
		this.nome = usuario.getNome();
		this.perfies = usuario.getPerfis();
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
