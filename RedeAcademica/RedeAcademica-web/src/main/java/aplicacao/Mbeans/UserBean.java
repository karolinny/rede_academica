/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao.Mbeans;

import br.edu.ifpb.beans.HistoricoSessionBeanLocal;
import br.edu.ifpb.beans.UserSessionBeanLocal;
import br.edu.ifpb.entidades.Historico;

import br.edu.ifpb.entidades.Usuario;


import java.io.IOException;
import java.io.Serializable;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author sales
 */
@ManagedBean(name = "UserBean")
@SessionScoped
public class UserBean implements Serializable {

    private Usuario usuario = new Usuario();
    private Usuario usuarioColaborador = new Usuario();
    private Usuario usuarioCadastro = new Usuario();
    @EJB
    private UserSessionBeanLocal userSessionB;
    @EJB
    private HistoricoSessionBeanLocal historicoUsusario;

    public UserBean() {
    }

    public UserSessionBeanLocal getUserSessionB() {
        return userSessionB;
    }

    public void setUserSessionB(UserSessionBeanLocal userSessionB) {
        this.userSessionB = userSessionB;
    }

    public HistoricoSessionBeanLocal getHistoricoUsusario() {
        return historicoUsusario;
    }

    public void setHistoricoUsusario(HistoricoSessionBeanLocal historicoUsusario) {
        this.historicoUsusario = historicoUsusario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioColaborador() {
        return usuarioColaborador;
    }

    public void setUsuarioColaborador(Usuario usuarioColaborador) {
        this.usuarioColaborador = usuarioColaborador;
    }

    public Usuario getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(Usuario usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    public String autenticarUsuario() throws IOException {
        String paginaRetorno = "index.jsf";

        Usuario user = userSessionB.recuperarAdministradorLogin(usuario.getLogin(), usuario.getSenha());
        if (user.getLogin() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro de autenticação", "Login ou senha inválidos"));
            FacesContext.getCurrentInstance().getExternalContext().redirect(paginaRetorno);
        } else {
            if (usuario.getSenha().equals(user.getSenha())) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentUser", user);

                if (user.getTipo().equals(Usuario.Tipo.COLABORADOR)) {
                    System.out.println("mudulo colaborador " + user);
                    gerarHistoricoDeAcesso("usuario colaborador logou no sistema");
                    paginaRetorno = "colaborador/indexUsuario.jsf";
                    FacesContext.getCurrentInstance().getExternalContext().redirect(paginaRetorno);

                } else if (user.getTipo().equals(Usuario.Tipo.COORDENADOR)) {
                    System.out.println("mudulo coordenador " + user);
                    gerarHistoricoDeAcesso("usuario coordenador logou no sistema");
                    paginaRetorno = "coordenador/indexCoordenador.jsf";
                    FacesContext.getCurrentInstance().getExternalContext().redirect(paginaRetorno);

                } else {
                    System.out.println("mudulo diretor " + user);
                    gerarHistoricoDeAcesso("diretor logou no sistema");
                    paginaRetorno = "diretor/indexDiretor.jsf";
                    FacesContext.getCurrentInstance().getExternalContext().redirect(paginaRetorno);
                }

            }

        }
        return paginaRetorno;
    }

    public String inserirUsuario() throws IOException {
        String paginaRetorno = "indexDiretor.jsf";

        userSessionB.inserirUsuario(usuarioCadastro);
        gerarHistoricoDeAcesso("inserindo um usuario no sistema" + " nome: " + usuarioCadastro.getNome());
        usuarioCadastro = new Usuario();
        FacesContext.getCurrentInstance().getExternalContext().redirect(paginaRetorno);
        return paginaRetorno;
    }

    public String inserirUsuarioColaborador() throws IOException {
        String paginaRetorno = "index.jsf";
        usuarioColaborador.setTipo(Usuario.Tipo.COLABORADOR);
        userSessionB.inserirUsuario(usuarioColaborador);
        usuarioColaborador = new Usuario();
        FacesContext.getCurrentInstance().getExternalContext().redirect(paginaRetorno);
        return paginaRetorno;

    }

    public Usuario usuarioDaSessao() {

        Usuario usuarioDaSessao = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser");

        return usuarioDaSessao;
    }

    public void gerarHistoricoDeAcesso(String acao) {
        Calendar c = Calendar.getInstance();

        Historico historico = new Historico();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ip = request.getRemoteAddr();
        historico.setLogin(usuarioDaSessao().getLogin());
        historico.setDataHistorivo(new Timestamp(c.getTimeInMillis()));
        historico.setIp(ip);
        historico.setAcao(acao);
        historicoUsusario.inserirHistorico(historico);

    }

    public void encerrarSessao() throws IOException {
        gerarHistoricoDeAcesso("usuario saiu do sistema");
        usuario = new Usuario();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().redirect("../index.jsf");
    }
}
