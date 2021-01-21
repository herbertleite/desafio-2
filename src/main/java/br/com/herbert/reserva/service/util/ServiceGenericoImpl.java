package br.com.herbert.reserva.service.util;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.herbert.reserva.exception.ObjetoNaoEncontradoException;
/*Serviço abstrato de um service que sera utilizado em todos os services onde passaremos A classe no E e Long no K*/
@Service
public abstract class ServiceGenericoImpl <E, K> implements ServiceGenerico<E, K> {

	private JpaRepository<E, K> jpaRepository;

	public ServiceGenericoImpl(JpaRepository<E, K> jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	public ServiceGenericoImpl() {
	}
	

	
	/**
	 * Salva a entidade passada e retorna a entidade atualizada e com identificador definido.
	 */
	@Override
	public E salvar(E entity) {
		return jpaRepository.save(entity);
	}
	
	/**
	 * Atualiza os dados de uma entidade de forma simples. Apenas pega os dados da entidade e atualiza no banco.
	 */
	public E atualizar(E entity, K key) {
		return jpaRepository.save(entity);
	}
	
	/**
	 * Busca uma entidade pela sua chave. Caso ela não exista é retornado um objeto null.
	 */
	@Override
	public E buscar(K key) {
		Optional<E> obj = jpaRepository.findById(key);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Id nao encontrado"));
	}
	
	
	/**
	 * Remove um entidade do banco de acordo com o id do objeto.
	 */
	@Override
	public void remover(K key) {
		jpaRepository.deleteById(key);
	}
	
	/**
	 * Lista todos os registros da entidade no banco.
	 */
	@Override
	public List<E> listarTodos() {
		return jpaRepository.findAll();
	}
	
	public Page<E> buscaPaginada(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return jpaRepository.findAll(pageRequest);
	}
	
	public List<E> saveAll(List<E> entities) {
		return jpaRepository.saveAll(entities);
	}


}
