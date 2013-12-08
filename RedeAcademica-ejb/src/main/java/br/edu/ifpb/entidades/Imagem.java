package br.edu.ifpb.entidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Im")
public class Imagem extends Multimidia {

    private String Resolucao;

    public Imagem() {
    }

    public String getResolucao() {
        return Resolucao;
    }

    public void setResolucao(String Resolucao) {
        this.Resolucao = Resolucao;
    }
}
