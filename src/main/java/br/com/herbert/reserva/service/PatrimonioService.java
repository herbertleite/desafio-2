package br.com.herbert.reserva.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.herbert.reserva.dto.request.NovoPatrimonioDto;
import br.com.herbert.reserva.model.Marca;
import br.com.herbert.reserva.model.Patrimonio;
import br.com.herbert.reserva.repository.PatrimonioRepository;
import br.com.herbert.reserva.service.util.ServiceGenericoImpl;

@Service
public class PatrimonioService extends ServiceGenericoImpl<Patrimonio, Long> {

	@SuppressWarnings("unused")
	@Autowired
	private PatrimonioRepository patrimonioRepository;

	@Autowired
	private MarcaService marcaService;

	public PatrimonioService(JpaRepository<Patrimonio, Long> jpaRepository, PatrimonioRepository patrimonioRepository,
			MarcaService marcaService) {
		super(jpaRepository);
		this.patrimonioRepository = patrimonioRepository;
		this.marcaService = marcaService;
	}

	@Override
	public Patrimonio buscar(Long key) {
		return super.buscar(key);
	}

	@Override
	public Patrimonio salvar(Patrimonio entity) {
		return super.salvar(entity);
	}

	@Override
	public Patrimonio atualizar(Patrimonio entity, Long key) {
		Patrimonio patrimonio = buscar(key);
		patrimonio.setDescricao(entity.getDescricao());
		patrimonio.setMarcaId(entity.getMarcaId());
		patrimonio.setNome(entity.getNome());
		return super.atualizar(patrimonio, key);
	}

	@Override
	public void remover(Long key) {
		Patrimonio patrimonio = buscar(key);
		super.remover(patrimonio.getNumeroTombo());
	}


	public Patrimonio converteNovoPatrimonioDTOEmEntidade(@Valid NovoPatrimonioDto dto) {
		Marca marca = marcaService.buscar(dto.getMarcaId());
		return new Patrimonio(dto.getNome(), dto.getDescricao(), marca);
	}
}
