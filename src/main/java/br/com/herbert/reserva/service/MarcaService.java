package br.com.herbert.reserva.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.herbert.reserva.dto.request.NovoMarcaDto;
import br.com.herbert.reserva.exception.NegocioException;
import br.com.herbert.reserva.model.Marca;
import br.com.herbert.reserva.repository.MarcaRepository;
import br.com.herbert.reserva.service.util.ServiceGenericoImpl;

@Service
public class MarcaService extends ServiceGenericoImpl<Marca, Long> {

	public MarcaService(JpaRepository<Marca, Long> jpaRepository, MarcaRepository marcaRepository) {
		super(jpaRepository);
		this.marcaRepository = marcaRepository;
	}

	@Autowired
	private MarcaRepository marcaRepository;

	@Override
	public Marca buscar(Long key) {
		return super.buscar(key);
	}

	@Override
	public void remover(Long key) {
		Marca marca = buscar(key);
		super.remover(marca.getMarcaId());
	}

	@Override
	public Marca salvar(Marca entity) {
		verificaNomeMarca(entity.getNome());
		return super.salvar(entity);
	}

	@Override
	public Marca atualizar(Marca entity, Long key) {
		Marca marca = buscar(key);
		if(entity.getNome().equals(marca.getNome())) {
			throw new NegocioException("Não há alteração no nome!");
		}
		verificaNomeMarca(entity.getNome());
		marca.setNome(entity.getNome());
		return super.atualizar(marca, key);
	}
	
	private boolean verificaNomeMarca(String nome) {
		Optional<Marca> marca = marcaRepository.buscaPorNome(nome);
		if(marca.isEmpty()) {
			return true;
		}else {
			throw new NegocioException("Nome ja cadastrado no sistema!");
		}
	}

	public Marca converteNovoMarcaDTOEmEntidade(@Valid NovoMarcaDto dto) {
		Marca marca = new Marca();
		marca.setNome(dto.getNome());
		return marca;
	}
}
