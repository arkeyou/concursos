
package br.gov.prodemge.prodigio.cursoprodigio.negocio;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.gov.prodigio.comuns.IProFacadeLocal;
import br.gov.prodigio.comuns.IProFacadeRemote;
import br.gov.prodigio.negocio.ProFacadeImp;


@Stateless(name = "cursoprodigio-ejb")
@TransactionManagement(TransactionManagementType.BEAN)
@Local(IProFacadeLocal.class)
@Remote(IProFacadeRemote.class)
public class CursoProdigioBaseFacadeImp extends ProFacadeImp implements IProFacadeLocal, IProFacadeRemote {
    
	
}
