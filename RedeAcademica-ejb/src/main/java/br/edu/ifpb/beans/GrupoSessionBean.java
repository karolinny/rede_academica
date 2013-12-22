package br.edu.ifpb.beans;

import br.edu.ifpb.entidades.Grupo;
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
    
    @Override
    public List<Grupo> recuperarGrupoDiretor(int id) {
        List<Grupo> gruposdiretor = new ArrayList<Grupo>();
        try {
            Query query = grupoSession.createNamedQuery("grupo.recuperapordiretor");
            query.setParameter("diretor", id);
            gruposdiretor = query.getResultList();
        } catch (Exception e) {
        }
        return gruposdiretor;
    }
    
    @Override
     public Grupo recuperarGrupoId(Long id) {
            return grupoSession.find(Grupo.class, id);
    }
    
    public void adicionarParticipantes(Long id){
       
    }
}
