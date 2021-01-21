package br.com.herbert.reserva.service.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.herbert.reserva.model.Usuario;
import br.com.herbert.reserva.repository.UsuarioRepository;
import br.com.herbert.reserva.security.UserDetailsImpl;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	//metodo para consultar o se o email existe no banco de dados
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.buscaPorEmail(email);
		
		return new UserDetailsImpl(usuario.orElseThrow(() -> new UsernameNotFoundException(email)));
	}

}
