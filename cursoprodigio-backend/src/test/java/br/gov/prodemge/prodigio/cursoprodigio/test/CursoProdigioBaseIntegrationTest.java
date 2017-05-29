package br.gov.prodemge.prodigio.cursoprodigio.test;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import br.gov.prodigio.comuns.IProFacadeRemote;
import br.gov.prodigio.entidades.ProBaseVO;
import br.gov.prodigio.test.ProBaseIntegrationTest;

public class CursoProdigioBaseIntegrationTest {

	protected static final String WAR = ".war";
	protected static final String PROJETO_WEB = "cursoprodigio";
	protected static final String[] PACOTES = new String[]{
		"br.gov.prodemge.prodigio.cursoprodigio.entidades",
		"br.gov.prodemge.prodigio.cursoprodigio.negocio",
		"br.gov.prodemge.prodigio.cursoprodigio.test"
	};
	
	@EJB
	protected IProFacadeRemote facade;
	
	@Deployment
	public static WebArchive createDeploy() {
		return ProBaseIntegrationTest.instanciarAplicacao(
			PROJETO_WEB, 
			WAR, 
			PACOTES);
	}
	

	protected Exception capturaExcecaoAoGravar(ProBaseVO proVO) {
		Exception e1 = new Exception("Excecao nao esperada!");
		try {
			facade.gravar(proVO);
		} catch (Exception e) {
			e1 = e;
		}
		return e1;
	}

}
