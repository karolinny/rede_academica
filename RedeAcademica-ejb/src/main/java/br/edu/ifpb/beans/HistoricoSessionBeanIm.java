package br.edu.ifpb.beans;

import br.edu.ifpb.entidades.Historico;
import br.edu.ifpb.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Local(HistoricoSessionBeanLocal.class)
public class HistoricoSessionBeanIm implements HistoricoSessionBeanLocal {

    @PersistenceContext(unitName = "RedeAcademica")
    private EntityManager servicoHistorico;

    @Override
    public void inserirHistorico(Historico historico) {
        servicoHistorico.persist(historico);
    }

    @Override
    public void removerHsitorido(Historico hitorico) {
        servicoHistorico.remove(hitorico);
    }

    @Override
    public List<Historico> listarHistorico() {
        List<Historico> historicos = new ArrayList<Historico>();
        Query query = servicoHistorico.createNamedQuery("historico.recuperar");
        historicos = query.getResultList();
        return historicos;

    }
}
