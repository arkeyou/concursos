package br.gov.prodemge.prodigio.cursoprodigio.entidades.curso;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.gov.prodemge.prodigio.cursoprodigio.entidades.CursoProdigioBaseVO;
import br.gov.prodemge.prodigio.cursoprodigio.enums.curso.CursoAreaEnum;
import br.gov.prodigio.comuns.anotacoes.Lookup;

@Lookup
@Entity
@Table(name = "tb_curso")
public class CursoVO  extends CursoProdigioBaseVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6695914097445072610L;
	private String nome;
	private String ementa;
	private CursoAreaEnum areaCurso; 
	
	@Override
	@Id
	@SequenceGenerator(name = "sq_curso", sequenceName = "sq_curso", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_curso")
	public Long getId() {
		return super.getId();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

	@Override
	public String toString(){
		return this.nome;
	}

	public String getEmenta() {
		return ementa;
	}

	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

	@Enumerated(EnumType.STRING)
	public CursoAreaEnum getAreaCurso() {
		return areaCurso;
	}

	public void setAreaCurso(CursoAreaEnum areaCurso) {
		this.areaCurso = areaCurso;
	}
	
}
	
	
