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

import br.com.herbert.reserva.dto.request.NovoMarcaDto;
import br.com.herbert.reserva.dto.response.ExibirMarcaDto;
import br.com.herbert.reserva.model.Marca;
import br.com.herbert.reserva.service.MarcaService;

@RestController
@RequestMapping("/api/marca")
public class MarcaController {

	@Autowired
	private MarcaService marcaService;
	
	@GetMapping("{id}")
	public ResponseEntity<ExibirMarcaDto> buscaPorId(@PathVariable Long id){
		Marca marca = marcaService.buscar(id);
		return ResponseEntity.ok(new ExibirMarcaDto(marca));
	}
	
	@GetMapping
	public ResponseEntity<List<ExibirMarcaDto>> buscaTodos() {
		List<Marca> marca = marcaService.listarTodos();
		List<ExibirMarcaDto> marcaDto = marca.stream().map(u -> new ExibirMarcaDto(u))
				.collect(Collectors.toList());
		return ResponseEntity.ok(marcaDto);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<ExibirMarcaDto>> buscaPaginada(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Marca> list = marcaService.buscaPaginada(page, linesPerPage, orderBy, direction);
		Page<ExibirMarcaDto> listDto = list.map(obj -> new ExibirMarcaDto(obj));

		return ResponseEntity.ok().body(listDto);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		marcaService.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping()
	public ResponseEntity<ExibirMarcaDto> adicionarCliente(@Valid @RequestBody NovoMarcaDto dto) {
		Marca marca = marcaService.converteNovoMarcaDTOEmEntidade(dto);
		marca = marcaService.salvar(marca);
		return new ResponseEntity<ExibirMarcaDto>(new ExibirMarcaDto(marca), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ExibirMarcaDto> atualizar(@Valid @RequestBody NovoMarcaDto dto, @PathVariable Long id) {
		Marca marca = marcaService.converteNovoMarcaDTOEmEntidade(dto);
		marca.setMarcaId(id);
		marca = marcaService.atualizar(marca, id);
		return new ResponseEntity<ExibirMarcaDto>(new ExibirMarcaDto(marca), HttpStatus.OK);
	}
}
