package br.com.herbert.reserva.repository.customizer;

import java.util.Optional;

import br.com.herbert.reserva.model.Usuario;

public interface UsuarioRepositoryCustom {

	public Optional<Usuario> buscaPorEmail(String email);
}
