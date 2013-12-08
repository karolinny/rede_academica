package br.edu.ifpb.beans;

import br.edu.ifpb.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Local(UserSessionBeanLocal.class)
public class UserSessionBean implements UserSessionBeanLocal {

    @PersistenceContext(unitName = "RedeAcademica")
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        Query query = entityManager.createQuery("SELECT u from usuario u ");
        List<Usuario> usuarios = new ArrayList<Usuario>();
        usuarios = query.getResultList();

        return usuarios;
    }

    @Override
    public Usuario buscarUsuario(Usuario id) {
        return entityManager.find(Usuario.class, id);
    }

    @Override
    public void inserirUsuario(Usuario usuario) {
        entityManager.persist(usuario);
    }

    @Override
    public void removerUsuario(Usuario usuario) {
        entityManager.remove(usuario);
    }

    @Override
    public void atualizarUsuario(Usuario usuario) {
        entityManager.find(Usuario.class, usuario);
        entityManager.merge(this);
    }

    @Override
    public Usuario receperarUsuarioLogin(String login) {
        Usuario usuario = new Usuario();
        try {
            Query query = entityManager.createNamedQuery("usuario.recuperaPeloLogin");
            query.setParameter("login", login);
            usuario = (Usuario) query.getSingleResult();
        } catch (Exception e) {
        }
        return usuario;
    }

    @Override
    public Usuario recuperarAdministradorLogin(String login, String senha) {
        Usuario usuario = new Usuario();
        try {
            Query query = entityManager.createNamedQuery("usuario.recuperar");
            query.setParameter("login", login);
            query.setParameter("senha", senha);
            usuario = (Usuario) query.getSingleResult();
        } catch (Exception e) {
        }
        return usuario;
    }
}
