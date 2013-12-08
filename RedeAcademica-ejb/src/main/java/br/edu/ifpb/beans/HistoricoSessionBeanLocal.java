package br.edu.ifpb.beans;

import br.edu.ifpb.entidades.Historico;
import java.util.List;

public interface HistoricoSessionBeanLocal {

    public void inserirHistorico(Historico historico);

    public void removerHsitorido(Historico hitorico);

    public List<Historico> listarHistorico();
}
