/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listener;

import br.edu.ifpb.entidades.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sales
 */
public class AutenticadorListener implements PhaseListener {

    @Override
    public void afterPhase(PhaseEvent event) {

        String paginaAtual = event.getFacesContext().getViewRoot().getViewId();
        String paginaRetorno = "../index.jsf";
        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser");

        if (!paginaAtual.equals("/index.xhtml") && !paginaAtual.equals("/cadastro.xhtml") && user == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(paginaRetorno);

            } catch (IOException ex) {
                Logger.getLogger(AutenticadorListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (user != null) {

            if (user.getTipo().equals(Usuario.Tipo.COLABORADOR)) {
                System.out.println(user+"entrou errado");
                if ((paginaAtual.equals("/diretor/indexAdmin.xhtml")
                        || paginaAtual.equals("/diretor/ListarHitorico.xhtml")
                        || paginaAtual.equals("/diretor/cadastrarUsuarios.xhtml")
                        || paginaAtual.equals("/coordenador/indexBibliotecario.xhtml"))) {
                    try {
                        paginaRetorno = "../colaborador/IndexUsuario.jsf";
                        FacesContext.getCurrentInstance().getExternalContext().redirect(paginaRetorno);

                    } catch (IOException ex) {
                        Logger.getLogger(AutenticadorListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            if (user.getTipo().equals(Usuario.Tipo.COORDENADOR)) {
                System.out.println(user+"entrou certo");
                if (paginaAtual.equals("/diretor/indexAdmin.xhtml")
                        || paginaAtual.equals("/diretor/ListarHitorico.xhtml")
                        || paginaAtual.equals("/diretor/cadastrarUsuarios.xhtml")) {

                    paginaRetorno = "../coordenador/indexBibliotecario.jsf";
                    try {
                        FacesContext.getCurrentInstance().getExternalContext().redirect(paginaRetorno);
                    } catch (IOException ex) {
                        Logger.getLogger(AutenticadorListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        System.out.println(user+"saida");

    }

    @Override
    public void beforePhase(PhaseEvent pe) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
