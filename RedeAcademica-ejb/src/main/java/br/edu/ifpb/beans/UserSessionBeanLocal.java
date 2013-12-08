package br.edu.ifpb.beans;

import br.edu.ifpb.entidades.Usuario;

import java.util.List;

public interface UserSessionBeanLocal {

    public List<Usuario> listarUsuarios();

    public Usuario buscarUsuario(Usuario id);

    public void inserirUsuario(Usuario usuario);

    public void removerUsuario(Usuario usuario);

    public void atualizarUsuario(Usuario usuario);

    public Usuario receperarUsuarioLogin(String login);

    public Usuario recuperarAdministradorLogin(String login, String senha);
}
