package br.edu.ifpb.beans;

import br.edu.ifpb.entidades.Grupo;
import br.edu.ifpb.entidades.Historico;
import java.util.List;
import javax.ejb.Local;

@Local
public interface GrupoSessionBeanLocal {

    public void inserirGrupo(Grupo grupo);

    public void removerGrupo(Grupo grupo);

    public List<Grupo> listarGrupo();
     
    public List<Grupo> recuperarGrupoDiretor(int id);
    
     public Grupo recuperarGrupoId(Long id);
}
