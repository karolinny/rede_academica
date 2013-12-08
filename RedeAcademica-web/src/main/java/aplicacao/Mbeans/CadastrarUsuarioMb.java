/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao.Mbeans;

import br.edu.ifpb.beans.UserSessionBeanLocal;

import br.edu.ifpb.entidades.Usuario;

import java.io.Serializable;
import javassist.expr.Cast;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author sales
 */
@Named(value = "cadastrarUsuarioMb")
@ViewScoped

public class CadastrarUsuarioMb {

    @EJB
    private UserSessionBeanLocal serviceCadastrar;
    private Usuario usuarioLogado = new Usuario();
    private Usuario diretorUsuario = new Usuario();
    private Usuario coordenadorUsuario = new Usuario();
    private Usuario colaboradorUsuario = new Usuario();
    private String login;
    private String senha;

    /**
     * Creates a new instance of LoginBean
     */
    public CadastrarUsuarioMb() {
    }

    public UserSessionBeanLocal getServiceCadastrar() {
        return serviceCadastrar;
    }

    public void setServiceCadastrar(UserSessionBeanLocal serviceCadastrar) {
        this.serviceCadastrar = serviceCadastrar;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
    }

    public Usuario getDiretorUsuario() {
        return diretorUsuario;
    }

    public void setDiretorUsuario(Usuario diretorUsuario) {
        this.diretorUsuario = diretorUsuario;
    }

    public Usuario getCoordenadorUsuario() {
        return coordenadorUsuario;
    }

    public void setCoordenadorUsuario(Usuario coordenadorUsuario) {
        this.coordenadorUsuario = coordenadorUsuario;
    }

    public Usuario getColaboradorUsuario() {
        return colaboradorUsuario;
    }

    public void setColaboradorUsuario(Usuario colaboradorUsuario) {
        this.colaboradorUsuario = colaboradorUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String logarUsuario() {

        return "diretor/cadastrarUsuario.jsf";
    }

    public String logar() {
    String paginaAtual = null;
       
        System.out.println(login);
        System.out.println(senha);
    Usuario user = serviceCadastrar.receperarUsuarioLogin(login);
        System.out.println(user);
    if(user.getLogin() == null){
       paginaAtual = "/index.xhtml";
   }else{
       System.out.println("logado!!!");
   paginaAtual = "diretor/indexAdmin.xhtml";
   }
   return paginaAtual;
    }
}
