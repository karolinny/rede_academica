/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao.Mbeans;

import br.edu.ifpb.beans.MultimidiaSessionBeanLocal;
import br.edu.ifpb.entidades.Texto;
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
public class CadastrarTextoManagerBean implements Serializable{
    @Inject
    private Conversation cons;
    @EJB
    private MultimidiaSessionBeanLocal TextoManager;
    private Texto texto = new Texto();
    @Inject
    private UserBean user;
        
     public String inicializar() {
        this.cons.begin();
        user.gerarHistoricoDeAcesso("usuario colaborador iniciou o cadastro de texto");
        return "cadastroTexto";
    }
     
    public String finalizar() {
        this.cons.end();
        user.gerarHistoricoDeAcesso("usuario colaborador finalizou o cadastro de texto");
        return "indexUsuario";
    }
    
    public String efetuarCadastroTexto() {
        if (user == null) {
        texto.setUpLoader("Uploader Desconhecido");
        user.gerarHistoricoDeAcesso("usuario colaborador cadastrando metadados do arquivo de texto");
        } else {
            texto.setUpLoader(user.usuarioDaSessao().getNome());
            user.gerarHistoricoDeAcesso("usuario colaborador cadastrando metadados do arquivo de texto");
        }
        return "textoUpload";
    }    
    
    public void processFileUpload(FileUploadEvent event) throws FileNotFoundException, IOException {
        UploadedFile arq = event.getFile();
        File file = new File("C://arquivos//" + arq.getFileName());
        texto.setUrl(file.getAbsolutePath());
        texto.setQtdeDownloads(0);
        texto.setQtdeVisualizacao(0);
        TextoManager.inserirTexto(texto);
        user.gerarHistoricoDeAcesso("fazendo upload de um arquivo de texto");
        byte[] conteudo = event.getFile().getContents();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(conteudo);
        fos.close();
        FacesMessage msg = new FacesMessage("O Arquivo ", file.getName() + " foi salvo.");
        FacesContext.getCurrentInstance().addMessage("msgUpdate", msg);
    }

        public List<Texto> getListaTexto(){
        user.gerarHistoricoDeAcesso("usuario colaborador listou todas as midias de texto registradas");
        return TextoManager.listarTexto();
    }
    
    public Texto getTexto() {
        return texto;
    }

    public void setTexto(Texto texto) {
        this.texto = texto;
    }    
}