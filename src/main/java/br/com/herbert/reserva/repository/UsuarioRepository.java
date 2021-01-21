package br.com.herbert.reserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.herbert.reserva.model.Usuario;
import br.com.herbert.reserva.repository.customizer.UsuarioRepositoryCustom;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryCustom{

}
