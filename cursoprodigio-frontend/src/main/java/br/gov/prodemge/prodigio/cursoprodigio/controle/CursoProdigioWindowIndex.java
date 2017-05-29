package br.gov.prodemge.prodigio.cursoprodigio.controle;

import java.util.Map;

import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;

import br.gov.prodemge.ssc.comuns.constantes.Constantes;
import br.gov.prodemge.ssc.interfaces.IUnidade;
import br.gov.prodigio.controle.WindowIndex;

public class CursoProdigioWindowIndex extends WindowIndex{
	
	@Override
	protected void exibeTelaDeIntroducao(IUnidade unidade) {
		AbstractComponent tela = getTela();

		tela.setAttribute(Constantes.UNIDADE_AUTENTICADA, unidade,
				Component.SESSION_SCOPE);
		tela.setAttribute(PARAMETRO_SESSAO_LOGIN_UNIDADE_EXECUTORA,
				String.valueOf(unidade.getCodigoOrgao()),
				Component.SESSION_SCOPE);

		Component includeIntroducao = tela.getFellow("introducao");
		String urlcasodeuso = "introducao.zul";
		// redireciona o meio da pagina
		Component component = null;
		component = includeIntroducao.getFirstChild();
		if (component != null) {
			component.detach();
		}
		includeIntroducao.getChildren().clear();

		Map attributes = tela.getAttributes(Component.SESSION_SCOPE);
		attributes.put("casodeusoatual", urlcasodeuso);

		component = Executions.createComponents(urlcasodeuso,
				includeIntroducao, attributes);
		includeIntroducao.appendChild(component);
	}
	
}