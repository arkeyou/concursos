package br.gov.prodemge.prodigio.cursoprodigio.negocio.compromisso;

import java.util.LinkedHashMap;
import java.util.Map;

import br.gov.prodemge.prodigio.cursoprodigio.entidades.compromisso.CompromissoVO;
import br.gov.prodigio.comuns.anotacoes.RegraDeNegocio;
import br.gov.prodigio.comuns.anotacoes.enumeracao.MomentoDeExecucao;
import br.gov.prodigio.comuns.constantes.Constantes;
import br.gov.prodigio.comuns.excecoes.ViolacaoDeRegraEx;
import br.gov.prodigio.negocio.ProRN;

public class CompromissoRN extends ProRN {

	@RegraDeNegocio(autor = "Érico Gomes", codigo = "RN_01", ordem = 1, fluxo = Constantes.FLUXO.LISTAR_BASEADO_NO_EXEMPLO, momentoDeExecucao = MomentoDeExecucao.ANTES)
	public void limpaLista(CompromissoVO compromissoVO) {
		compromissoVO.getAtributo("listaObjetos");
	}
	
	@RegraDeNegocio(autor = "Érico Gomes", codigo = "RN_01", ordem = 1, fluxo = "gravar", momentoDeExecucao = MomentoDeExecucao.ANTES)
	public void verificaDescricaoVazia(CompromissoVO compromissoVO) {
		if (compromissoVO.getDescricao() == null || compromissoVO.getDescricao() .isEmpty())   
	           {
			throw new ViolacaoDeRegraEx("O Compromisso precisa de uma descrição.");
		}
	}
	
	@RegraDeNegocio(autor = "Érico Gomes", codigo = "RN_02", ordem = 2, fluxo = "gravar", momentoDeExecucao = MomentoDeExecucao.ANTES)
	public void verificaDataFimVazia(CompromissoVO compromissoVO) {
		if (compromissoVO.getDataFim() == null)   
	           {
			throw new ViolacaoDeRegraEx("A data fim do compromisso não pode ser vazia.");
		}
	}
	
	@RegraDeNegocio(autor = "Érico Gomes", codigo = "RN_03", ordem = 3, fluxo = "gravar", momentoDeExecucao = MomentoDeExecucao.ANTES)
	public void verificaDataInicioVazia(CompromissoVO compromissoVO) {
		if (compromissoVO.getDataInicio() == null) {
			Map<String, String> mapaErros = new LinkedHashMap<String, String>();
			mapaErros.put("dataInicio", "A data inicio do compromisso não pode ser vazia.");
			throw new ViolacaoDeRegraEx(mapaErros);
		}
	}
	
	@RegraDeNegocio(autor = "Érico Gomes", codigo = "RN_04", ordem = 4, fluxo = "gravar", momentoDeExecucao = MomentoDeExecucao.ANTES)
	public void verificaDataInicioAposDataFim(CompromissoVO compromissoVO) {
		if (compromissoVO.getDataInicio().after(compromissoVO.getDataFim())) {
			throw new ViolacaoDeRegraEx("A data do compromisso deve ser antes da data fim.");
		}
	}
}
