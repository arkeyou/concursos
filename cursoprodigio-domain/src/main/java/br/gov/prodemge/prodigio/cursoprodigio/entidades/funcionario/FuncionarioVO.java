package br.gov.prodemge.prodigio.cursoprodigio.entidades.funcionario;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.gov.prodemge.prodigio.cursoprodigio.entidades.CursoProdigioBaseVO;
import br.gov.prodemge.prodigio.cursoprodigio.entidades.cidade.CidadeVO;
import br.gov.prodemge.prodigio.cursoprodigio.entidades.cidade.EstadoSimpleVO;
import br.gov.prodemge.prodigio.cursoprodigio.entidades.cidade.PaisVO;
import br.gov.prodemge.prodigio.cursoprodigio.entidades.curso.CursoVO;
import br.gov.prodemge.prodigio.cursoprodigio.enums.EstadoCivilEnum;
import br.gov.prodemge.prodigio.cursoprodigio.enums.curso.CursoAreaEnum;
import br.gov.prodemge.prodigio.cursoprodigio.enums.funcionario.FuncionarioAnexoEnum;
import br.gov.prodigio.comuns.anotacoes.ExclusaoLogica;
import br.gov.prodigio.comuns.anotacoes.Lookup;

@Lookup
@Entity
@Table(name = "tbfuncionario")
@ExclusaoLogica
public class FuncionarioVO extends CursoProdigioBaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	// private DadosBasicosVO dadosBasicos;
	private String cpf;
	private String matricula;
	private PaisVO paisVO;
	private EstadoSimpleVO estadoVO;
	private CidadeVO cidade;
	private BigDecimal salario;
