/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao.Mbeans;

import br.edu.ifpb.beans.HistoricoSessionBeanLocal;
import br.edu.ifpb.entidades.Historico;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author sales
 */
@Named(value = "historicoMb")
@Dependent
public class HistoricoMb {

    @EJB
    private HistoricoSessionBeanLocal servicoHistorio;
    private Historico historico = new Historico();
    
    /**
     * Creates a new instance of HistoricoMb
     */
    public HistoricoMb() {
    }

    public HistoricoSessionBeanLocal getServicoHistorio() {
        return servicoHistorio;
    }

    public void setServicoHistorio(HistoricoSessionBeanLocal servicoHistorio) {
        this.servicoHistorio = servicoHistorio;
    }

    public Historico getHistorico() {
        return historico;
    }

    public void setHistorico(Historico historico) {
        this.historico = historico;
    }

    public List<Historico> listarHistorico() {
        return servicoHistorio.listarHistorico();
    }

    public void removerHistorico(Historico historico) {
        servicoHistorio.removerHsitorido(historico);
    }
}
