package br.gov.prodemge.prodigio.cursoprodigio.negocio.funcionario;

import  br.gov.prodemge.prodigio.cursoprodigio.entidades.funcionario.FuncionarioVO;
import br.gov.prodemge.prodigio.cursoprodigio.negocio.CursoProdigioBaseRN;
import br.gov.prodigio.comuns.anotacoes.RegraDeNegocio;
import br.gov.prodigio.comuns.anotacoes.enumeracao.MomentoDeExecucao;
import br.gov.prodigio.comuns.excecoes.ViolacaoDeRegraEx;


public class FuncionarioRN extends CursoProdigioBaseRN {

	@RegraDeNegocio(autor = "Autor", codigo = "CODIGO_DA_REGRA", ordem = 1, fluxo = "gravar", momentoDeExecucao = MomentoDeExecucao.ANTES)
	public void verificaNome(FuncionarioVO funcionarioVO) {
		    if (funcionarioVO.getNome() == null || funcionarioVO.getNome().isEmpty())   
	         {
			throw new ViolacaoDeRegraEx("O funcionario precisa de um nome.");
		    }
		}

	
}