//	private FuncionarioEnderecoVO funcionarioEndereco = new FuncionarioEnderecoVO();
	private FuncionarioEnderecoVO funcionarioEndereco = null;  // feito para funcionar a data movimentação
	private Set<FuncionarioCursoVO> cursos = new LinkedHashSet<FuncionarioCursoVO>();
	private Set<FuncionarioAnexoVO> anexos = new LinkedHashSet<FuncionarioAnexoVO>();
	private Set<FuncionarioDependenteVO> dependentes = new LinkedHashSet<FuncionarioDependenteVO>();
	private Boolean temFilhos;
	private EstadoCivilEnum estadoCivil;
	private String breveDescricao;

	@Override
	@Id
	@SequenceGenerator(name = "sq_funcionario", sequenceName = "sq_funcionario", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_funcionario")
	public Long getId() {
		return super.getId();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	@ManyToOne
	public CidadeVO getCidade() {
		return cidade;
	}

	public void setCidade(CidadeVO cidade) {
		this.cidade = cidade;
	}

	@Transient
	public PaisVO getPaisVO() {
		if (this.paisVO == null && cidade != null && cidade.getEstado() != null) {
			this.paisVO = cidade.getEstado().getPais();
		}
		return this.paisVO;
	}

	public void setPaisVO(PaisVO paisVO) {
		this.estadoVO = new EstadoSimpleVO();
		this.estadoVO.setPais(paisVO);
		this.cidade = new CidadeVO();
		this.cidade.setEstado(this.estadoVO);
		this.paisVO = paisVO;
	}

	@Transient
	public EstadoSimpleVO getEstadoVO() {
		if (estadoVO == null && cidade != null) {
			this.estadoVO = cidade.getEstado();
		}
		return this.estadoVO;
	}

	public void setEstadoVO(EstadoSimpleVO estadoVO) {
		this.cidade = new CidadeVO();
		this.cidade.setEstado(estadoVO);
		this.estadoVO = estadoVO;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "funcionario", orphanRemoval = true)
	@OrderBy("id")
	public Set<FuncionarioCursoVO> getCursos() {
		return cursos;
	}

	public void setCursos(Set<FuncionarioCursoVO> cursos) {
		this.cursos = cursos;
	}

	@Transient
	public Set<FuncionarioCursoVO> getCursosExatas() {
		Set<FuncionarioCursoVO> cursosExatas = new HashSet<FuncionarioCursoVO>();
		for (FuncionarioCursoVO funcionarioCursoVO : cursos) {
			CursoVO cursoVO = funcionarioCursoVO.getCursoVO();
			CursoAreaEnum areaCurso = cursoVO.getAreaCurso();
			if (cursoVO != null && areaCurso != null && areaCurso.equals(CursoAreaEnum.EXATAS))
				cursosExatas.add(funcionarioCursoVO);
		}
		return cursosExatas;
	}

	public void setCursosExatas(Set<FuncionarioCursoVO> cursos) {
		if (this.cursos != null)
			this.cursos.addAll(cursos);
	}

	@Transient
	public Set<FuncionarioCursoVO> getCursosHumanas() {
		Set<FuncionarioCursoVO> cursosAux = new HashSet<FuncionarioCursoVO>();
		for (FuncionarioCursoVO funcionarioCursoVO : cursos) {
			CursoVO cursoVO = funcionarioCursoVO.getCursoVO();
			CursoAreaEnum areaCurso = cursoVO.getAreaCurso();
			if (cursoVO != null && areaCurso != null && areaCurso.equals(CursoAreaEnum.HUMANAS))
				cursosAux.add(funcionarioCursoVO);
		}
		return cursosAux;
	}

	public void setCursosHumanas(Set<FuncionarioCursoVO> cursos) {
		if (this.cursos != null)
			this.cursos.addAll(cursos);
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "funcionario", orphanRemoval = true)
	public FuncionarioEnderecoVO getFuncionarioEndereco() {
		return funcionarioEndereco;
	}

	public void setFuncionarioEndereco(FuncionarioEnderecoVO funcionarioEndereco) {
		this.funcionarioEndereco = funcionarioEndereco;
		if(this.funcionarioEndereco != null)
			this.funcionarioEndereco.setFuncionario(this);
	}

	@Override
	@Lob
	public byte[] getImagem() {
		return super.getImagem();
	}

	@Transient
	public String getCursosStr() {
		String cursosStr = "";
		Set<FuncionarioCursoVO> cursos2 = getCursos();
		for (FuncionarioCursoVO funcionarioCursoVO : cursos2) {
			cursosStr = funcionarioCursoVO.getCursoVO().getNome() + ", " + cursosStr;
		}
		return cursosStr;
	}

	public Boolean getTemFilhos() {
		return temFilhos;
	}

	public void setTemFilhos(Boolean temFilhos) {
		this.temFilhos = temFilhos;
	}

	@Enumerated(EnumType.STRING)
	public EstadoCivilEnum getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivilEnum estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getBreveDescricao() {
		return breveDescricao;
	}

	public void setBreveDescricao(String breveDescricao) {
		this.breveDescricao = breveDescricao;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "funcionario", orphanRemoval = true)
	@OrderBy("id")
	public Set<FuncionarioAnexoVO> getAnexos() {
		return anexos;
	}

	public void setAnexos(Set<FuncionarioAnexoVO> anexos) {
		this.anexos = anexos;
	}

	@Transient
	public Set<FuncionarioAnexoVO> getAnexosMemorando() {
		Set<FuncionarioAnexoVO> anexosAux = new HashSet<FuncionarioAnexoVO>();
		for (FuncionarioAnexoVO funcionarioAnexoVO : this.anexos) {
//			Para funcionar os anexos ou comente este metodo ou utilize o mérodo inicializaNovoDetalhe do Ctr.
			if (funcionarioAnexoVO.getAnexoTipo() != null && funcionarioAnexoVO.getAnexoTipo().equals(FuncionarioAnexoEnum.MEMORANDO))
				anexosAux.add(funcionarioAnexoVO);
		}
		return anexosAux;
	}

	public void setAnexosMemorando(Set<FuncionarioAnexoVO> anexos) {
		if (this.anexos != null)
			this.anexos.addAll(anexos);
	}

	@Transient
	public Set<FuncionarioAnexoVO> getAnexosOficio() {
		Set<FuncionarioAnexoVO> anexosAux = new HashSet<FuncionarioAnexoVO>();
		for (FuncionarioAnexoVO funcionarioAnexoVO : this.anexos) {
			if (funcionarioAnexoVO.getAnexoTipo() != null && funcionarioAnexoVO.getAnexoTipo().equals(FuncionarioAnexoEnum.OFICIO))
				anexosAux.add(funcionarioAnexoVO);
		}
		return anexosAux;
	}

	public void setAnexosOficio(Set<FuncionarioAnexoVO> anexos) {
		if (this.anexos != null)
			this.anexos.addAll(anexos);
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "funcionario")
	@OrderBy("id")
	public Set<FuncionarioDependenteVO> getDependentes() {
		return dependentes;
	}

	public void setDependentes(Set<FuncionarioDependenteVO> dependentes) {
		this.dependentes = dependentes;
	}

	/*
	 * @Embedded public DadosBasicosVO getDadosBasicos() { return dadosBasicos; }
	 * 
	 * public void setDadosBasicos(DadosBasicosVO dadosBasicos) { this.dadosBasicos = dadosBasicos; }
	 */
}
