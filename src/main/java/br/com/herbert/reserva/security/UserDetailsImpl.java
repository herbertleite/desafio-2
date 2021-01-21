package br.com.herbert.reserva.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.herbert.reserva.model.Usuario;
import br.com.herbert.reserva.model.enuns.Role;

//implementa a interface UserDetails para facilitar a troca de inforções de segurança
public class UserDetailsImpl implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String senha;
	private String nome;

	private Collection<? extends GrantedAuthority> authorities;
	
	public UserDetailsImpl(Usuario usuario) {
		this.id = usuario.getUsuarioId();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.authorities = usuario.getPerfis().stream().map(
				p -> new SimpleGrantedAuthority(p.getDescricao())).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}
	
	public Usuario getCliente() {
		return new Usuario(id, nome,senha, email);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public boolean hasRole(Role perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
	}


}
