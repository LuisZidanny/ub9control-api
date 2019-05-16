package br.com.applicationfinancialmanagement.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "lancamento")
public class Lancamento implements Comparable<Lancamento>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	private Double uber = 0.0;
	private Double novenove = 0.0;
	private Double cabify = 0.0;
	private Double particular = 0.0;
	private Double combustivel = 0.0;
	private Double refeicao = 0.0;
	private String diaSemana;
	
	@Column(name = "data_trabalho")
	private LocalDate dataTrabalho;
	
	@ManyToOne
	@JoinColumn(name = "usuario")
	private Usuario usuario;

	@JsonIgnore
	@Transient
	public Double getReceita() {
		return getUber() + getNovenove() + getCabify() + getParticular();
	}
	
	@JsonIgnore
	@Transient
	public Double getDespesa() {
		return getCombustivel() + getRefeicao();
	}
	
	@JsonIgnore
	@Transient
	public Double getTotal() {
		return (this.getReceita() - this.getDespesa());
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
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Lancamento [codigo=" + codigo + ", uber=" + uber + ", novenove=" + novenove + ", cabify=" + cabify
				+ ", particular=" + particular + ", combustivel=" + combustivel + ", refeicao=" + refeicao
				+ ", dataTrabalho=" + dataTrabalho + "]";
	}

	@Override
	public int compareTo(Lancamento lancamento) {
		return getDataTrabalho().compareTo(lancamento.getDataTrabalho());
	}
	
	
}
