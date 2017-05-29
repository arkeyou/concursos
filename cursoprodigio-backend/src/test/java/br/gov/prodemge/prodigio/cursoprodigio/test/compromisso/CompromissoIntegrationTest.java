package br.gov.prodemge.prodigio.cursoprodigio.test.compromisso;

import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;

import org.fest.assertions.Assertions;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.prodemge.prodigio.cursoprodigio.entidades.compromisso.CompromissoVO;
import br.gov.prodemge.prodigio.cursoprodigio.test.CursoProdigioBaseIntegrationTest;
import br.gov.prodigio.comuns.excecoes.ViolacaoDeRegraEx;
import br.gov.prodigio.entidades.ProVO.SITUACAO_DO_REGISTRO;
import br.gov.prodigio.entidades.StatusDoRegistro;

@RunWith(Arquillian.class)
public class CompromissoIntegrationTest extends	CursoProdigioBaseIntegrationTest {

	@Test
	public void gravaCompromisso() throws Exception {
		// Preenchendo o objeto compromisso
		CompromissoVO compromisso = new CompromissoVO();
		Date agora = new Date();
		compromisso.setTsMovimentacao(agora);
		compromisso.setIpMovimentacao("127.0.0.1");
		compromisso.setStatusDoRegistro(StatusDoRegistro.ATIVO);
		compromisso.setTpOperacao(SITUACAO_DO_REGISTRO.EM_EDICAO);
		compromisso.setDescricao("Compromisso " + agora);
		compromisso.setCdLoginMovimentacao("2154567899");
		Calendar calendar = Calendar.getInstance();
		compromisso.setDataInicio(agora);
		calendar.setTime(agora);
		calendar.add(Calendar.HOUR, 1);
		calendar.add(Calendar.MINUTE, 30);
		compromisso.setDataFim(calendar.getTime());
		// Enviando o objeto para a gravação.
		Long id = (Long) facade.gravar(compromisso);
		// Conferindo se foi gravado.
		Assertions.assertThat(id).isNotNull();
	}
	
	@Test
	public void gravaCompromissoComDescricaoVazia() {

		CompromissoVO compromisso = new CompromissoVO();
		Date agora = new Date();

		compromisso.setTsMovimentacao(agora);
		compromisso.setIpMovimentacao("127.0.0.1");
		compromisso.setStatusDoRegistro(StatusDoRegistro.ATIVO);
		compromisso.setTpOperacao(SITUACAO_DO_REGISTRO.EM_EDICAO);
		compromisso.setCdLoginMovimentacao("2154567899");
		
		compromisso.setDataInicio(agora);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(agora);
		calendar.add(Calendar.HOUR, 1);
		calendar.add(Calendar.MINUTE, 30);
		compromisso.setDataFim(calendar.getTime());
		compromisso.setDescricao(null);
		
		Exception e1 = capturaExcecaoAoGravar(compromisso);
		Assertions.assertThat(e1).isExactlyInstanceOf(ViolacaoDeRegraEx.class).hasMessage("O Compromisso precisa de uma descrição.");

//		try {
//			Long id = (Long) facade.gravar(compromisso);
//			fail();
//		} catch (ViolacaoDeRegraEx e) {
//			Assertions.assertThat(e).hasMessage(
//					"O Compromisso precisa de uma descrição.");
//		} catch (Exception e) {
//			fail();
//		}
	}
	
	@Test
	public void gravaCompromissoComDataInicioVazia() {

		CompromissoVO compromisso = new CompromissoVO();
		Date agora = new Date();

		compromisso.setTsMovimentacao(agora);
		compromisso.setIpMovimentacao("127.0.0.1");
		compromisso.setStatusDoRegistro(StatusDoRegistro.ATIVO);
		compromisso.setTpOperacao(SITUACAO_DO_REGISTRO.EM_EDICAO);
		compromisso.setCdLoginMovimentacao("2154567899");
		
		compromisso.setDataInicio(null);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(agora);
		calendar.add(Calendar.HOUR, 1);
		calendar.add(Calendar.MINUTE, 30);
		compromisso.setDataFim(calendar.getTime());
		compromisso.setDescricao("desc");
		
		Exception e1 = capturaExcecaoAoGravar(compromisso);
		Assertions.assertThat(e1).isExactlyInstanceOf(ViolacaoDeRegraEx.class).hasMessage("A data inicio do compromisso não pode ser vazia.");

	}
	
	@Test
	public void gravaCompromissoComDataFimVazia() {

		CompromissoVO compromisso = new CompromissoVO();
		Date agora = new Date();

		compromisso.setTsMovimentacao(agora);
		compromisso.setIpMovimentacao("127.0.0.1");
		compromisso.setStatusDoRegistro(StatusDoRegistro.ATIVO);
		compromisso.setTpOperacao(SITUACAO_DO_REGISTRO.EM_EDICAO);
		compromisso.setCdLoginMovimentacao("2154567899");
		
		compromisso.setDataInicio(agora);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(agora);
		calendar.add(Calendar.HOUR, 1);
		calendar.add(Calendar.MINUTE, 30);
		compromisso.setDataFim(null);
		compromisso.setDescricao("desc");
		
		Exception e1 = capturaExcecaoAoGravar(compromisso);
		Assertions.assertThat(e1).isExactlyInstanceOf(ViolacaoDeRegraEx.class).hasMessage("A data fim do compromisso não pode ser vazia.");

	}
	
	@Test
	public void gravaCompromissoComDataInicioAposDataFim() {

		CompromissoVO compromisso = new CompromissoVO();
		Date agora = new Date();

		compromisso.setTsMovimentacao(agora);
		compromisso.setIpMovimentacao("127.0.0.1");
		compromisso.setStatusDoRegistro(StatusDoRegistro.ATIVO);
		compromisso.setTpOperacao(SITUACAO_DO_REGISTRO.EM_EDICAO);
		compromisso.setCdLoginMovimentacao("2154567899");
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(agora);
		calendar.add(Calendar.HOUR, 1);
		calendar.add(Calendar.MINUTE, 30);
		compromisso.setDataFim(agora);
		compromisso.setDataInicio(calendar.getTime());
		compromisso.setDescricao("desc");
		
		Exception e1 = capturaExcecaoAoGravar(compromisso);
		Assertions.assertThat(e1).isExactlyInstanceOf(ViolacaoDeRegraEx.class).hasMessage("A data do compromisso deve ser antes da data fim.");

	}

	
}
