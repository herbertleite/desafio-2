package br.com.herbert.reserva.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.herbert.reserva.dto.request.NovoPatrimonioDto;
import br.com.herbert.reserva.dto.response.ExibirPatrimonioDto;
import br.com.herbert.reserva.model.Patrimonio;
import br.com.herbert.reserva.service.PatrimonioService;

@RestController
@RequestMapping("/api/patrimonio")
public class PatrimonioController {

	@Autowired
	private PatrimonioService patrimonioService;
	
	@GetMapping("{id}")
	public ResponseEntity<ExibirPatrimonioDto> buscaPorId(@PathVariable Long id){
		Patrimonio patrimonio = patrimonioService.buscar(id);
		return ResponseEntity.ok(new ExibirPatrimonioDto(patrimonio));
	}
	
	@GetMapping
	public ResponseEntity<List<ExibirPatrimonioDto>> buscaTodos() {
		List<Patrimonio> patrimonio = patrimonioService.listarTodos();
		List<ExibirPatrimonioDto> patrimonioDto = patrimonio.stream().map(u -> new ExibirPatrimonioDto(u))
				.collect(Collectors.toList());
		return ResponseEntity.ok(patrimonioDto);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<ExibirPatrimonioDto>> buscaPaginada(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "numeroTombo") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Patrimonio> list = patrimonioService.buscaPaginada(page, linesPerPage, orderBy, direction);
		Page<ExibirPatrimonioDto> listDto = list.map(obj -> new ExibirPatrimonioDto(obj));

		return ResponseEntity.ok().body(listDto);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		patrimonioService.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping()
	public ResponseEntity<ExibirPatrimonioDto> adicionarCliente(@Valid @RequestBody NovoPatrimonioDto dto) {
		Patrimonio patrimonio = patrimonioService.converteNovoPatrimonioDTOEmEntidade(dto);
		patrimonio = patrimonioService.salvar(patrimonio);
		return new ResponseEntity<ExibirPatrimonioDto>(new ExibirPatrimonioDto(patrimonio), HttpStatus.CREATED);
	}
	
	@PutMapping("{numeroTombo}")
	public ResponseEntity<ExibirPatrimonioDto> atualizar(@Valid @RequestBody NovoPatrimonioDto dto, @PathVariable Long numeroTombo) {
		Patrimonio patrimonio = patrimonioService.converteNovoPatrimonioDTOEmEntidade(dto);
		patrimonio.setNumeroTombo(numeroTombo);
		patrimonio = patrimonioService.atualizar(patrimonio, numeroTombo);
		return new ResponseEntity<ExibirPatrimonioDto>(new ExibirPatrimonioDto(patrimonio), HttpStatus.OK);
	}
}
