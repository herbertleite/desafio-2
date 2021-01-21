package br.com.herbert.reserva.service.util;

import java.util.List;
/*
 * Interface generica para auxiliar nos services*/
public interface ServiceGenerico <E,K> {
    public E salvar(E entity) ;
    public void remover(K key);
    public E buscar(K key);
    public List<E> listarTodos();
	public E atualizar(E entity, K key);

}
