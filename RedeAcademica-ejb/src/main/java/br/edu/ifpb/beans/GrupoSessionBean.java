package br.edu.ifpb.beans;

import br.edu.ifpb.entidades.Grupo;
import br.edu.ifpb.entidades.Historico;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class GrupoSessionBean implements GrupoSessionBeanLocal {

    @PersistenceContext(unitName = "RedeAcademica")
    private EntityManager grupoSession;

    @Override
    public void inserirGrupo(Grupo grupo) {
        grupoSession.persist(grupo);
    }

    @Override
    public void removerGrupo(Grupo grupo) {
        grupoSession.remove(grupo);
    }

    @Override
    public List<Grupo> listarGrupo() {
        List<Grupo> grupos = new ArrayList<Grupo>();
        Query query = grupoSession.createNamedQuery("grupo.recuperar");
        grupos = query.getResultList();
        return grupos;
    }
}