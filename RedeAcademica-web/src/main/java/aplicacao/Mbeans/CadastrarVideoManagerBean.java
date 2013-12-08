/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao.Mbeans;

import br.edu.ifpb.beans.MultimidiaSessionBeanLocal;
import br.edu.ifpb.entidades.Video;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author Vagner
 */
@Named
@ConversationScoped
public class CadastrarVideoManagerBean implements Serializable {
    @Inject
    private Conversation cons;
    @EJB
    private MultimidiaSessionBeanLocal VideoManager;
    private Video video = new Video();
    @Inject
    UserBean user;
        
     public String inicializar() {
        this.cons.begin();
        user.gerarHistoricoDeAcesso("usuario colaborador iniciou o cadastro de video");
        return "cadastroVideo";
    }
     
    public String finalizar() {
        this.cons.end();
        user.gerarHistoricoDeAcesso("usuario colaborador finalizou o cadastro de video");
        return "indexUsuario";
    }
    
    public String efetuarCadastroVideo() {
        if (user == null) {
        video.setUpLoader("Uploader Desconhecido");
        user.gerarHistoricoDeAcesso("usuariocolaborador cadastrando metadados do arquivo de video");
        } else {
            video.setUpLoader(user.usuarioDaSessao().getNome());
            user.gerarHistoricoDeAcesso("usuario colaborador cadastrando metadados do arquivo de video");
        }
        return "videoUpload";
    }    
    
    public void processFileUpload(FileUploadEvent event) throws FileNotFoundException, IOException {
        UploadedFile arq = event.getFile();
        File file = new File("C://arquivos//" + arq.getFileName());
        video.setUrl(file.getAbsolutePath());
        video.setQtdeDownloads(0);
        video.setQtdeVisualizacao(0);
        VideoManager.inserirVideo(video);
        user.gerarHistoricoDeAcesso("fazendo upload de um arquivo de video");
        byte[] conteudo = event.getFile().getContents();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(conteudo);
        fos.close();
        FacesMessage msg = new FacesMessage("O Arquivo ", file.getName() + " foi salvo.");
        FacesContext.getCurrentInstance().addMessage("msgUpdate", msg);
    }
    
        public List<Video> getListaVideo(){
        user.gerarHistoricoDeAcesso("usuario colaborador listou todas as midias de video registradas");
        return VideoManager.listarVideo();
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }   
}
