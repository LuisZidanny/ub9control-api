package br.com.applicationfinancialmanagement.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import br.com.applicationfinancialmanagement.model.Lancamento;
import br.com.applicationfinancialmanagement.model.Usuario;
import br.com.applicationfinancialmanagement.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> , LancamentoRepositoryQuery{
	
	@Query(
		value = "SELECT * FROM lancamento WHERE WEEK (data_trabalho , 1) = WEEKOFYEAR( NOW() ) AND YEAR(data_trabalho) = YEAR( NOW() ) AND usuario = ?1 ORDER BY data_trabalho",
		nativeQuery = true
	)
	public List<Lancamento> findBylancamentoSemanal(Long codigo);
	
	@Query(
		value = "SELECT * FROM lancamento WHERE data_trabalho = CURDATE() AND usuario = ?1",
		nativeQuery = true
	)
	public Lancamento findBylancamentoDiario(Long codigo);
	
	@Query(
		value = "SELECT * FROM lancamento WHERE MONTH(data_trabalho)=MONTH(CURDATE()) AND YEAR(data_trabalho)=YEAR(CURDATE()) AND usuario = ?1 ORDER BY data_trabalho",
		nativeQuery = true
	)
	public List<Lancamento> findBylancamentoMensal(Long codigo);
	
	public Optional<Lancamento> findByDataTrabalhoAndUsuario(LocalDate dataTrabalho , Usuario codigo);
}
