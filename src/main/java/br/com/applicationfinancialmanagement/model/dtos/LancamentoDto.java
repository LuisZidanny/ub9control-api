package br.com.applicationfinancialmanagement.model.dtos;

import java.time.LocalDate;

import br.com.applicationfinancialmanagement.model.Lancamento;

public class LancamentoDto {

	private Long codigo;
	private Double uber;
	private Double novenove;
	private Double cabify;
	private Double particular;
	private LocalDate dataTrabalho;
	private Double combustivel;
	private Double refeicao;
	private Double receita;
	private Double despesa;
	private Double total;
	private String diaSemana;
	
	public LancamentoDto(Lancamento lancamento) {
		this.codigo = lancamento.getCodigo();
		this.uber = lancamento.getUber();
		this.novenove = lancamento.getNovenove();
		this.cabify = lancamento.getCabify();
		this.particular = lancamento.getParticular();
		this.dataTrabalho = lancamento.getDataTrabalho();
		this.combustivel = lancamento.getCombustivel();
		this.refeicao = lancamento.getRefeicao();
		this.receita = lancamento.getReceita();
		this.despesa = lancamento.getDespesa();
		this.total = lancamento.getTotal();
		this.diaSemana = lancamento.getDiaSemana();
	}

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Double getUber() {
		return uber;
	}
	public void setUber(Double uber) {
		this.uber = uber;
	}
	public Double getNovenove() {
		return novenove;
	}
	public void setNovenove(Double novenove) {
		this.novenove = novenove;
	}

	public Double getCabify() {
		return cabify;
	}
	public void setCabify(Double cabify) {
		this.cabify = cabify;
	}
	public Double getParticular() {
		return particular;
	}
	public void setParticular(Double particular) {
		this.particular = particular;
	}
	public LocalDate getDataTrabalho() {
		return dataTrabalho;
	}
	public void setDataTrabalho(LocalDate dataTrabalho) {
		this.dataTrabalho = dataTrabalho;
	}
	public Double getCombustivel() {
		return combustivel;
	}
	public void setCombustivel(Double combustivel) {
		this.combustivel = combustivel;
	}
	public Double getRefeicao() {
		return refeicao;
	}
	public void setRefeicao(Double refeicao) {
		this.refeicao = refeicao;
	}
	public Double getReceita() {
		return receita;
	}
	public void setReceita(Double receita) {
		this.receita = receita;
	}
	public Double getDespesa() {
		return despesa;
	}
	public void setDespesa(Double despesa) {
		this.despesa = despesa;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

}
