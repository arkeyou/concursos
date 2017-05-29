package br.gov.prodemge.prodigio.cursoprodigio.controle.funcionario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
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
import br.gov.prodemge.prodigio.cursoprodigio.entidades.funcionario.FuncionarioDependenteVO;
import br.gov.prodemge.prodigio.cursoprodigio.entidades.funcionario.FuncionarioVO;
import br.gov.prodemge.prodigio.cursoprodigio.enums.funcionario.EstadoCivilEnum;
import br.gov.prodemge.prodigio.cursoprodigio.enums.funcionario.FuncionarioAnexoEnum;
import br.gov.prodigio.controle.componente.DetBox;
import br.gov.prodigio.controle.componente.InternalWindow;

public class FuncionarioCtr extends CursoProdigioBaseCtr<FuncionarioVO> {

	FuncionarioVO funcionarioEmDetalhes;
	
	@Override
	public void doAfterCompose(Component tela) throws Exception {
		super.doAfterCompose(tela);
		pesquisar();
	}
	
	public List<EstadoCivilEnum> getListaEstadoCivil() {
		EstadoCivilEnum[] values = EstadoCivilEnum.values();
		ArrayList<EstadoCivilEnum> arrayList = new ArrayList<EstadoCivilEnum>(Arrays.asList(values));
		return arrayList;
	}
	
	public void verCursos(Listcell listcell) {
		Listitem item = (Listitem) listcell.getParent();
		funcionarioEmDetalhes = item.getValue();

		InternalWindow internalWindow = (InternalWindow) getTela().getFellow("cursoDetalhe");
		//internalWindow.doPopup();
		//internalWindow.doEmbedded();
		internalWindow.doOverlapped();
		//internalWindow.doHighlighted();
		//internalWindow.doModal();

		Popup popupWindow = (Popup) getTela().getFellow("cursoDetalhePopup");
		popupWindow.open(listcell);

		getBinder().loadAll();
	}
	
	public FuncionarioVO getFuncionarioEmDetalhes() {
		return funcionarioEmDetalhes;
	}

	public void setFuncionarioEmDetalhes(FuncionarioVO funcionarioEmDetalhes) {
		this.funcionarioEmDetalhes = funcionarioEmDetalhes;
	}
	
	public void novoDependente() throws Exception {
		FuncionarioDependenteVO dependenteVO = new FuncionarioDependenteVO();
		dependenteVO.setFuncionario((FuncionarioVO)getObjetoAtual());
		abreDependente(dependenteVO);
	}
	
	public void editarDependente(Listitem listitem) throws Exception {
		FuncionarioDependenteVO dependenteVO = listitem.getValue();
		abreDependente(dependenteVO);
	}
	
	protected void abreDependente(FuncionarioDependenteVO dependenteVO) throws Exception {
		getTela().getDesktop().setAttribute("dependenteAtual", dependenteVO);
		getHelperView().abreCasoDeUsoSecundario("funcionario/funcionariodependente.zul", getTela(), new LinkedHashMap<String, Object>(), false, "modal", "50%");
		//A linha cima "trava" a tela pai até que o popup seja fechado
		//Recupera o objeto do repositorio 
		this.objetoAtual = repositorio().recuperaObjeto(objetoAtual);
		//Atualiza apenas o div da tela
		getBinder().loadComponent(getTela().getFellow("div_dependente"));
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
	
	//Esconde o botão novo do detalhe pois já existe um botão com essa finalidade
	@Override
	public boolean detalheSomenteLeitura(DetBox detalhe) {
		if (detalhe.getId().equals("dependentesDet"))
			return true;
		return super.detalheSomenteLeitura(detalhe);
	}

	public void recuperaLogradouroPorCep(Textbox textbox) throws Exception {
		String value = textbox.getValue();
		LogradouroVO exemplo = new LogradouroVO();
		exemplo.setCep(value);

		LogradouroVO logradouro = repositorio().recuperaPorChaveNatural(exemplo, "cep");

		getObjetoAtual().getFuncionarioEndereco().setLogradouro(logradouro);
		Component parteDaTelaQueContemOsCamposDeEndereco = getWindowAtual().getFellow("endereco");
		getBinder().loadComponent(parteDaTelaQueContemOsCamposDeEndereco);
	}
	
	@Override
	protected void inicializaNovoDetalhe(Object detalheNovo, DetBox detBox) {
		if (detalheNovo instanceof FuncionarioAnexoVO) {
			FuncionarioAnexoVO funcionarioAnexoVO = (FuncionarioAnexoVO) detalheNovo;
			if (detBox.getId().equals("anexosMemorandoDet")) {
				funcionarioAnexoVO.setAnexoTipo(FuncionarioAnexoEnum.MEMORANDO);
			}
			if (detBox.getId().equals("anexosOficioDet")) {
				funcionarioAnexoVO.setAnexoTipo(FuncionarioAnexoEnum.OFICIO);
			}
		}
	}
	
}
