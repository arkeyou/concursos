package br.gov.prodemge.prodigio.cursoprodigio.entidades.cidade;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.gov.prodemge.prodigio.cursoprodigio.entidades.CursoProdigioBaseVO;
import br.gov.prodigio.comuns.anotacoes.Lookup;

@Lookup
@Entity
@Table(name="tb_cidade")
public class CidadeVO extends CursoProdigioBaseVO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5870954401831508693L;
	private String nome;
	private String apelido;
	private EstadoSimpleVO estado;
	
	@Override
	@Id
	@SequenceGenerator(name = "sq_cidade", sequenceName = "sq_cidade", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_cidade")
	public Long getId() {
		return super.getId();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	public EstadoSimpleVO getEstado() {
		return estado;
	}

	public void setEstado(EstadoSimpleVO estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		if(getNome() == null)
			setNome("");
		return getNome();
	}
	

}
