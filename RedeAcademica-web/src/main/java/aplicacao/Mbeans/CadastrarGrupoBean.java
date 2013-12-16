
package aplicacao.Mbeans;

import br.edu.ifpb.beans.GrupoSessionBeanLocal;
import br.edu.ifpb.beans.HistoricoSessionBeanLocal;
import br.edu.ifpb.entidades.Grupo;
import br.edu.ifpb.entidades.Historico;
import br.edu.ifpb.entidades.Usuario;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "GrupoNovo")
@SessionScoped
public class CadastrarGrupoBean {
    
    private Grupo grupo;
    @EJB
    private GrupoSessionBeanLocal grupoSession;
     @EJB
    private HistoricoSessionBeanLocal historicoUsusario;
    
    public CadastrarGrupoBean(){
        grupo = new Grupo();
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public GrupoSessionBeanLocal getGrupoSession() {
        return grupoSession;
    }

    public void setGrupoSession(GrupoSessionBeanLocal grupoSession) {
        this.grupoSession = grupoSession;
    }

    public HistoricoSessionBeanLocal getHistoricoUsusario() {
        return historicoUsusario;
    }

    public void setHistoricoUsusario(HistoricoSessionBeanLocal historicoUsusario) {
        this.historicoUsusario = historicoUsusario;
    }
    
    public String cadastrarGrupo()throws IOException{
        String paginaRetorno = "cadastrarGrupos.jsf";
        grupoSession.inserirGrupo(grupo);
        addMessage("Grupo Criado com Sucesso !!");
        gerarHistoricoDeAcesso("Inserindo um novo grupo ao sistema: nome: " + grupo.getNome());
        grupo = new Grupo();
        FacesContext.getCurrentInstance().getExternalContext().redirect(paginaRetorno);
        return paginaRetorno;
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
     
     public List<Grupo> listarGrupos() {
        return grupoSession.listarGrupo();
    }
     
     public Usuario usuarioDaSessao() {

        Usuario usuarioDaSessao = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser");

        return usuarioDaSessao;
    }
     
      public void addMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    } 
}
