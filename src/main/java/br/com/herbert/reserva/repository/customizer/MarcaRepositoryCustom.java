package br.com.herbert.reserva.repository.customizer;

import java.util.Optional;

import br.com.herbert.reserva.model.Marca;

public interface MarcaRepositoryCustom {

	public Optional<Marca> buscaPorNome(String nome);
}
