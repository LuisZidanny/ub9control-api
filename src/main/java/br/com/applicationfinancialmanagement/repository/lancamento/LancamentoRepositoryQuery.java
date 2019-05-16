package br.com.applicationfinancialmanagement.repository.lancamento;

import java.util.List;

import br.com.applicationfinancialmanagement.model.Lancamento;
import br.com.applicationfinancialmanagement.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

	public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
}
