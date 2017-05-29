package br.gov.prodemge.prodigio.cursoprodigio.negocio.compromisso;

import java.util.LinkedHashMap;
import java.util.Map;

import br.gov.prodemge.prodigio.cursoprodigio.entidades.compromisso.CompromissoVO;
import br.gov.prodigio.comuns.anotacoes.RegraDeNegocio;
import br.gov.prodigio.comuns.anotacoes.enumeracao.MomentoDeExecucao;
import br.gov.prodigio.comuns.excecoes.ViolacaoDeRegraEx;
import br.gov.prodigio.negocio.ProRN;

public class CompromissoRN extends ProRN {

	@RegraDeNegocio(autor = "Autor", codigo = "CODIGO_DA_REGRA", ordem = 1, fluxo = "gravar", momentoDeExecucao = MomentoDeExecucao.ANTES)
	public void verificaDescricaoVazia(CompromissoVO compromissoVO) {
		if (compromissoVO.getDescricao() == null
				|| compromissoVO.getDescricao().isEmpty()) {
			throw new ViolacaoDeRegraEx(
					"O Compromisso precisa de uma descrição.");
		}
	}

	@RegraDeNegocio(autor = "Autor", codigo = "CODIGO_DA_REGRA", ordem = 1, fluxo = "gravar", momentoDeExecucao = MomentoDeExecucao.ANTES)
	public void verificaDataMaior(CompromissoVO compromissoVO) {
		if (compromissoVO.getDataFim().before(compromissoVO.getDataInicio())) {
			Map<String, String> erros = new LinkedHashMap<String, String>();
			erros.put("dataInicio", "A data fim não pode ser menor que a data inicio");
			erros.put("dataFim", "A data inicio não pode ser maior que a data fim");
			throw new ViolacaoDeRegraEx(erros);
		}
	}

	@RegraDeNegocio(autor = "Autor", codigo = "CODIGO_DA_REGRA", ordem = 1, fluxo = "gravar", momentoDeExecucao = MomentoDeExecucao.ANTES)
	public void verificaDataMenor(CompromissoVO compromissoVO) {
		if (compromissoVO.getDataInicio().after(compromissoVO.getDataFim())) {
			Map<String, String> erros = new LinkedHashMap<String, String>();
			erros.put("dataInicio", "A data fim não pode ser menor que a data inicio");
			erros.put("dataFim", "A data inicio não pode ser maior que a data fim");
			throw new ViolacaoDeRegraEx(erros);
		}
	}
}
