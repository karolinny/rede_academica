package br.edu.ifpb.entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries({
    @NamedQuery(name = "listarMultimidias", query = "SELECT m from Multimidia m "),
    @NamedQuery(name = "buscarMultimidiasNome", query = "SELECT m from Multimidia m where m.nome = :parametroNome"),
    @NamedQuery(name = "buscarMultimidiasId", query = "SELECT m from Multimidia m where m.id = :parametroId"),
    @NamedQuery(name = "listarTexto", query = "SELECT t from Texto t"),
    @NamedQuery(name = "listarAudio", query = "SELECT a from Audio a"),
    @NamedQuery(name = "listarVideo", query = "SELECT v from Video v"),
    @NamedQuery(name = "listarImagem", query = "SELECT i from Imagem i"),
    @NamedQuery(name = "busacaAvancadaTexto", query = "SELECT m from Multimidia m where m.nome like :parametroNome"),
    @NamedQuery(name = "busacaAvancadaAudio", query = "SELECT m from Multimidia m where m.nome like :parametroNome"),
    @NamedQuery(name = "busacaAvancadaVideo", query = "SELECT m from Multimidia m where m.nome like :parametroNome"),
    @NamedQuery(name = "busacaAvancadaImagem", query = "SELECT m from Multimidia m where m.nome like :parametroNome"),})
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Multimidia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String upLoader;
    @Column(unique = true, nullable = false, length = 20)
    private String nome;
    private String extensao;
    @Column(name = "Quantidade_visualizacoes")
    private Integer qtdeVisualizacao;
    @Column(name = "Quantidade_downloads")
    private Integer qtdeDownloads;
    private String url;

    public Multimidia() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExtensao() {
        return extensao;
    }

    public void setExtensao(String extensao) {
        this.extensao = extensao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQtdeVisualizacao() {
        return qtdeVisualizacao;
    }

    public void setQtdeVisualizacao(Integer qtdeVisualizacao) {
        this.qtdeVisualizacao = qtdeVisualizacao;
    }

    public Integer getQtdeDownloads() {
        return qtdeDownloads;
    }

    public void setQtdeDownloads(Integer qtdeDownloads) {
        this.qtdeDownloads = qtdeDownloads;
    }

    public String getUpLoader() {
        return upLoader;
    }

    public void setUpLoader(String upLoader) {
        this.upLoader = upLoader;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
