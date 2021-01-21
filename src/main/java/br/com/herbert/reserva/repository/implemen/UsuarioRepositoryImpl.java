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

import br.com.herbert.reserva.model.Usuario;
import br.com.herbert.reserva.repository.customizer.UsuarioRepositoryCustom;

public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Optional<Usuario> buscaPorEmail(String email) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);

		Root<Usuario> root = cq.from(Usuario.class);
		CriteriaQuery<Usuario> query = cq.select(root);

		Predicate predicado = cb.equal(root.get("email"), email);

		Predicate[] predicates = { predicado };

		query.where(predicates);

		TypedQuery<Usuario> tq = entityManager.createQuery(query);

		try {
			return Optional.of(tq.getSingleResult());
		} catch (NoResultException e) {
			return null;
		}
	}
}
