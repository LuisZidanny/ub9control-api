package br.com.applicationfinancialmanagement.repository.lancamento;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import br.com.applicationfinancialmanagement.model.Lancamento;
import br.com.applicationfinancialmanagement.model.Usuario;
import br.com.applicationfinancialmanagement.repository.filter.LancamentoFilter;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		

		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		
		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		return query.getResultList();
	}

	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (lancamentoFilter.getDataTrabalhoDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("dataTrabalho"), lancamentoFilter.getDataTrabalhoDe()));
		}
		
		if (lancamentoFilter.getDataTrabalhoAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get("dataTrabalho"), lancamentoFilter.getDataTrabalhoAte()));
		}
		
		if (lancamentoFilter.getUsuarioId() != null) {
			Join<Lancamento,  Usuario> join = root.join("usuario");
			Predicate lancamentoPorUsuario = builder.equal(join.get("codigo"), lancamentoFilter.getUsuarioId());
			predicates.add(lancamentoPorUsuario);
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}
