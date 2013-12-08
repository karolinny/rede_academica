/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao.Mbeans;

import br.edu.ifpb.beans.MultimidiaSessionBeanLocal;
import br.edu.ifpb.entidades.Imagem;
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
public class CadastrarImagemManagerBean implements Serializable {
    @Inject
    private Conversation cons;
    @EJB
    private MultimidiaSessionBeanLocal ImagemManager;
    private Imagem imagem = new Imagem();
    @Inject
    private UserBean user;       

        
     public String inicializar() {
        this.cons.begin();
        user.gerarHistoricoDeAcesso("usuario colaborador iniciou o cadastro de imagem");
        return "cadastroImagem";
    }
     
    public String finalizar() {
        this.cons.end();
        user.gerarHistoricoDeAcesso("usuario colaborador finalizou o cadastro de imagem");
        return "indexUsuario";
    }
    
    public String efetuarCadastroImagem() {
        if (user == null) {
        imagem.setUpLoader("Uploader Desconhecido");
        user.gerarHistoricoDeAcesso("usuario colaborador cadastrando metadados do arquivo de imagem");
        } else {
            user.gerarHistoricoDeAcesso("usuario colaborador cadastrando metadados do arquivo de imagem");
            imagem.setUpLoader(user.usuarioDaSessao().getNome());
        }
        return "imagemUpload";
    }    
    
    public void processFileUpload(FileUploadEvent event) throws FileNotFoundException, IOException {
        UploadedFile arq = event.getFile();
        File file = new File("C://arquivos//" + arq.getFileName());
        imagem.setUrl(file.getAbsolutePath());
        imagem.setQtdeDownloads(0);
        imagem.setQtdeVisualizacao(0);
        ImagemManager.inserirImagem(imagem);
        user.gerarHistoricoDeAcesso("fazendo upload de um arquivo de imagem");
        byte[] conteudo = event.getFile().getContents();
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(conteudo);
        fos.close();
        FacesMessage msg = new FacesMessage("O Arquivo ", file.getName() + " foi salvo.");
        FacesContext.getCurrentInstance().addMessage("msgUpdate", msg);
    }
    
    public List<Imagem> getListarImagem(){
        user.gerarHistoricoDeAcesso("usuario colaborador listou todas as midias de imagem registradas");
        return ImagemManager.listarImagem();
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }    
}
