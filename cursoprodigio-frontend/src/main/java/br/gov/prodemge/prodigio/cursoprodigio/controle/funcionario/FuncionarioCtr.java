package br.gov.prodemge.prodigio.cursoprodigio.controle.funcionario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Textbox;

import br.gov.prodemge.prodigio.cursoprodigio.controle.CursoProdigioBaseCtr;
import br.gov.prodemge.prodigio.cursoprodigio.entidades.cidade.LogradouroVO;
import br.gov.prodemge.prodigio.cursoprodigio.entidades.funcionario.FuncionarioAnexoVO;
import br.gov.prodemge.prodigio.cursoprodigio.entidades.funcionario.FuncionarioEnderecoVO;
import br.gov.prodemge.prodigio.cursoprodigio.entidades.funcionario.FuncionarioVO;
import br.gov.prodemge.prodigio.cursoprodigio.enums.EstadoCivilEnum;
import br.gov.prodemge.prodigio.cursoprodigio.enums.funcionario.FuncionarioAnexoEnum;
import br.gov.prodigio.controle.componente.DetBox;
import br.gov.prodigio.controle.componente.InternalWindow;

public class FuncionarioCtr extends CursoProdigioBaseCtr<FuncionarioVO> {

	private Object funcionarioEmDetalhes;

	@Override
	public void doAfterCompose(Component tela) throws Exception {
		super.doAfterCompose(tela);
		pesquisar();
	}

	public List<EstadoCivilEnum> getEstadoCivil() {
		EstadoCivilEnum[] values = EstadoCivilEnum.values();
		ArrayList<EstadoCivilEnum> arrayList = new ArrayList<EstadoCivilEnum>(Arrays.asList(values));
		return arrayList;
	}
	
	public void verCursos(Listcell listcell){
		Listitem item = (Listitem) listcell.getParent();
		funcionarioEmDetalhes = item.getValue();

		InternalWindow internalWindow = (InternalWindow) getTela().getFellow("cursoDetalhe");
		internalWindow.doPopup();
		// internalWindow.doEmbedded();
		// internalWindow.doOverlapped();
		// internalWindow.doHighlighted();
		// internalWindow.doModal();

		Popup popupWindow = (Popup) getTela().getFellow("cursoDetalhePopup");
		popupWindow.open(listcell);
		
		getBinder().loadAll();
	}
	
	public Object getFuncionarioEmDetalhes() {
		return funcionarioEmDetalhes;
	}

	public void setFuncionarioEmDetalhes(Object funcionarioEmDetalhes) {
		this.funcionarioEmDetalhes = funcionarioEmDetalhes;
	}
	
	@Override
	protected void configuraValoresPadroesParaObjeto() {
		FuncionarioVO funcionarioVO = getObjetoAtual();
		FuncionarioEnderecoVO funcionarioEndereco = funcionarioVO.getFuncionarioEndereco();
		if (funcionarioEndereco == null) {
			funcionarioEndereco = new FuncionarioEnderecoVO();
		}
		funcionarioEndereco.setFuncionario(funcionarioVO);
		funcionarioVO.setFuncionarioEndereco(funcionarioEndereco);
	}
	
	public void recuperaLogradouroPorCep(Textbox textbox) throws Exception{
		String value = textbox.getValue();
		LogradouroVO ex = new LogradouroVO();
		ex.setCep(value);
		
		LogradouroVO logradouro = repositorio().recuperaPorChaveNatural(ex, "cep");
		getObjetoAtual().getFuncionarioEndereco().setLogradouro(logradouro);
		Component tabPanel = getWindowAtual().getFellow("endereco");
		getBinder().loadComponent(tabPanel);
			
	}
	
	@Override
	protected void inicializaNovoDetalhe(Object detalheNovo, DetBox detBox) {
		if (detalheNovo instanceof FuncionarioAnexoVO) {
			FuncionarioAnexoVO funcionarioAnexoVO = (FuncionarioAnexoVO) detalheNovo;
			if (detBox.getId().equals("anexosDet")) {
				funcionarioAnexoVO.setAnexoTipo(FuncionarioAnexoEnum.MEMORANDO);
			}
			if (detBox.getId().equals("anexosDet")) {
				funcionarioAnexoVO.setAnexoTipo(FuncionarioAnexoEnum.OFICIO);
			}
		}
	}
}
