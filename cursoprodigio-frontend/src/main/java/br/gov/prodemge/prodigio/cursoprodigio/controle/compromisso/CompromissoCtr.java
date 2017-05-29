package br.gov.prodemge.prodigio.cursoprodigio.controle.compromisso;

import org.zkoss.zk.ui.Component;

import br.gov.prodemge.prodigio.cursoprodigio.controle.CursoProdigioBaseCtr;
import br.gov.prodemge.prodigio.cursoprodigio.entidades.compromisso.CompromissoVO;

public class CompromissoCtr extends CursoProdigioBaseCtr<CompromissoVO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5796538670587020498L;
	
	@Override
	public void doAfterCompose(Component tela) throws Exception{
		super.doAfterCompose(tela);
		pesquisar();
	}
	 

}
