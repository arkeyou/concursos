package br.gov.prodemge.prodigio.cursoprodigio.entidades.funcionario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.gov.prodemge.prodigio.cursoprodigio.entidades.CursoProdigioBaseVO;
import br.gov.prodemge.prodigio.cursoprodigio.entidades.cidade.BairroVO;
import br.gov.prodemge.prodigio.cursoprodigio.entidades.cidade.CidadeVO;
import br.gov.prodemge.prodigio.cursoprodigio.entidades.cidade.LogradouroVO;

@Entity
@Table(name="tb_funcionarioendereco")
public class FuncionarioEnderecoVO extends CursoProdigioBaseVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5847809139073294584L;
	private FuncionarioVO funcionario;
	private LogradouroVO logradouro;
	private String numero;
	
	//transientes
	private BairroVO bairro;
	private CidadeVO cidade;
	private String cep;
	
	@Override
	@Id
	@SequenceGenerator(name = "sq_funcionarioendereco", sequenceName = "sq_funcionarocursos", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_funcionarioendereco")
	public Long getId() {
		return super.getId();
	}


	@ManyToOne
	public FuncionarioVO getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(FuncionarioVO funcionario) {
		this.funcionario = funcionario;
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}


	@Transient
	public BairroVO getBairro() {
		if(logradouro != null && logradouro.getBairro() != null){
			this.bairro = logradouro.getBairro();
		}
		return bairro;
	}

	public void setBairro(BairroVO bairro) {
		this.bairro = bairro;
	}

	@Transient
	public String getCep() {
		if(logradouro != null && logradouro.getCep() != null){
			this.cep = logradouro.getCep();
		}
		return cep;
	}


	public void setCep(String cep) {
		this.cep = cep;
	}


	@Transient
	public CidadeVO getCidade() {
		return cidade;
	}


	public void setCidade(CidadeVO cidade) {
		if(logradouro != null && logradouro.getBairro() != null && logradouro.getBairro()!=null){
			this.cidade = logradouro.getBairro().getCidade();
		}
		this.cidade = cidade;
	}


	@ManyToOne
	public LogradouroVO getLogradouro() {
		return logradouro;
	}


	public void setLogradouro(LogradouroVO logradouro) {
		this.logradouro = logradouro;
	}
	
	
	
}