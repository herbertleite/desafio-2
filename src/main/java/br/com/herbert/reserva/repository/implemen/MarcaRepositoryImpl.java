package br.com.herbert.reserva.repository.implemen;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.herbert.reserva.model.Marca;
import br.com.herbert.reserva.repository.customizer.MarcaRepositoryCustom;

public class MarcaRepositoryImpl implements MarcaRepositoryCustom{

	//é usado especificamente quando precisamos injetar um entityManager
	@PersistenceContext
	private EntityManager entityManager;
	
	//metodo para consulta por nome utilizando criterea
	@Override
	public Optional<Marca> buscaPorNome(String nome) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Marca> cq = cb.createQuery(Marca.class);

		Root<Marca> root = cq.from(Marca.class);
		CriteriaQuery<Marca> query = cq.select(root);

		Predicate predicado = cb.equal(root.get("nome"), nome);

		Predicate[] predicates = { predicado };

		query.where(predicates);

		TypedQuery<Marca> tq = entityManager.createQuery(query);

		try {
			return Optional.of(tq.getSingleResult());
		} catch (NoResultException e) {
			return null;
		}
	}
}
