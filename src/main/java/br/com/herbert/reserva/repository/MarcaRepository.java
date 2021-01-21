package br.com.herbert.reserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.herbert.reserva.model.Marca;
import br.com.herbert.reserva.repository.customizer.MarcaRepositoryCustom;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long>, MarcaRepositoryCustom{
}
