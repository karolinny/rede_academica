/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao.Mbeans;

import br.edu.ifpb.beans.MultimidiaSessionBeanLocal;
import br.edu.ifpb.entidades.Audio;
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
public class CadastrarAudioManagerBean implements Serializable {

    @Inject
    private Conversation cons;
    @EJB
    private MultimidiaSessionBeanLocal AudioManager;
    
    private Audio audio = new Audio();
    @Inject
    private UserBean user;       
    

    public String inicializar() {
        this.cons.begin();
        user.gerarHistoricoDeAcesso("usuario colaborador iniciou o cadastro de audio");
        return "cadastroAudio";
    }

    public String finalizar() {
        this.cons.end();
        user.gerarHistoricoDeAcesso("usuario colaborador finalizou o cadastro de audio");
        return "indexUsuario";
    }

    public String efetuarCadastroAudio() {
        if (user == null) {
        audio.setUpLoader("Uploader Desconhecido");
        user.gerarHistoricoDeAcesso("usuario colaborador cadastrando metadados do arquivo de audio");
        } else {
            audio.setUpLoader(user.usuarioDaSessao().getNome());            
            user.gerarHistoricoDeAcesso("usuario colaborador cadastrando metadados do arquivo de audio");
        }
        return "audioUpload";
    }   

    public void processFileUpload(FileUploadEvent event) throws FileNotFoundException, IOException {
        UploadedFile arq = event.getFile();
        File file = new File("C://arquivos//" + arq.getFileName());
        audio.setUrl(file.getAbsolutePath());
        audio.setQtdeDownloads(0);
        audio.setQtdeVisualizacao(0);
        AudioManager.inserirAudio(audio);
        user.gerarHistoricoDeAcesso("fazendo upload de um arquivo de Audio");
        byte[] conteudo = event.getFile().getContents();  
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(conteudo);
        fos.close();
        FacesMessage msg = new FacesMessage("O Arquivo ", file.getName() + " foi salvo.");
        FacesContext.getCurrentInstance().addMessage("msgUpdate", msg);
    }
    
    public List<Audio> getListaAudio(){
        user.gerarHistoricoDeAcesso("usuario colaborador listou todas as midias de audio registradas");
        return AudioManager.listarAudio();
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }
}
