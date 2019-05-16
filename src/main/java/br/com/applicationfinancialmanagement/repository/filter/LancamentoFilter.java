package br.com.applicationfinancialmanagement.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class LancamentoFilter {
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataTrabalhoDe;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataTrabalhoAte;
	
	private Long usuarioId;

	public LocalDate getDataTrabalhoDe() {
		return dataTrabalhoDe;
	}

	public void setDataTrabalhoDe(LocalDate dataTrabalhoDe) {
		this.dataTrabalhoDe = dataTrabalhoDe;
	}

	public LocalDate getDataTrabalhoAte() {
		return dataTrabalhoAte;
	}

	public void setDataTrabalhoAte(LocalDate dataTrabalhoAte) {
		this.dataTrabalhoAte = dataTrabalhoAte;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
}
