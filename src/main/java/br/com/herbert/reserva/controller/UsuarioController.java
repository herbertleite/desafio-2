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

import br.com.herbert.reserva.dto.request.NovoClienteDto;
import br.com.herbert.reserva.dto.response.ExibirClienteDto;
import br.com.herbert.reserva.model.Usuario;
import br.com.herbert.reserva.service.UsuarioService;

//nos permite criar um controlador com características REST e que possa manipular as requisições
@RestController
// nos permite mapear o caminho da nossa api.
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("{id}")
	public ResponseEntity<ExibirClienteDto> buscaPorId(@PathVariable Long id){
		Usuario usuario = usuarioService.buscar(id);
		return ResponseEntity.ok(new ExibirClienteDto(usuario));
	}
	
	@GetMapping
	public ResponseEntity<List<ExibirClienteDto>> buscaTodos() {
		List<Usuario> usuario = usuarioService.listarTodos();
		List<ExibirClienteDto> usuarioDto = usuario.stream().map(u -> new ExibirClienteDto(u))
				.collect(Collectors.toList());
		return ResponseEntity.ok(usuarioDto);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<ExibirClienteDto>> buscaPaginada(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Usuario> list = usuarioService.buscaPaginada(page, linesPerPage, orderBy, direction);
		Page<ExibirClienteDto> listDto = list.map(obj -> new ExibirClienteDto(obj));

		return ResponseEntity.ok().body(listDto);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id) {
		usuarioService.remover(id);
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/adicionar_cliente")
	public ResponseEntity<ExibirClienteDto> adicionarCliente(@Valid @RequestBody NovoClienteDto novoCliente) {
		Usuario cliente = usuarioService.converteNovoClienteDTOEmEntidade(novoCliente);
		cliente = usuarioService.salvar(cliente);
		return new ResponseEntity<ExibirClienteDto>(new ExibirClienteDto(cliente), HttpStatus.CREATED);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ExibirClienteDto> atualizar(@Valid @RequestBody NovoClienteDto usuarioDto, @PathVariable Long id) {
		Usuario usuario = usuarioService.converteNovoClienteDTOEmEntidade(usuarioDto);
		usuario.setUsuarioId(id);
		usuario = usuarioService.atualizar(usuario, id);
		return new ResponseEntity<ExibirClienteDto>(new ExibirClienteDto(usuario), HttpStatus.OK);
	}
}
