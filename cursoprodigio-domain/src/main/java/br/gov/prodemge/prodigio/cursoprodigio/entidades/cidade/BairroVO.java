package br.gov.prodemge.prodigio.cursoprodigio.entidades.cidade;

import javax.persistence.Entity;
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
@Table(name="tb_bairro")
public class BairroVO extends CursoProdigioBaseVO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5870954401831508693L;
	private String nome;
	private CidadeVO cidade;
	
	@Override
	@Id
	@SequenceGenerator(name = "sq_bairro", sequenceName = "sq_bairro", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_bairro")
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
	public String toString() {
		if(getNome() == null)
			setNome("");
		return getNome();
	}

	@ManyToOne
	public CidadeVO getCidade() {
		return cidade;
	}

	public void setCidade(CidadeVO cidade) {
		this.cidade = cidade;
	}
	

}
