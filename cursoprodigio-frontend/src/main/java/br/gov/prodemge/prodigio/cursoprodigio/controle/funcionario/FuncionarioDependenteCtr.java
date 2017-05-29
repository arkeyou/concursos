package br.gov.prodemge.prodigio.cursoprodigio.controle.funcionario;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;

import br.gov.prodemge.prodigio.cursoprodigio.controle.CursoProdigioBaseCtr;
import br.gov.prodemge.prodigio.cursoprodigio.entidades.CursoProdigioBaseVO;
import br.gov.prodemge.prodigio.cursoprodigio.entidades.funcionario.FuncionarioDependenteVO;

public class FuncionarioDependenteCtr extends CursoProdigioBaseCtr<FuncionarioDependenteVO> {

	FuncionarioDependenteVO dependente;
	
	public void doAfterCompose(Component tela) throws Exception {
		Desktop desktop = getTela().getDesktop();
		dependente = (FuncionarioDependenteVO) desktop.getAttribute("dependenteAtual");
		super.doAfterCompose(tela);
		if (estaEditando())
			atualizaObjetoAtual(dependente);
		else
			novo();
	}

	protected boolean estaEditando() {
		return dependente.getId() != null;
	}
		
	@Override
	protected FuncionarioDependenteVO instanciaNovoObjeto() {
		return dependente;
	}	
}
