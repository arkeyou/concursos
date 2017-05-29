package br.gov.prodemge.prodigio.cursoprodigio.entidades.compromisso;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.gov.prodemge.prodigio.cursoprodigio.entidades.CursoProdigioBaseVO;

@Entity
public class CompromissoVO extends CursoProdigioBaseVO {

	private String descricao;
	private Date dataInicio;
	private Date dataFim;

	@Override
	@Id
	@SequenceGenerator(name = "sq_compromisso", sequenceName = "sq_compromisso", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_compromisso")
	public Long getId() {
		return super.getId();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date data) {
		this.dataInicio = data;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date data) {
		this.dataFim = data;
	}

}
