
package aplicacao.Mbeans;

import br.edu.ifpb.beans.GrupoSessionBeanLocal;
import br.edu.ifpb.beans.HistoricoSessionBeanLocal;
import br.edu.ifpb.entidades.Grupo;
import br.edu.ifpb.entidades.Historico;
import br.edu.ifpb.entidades.Usuario;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sucesso", "Grupo Criado com Sucesso !!"));
        String paginaRetorno = "cadastrarGrupos.jsf";
        grupo.setId_criador(usuarioDaSessao().getId());
        grupoSession.inserirGrupo(grupo);
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
     
      public List<Grupo> listarGruposPorID() {
        return grupoSession.recuperarGrupoDiretor(usuarioDaSessao().getId());
    }
     
     public Usuario usuarioDaSessao() {

        Usuario usuarioDaSessao = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentUser");

        return usuarioDaSessao;
    }
     
     public void getgrupoEditar(Long id){
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentGrupo", grupoSession.recuperarGrupoId(id));
         Grupo grupoDaSessao = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentGrupo");
         System.out.println(grupoDaSessao.getId());
     }
     
     public void addParticipante(Usuario user){
         Grupo grupoDaSessao = (Grupo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currentGrupo");
         List<Usuario> users = new ArrayList<Usuario>();
         users.add(user);
         grupoDaSessao.setUsuario(users);
     }
     
      public void addMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    } 
}
