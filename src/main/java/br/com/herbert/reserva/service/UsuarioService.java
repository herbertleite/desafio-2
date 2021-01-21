package br.com.herbert.reserva.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.herbert.reserva.dto.request.NovoClienteDto;
import br.com.herbert.reserva.exception.AuthorizationException;
import br.com.herbert.reserva.exception.NegocioException;
import br.com.herbert.reserva.model.Usuario;
import br.com.herbert.reserva.model.enuns.Role;
import br.com.herbert.reserva.repository.UsuarioRepository;
import br.com.herbert.reserva.security.UserDetailsImpl;
import br.com.herbert.reserva.service.util.ServiceGenericoImpl;

@Service
public class UsuarioService extends ServiceGenericoImpl<Usuario, Long> {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UsuarioService(JpaRepository<Usuario, Long> jpaRepository, UsuarioRepository usuarioRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		super(jpaRepository);
		this.usuarioRepository = usuarioRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	public Usuario buscar(Long key) {
		return super.buscar(key);
	}
	
	@Override
	public Usuario salvar(Usuario entity) {
		verificaEmail(entity.getEmail());
		entity.addPerfil(Role.CLIENTE);
		return super.salvar(entity);
	}
	
	@Override
	public void remover(Long key) {
		Usuario usuario = buscar(key);
		super.remover(usuario.getUsuarioId());
	}
	
	@Override
	public Usuario atualizar(Usuario entity, Long key) {
		Usuario usuario =  buscar(key);
		if(usuario.getEmail().equals(entity.getEmail())) {
			usuario.setNome(entity.getNome());
			usuario.setSenha(entity.getSenha());
		}else {
			verificaEmail(entity.getEmail());
			usuario.setNome(entity.getNome());
			usuario.setEmail(entity.getEmail());
			usuario.setSenha(entity.getSenha());
		}
		return super.atualizar(usuario, key);
	}

	public UserDetailsImpl getUserDetails() {
		try {
			return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean hasRole(Role perfil) {
		UserDetailsImpl user = getUserDetails();

		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}

		return user.hasRole(perfil);
	}
	
	public boolean verificaEmail(String email) {
		Optional<Usuario> usuarioEmail = usuarioRepository.buscaPorEmail(email);
		if (usuarioEmail.isEmpty()) {
			return true;
		} else {
			throw new NegocioException("Email já cadastrado no sistema!");
		}
	}

	public Usuario converteNovoClienteDTOEmEntidade(NovoClienteDto dto) {
		Usuario cliente = new Usuario();
		cliente.setEmail(dto.getEmail());
		cliente.setNome(dto.getNome());
		cliente.setSenha(bCryptPasswordEncoder.encode(dto.getSenha()));
		cliente.addPerfil(Role.CLIENTE);
		return cliente;
		
	}
}
