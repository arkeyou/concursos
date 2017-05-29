package br.gov.prodemge.prodigio.cursoprodigio.entidades.funcionario;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.gov.prodemge.prodigio.cursoprodigio.entidades.CursoProdigioBaseVO;

@Entity
@Table(name = "TB_FUNCIONARIO_DEPENDENTE")
@Inheritance(strategy = InheritanceType.JOINED)
public class FuncionarioDependenteVO extends CursoProdigioBaseVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1066209359631621408L;
	private FuncionarioVO funcionarioVO;
	private String nome;

	@Override
	@Id
	@SequenceGenerator(name = "SQ_FUNCIONARIO_DEPENDENTE", sequenceName = "SQ_FUNCIONARIO_DEPENDENTE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_FUNCIONARIO_DEPENDENTE")
	public Long getId() {
		return super.getId();
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_FUNCIONARIO")
	public FuncionarioVO getFuncionario() {
		return funcionarioVO;
	}

	public void setFuncionario(FuncionarioVO funcionarioVO) {
		this.funcionarioVO = funcionarioVO;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	@Lob
	public byte[] getImagem() {
		return super.getImagem();
	}
}
