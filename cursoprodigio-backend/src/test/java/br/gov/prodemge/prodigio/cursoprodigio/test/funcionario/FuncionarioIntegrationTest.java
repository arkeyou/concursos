package br.gov.prodemge.prodigio.cursoprodigio.test.funcionario;

import java.util.Date;

import org.fest.assertions.Assertions;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.gov.prodemge.prodigio.cursoprodigio.entidades.funcionario.FuncionarioVO;
import br.gov.prodemge.prodigio.cursoprodigio.test.CursoProdigioBaseIntegrationTest;
import br.gov.prodigio.comuns.excecoes.ViolacaoDeRegraEx;
import br.gov.prodigio.entidades.StatusDoRegistro;

@RunWith(Arquillian.class)
public class FuncionarioIntegrationTest extends CursoProdigioBaseIntegrationTest {
	
	@Test
	public void gravarFuncionario() {
		FuncionarioVO funcionario = new FuncionarioVO();
		funcionario.setIpMovimentacao("127.0.0.1");
		funcionario.setTsMovimentacao(new Date());
		funcionario.setStatusDoRegistro(StatusDoRegistro.ATIVO);
		funcionario.setTpOperacao("EM_EDICAO");
		
		Exception e1 = capturaExcecaoAoGravar(funcionario);
		Assertions.assertThat(e1).isExactlyInstanceOf(ViolacaoDeRegraEx.class).hasMessage("O funcionario precisa de um nome.");
		
	}
}