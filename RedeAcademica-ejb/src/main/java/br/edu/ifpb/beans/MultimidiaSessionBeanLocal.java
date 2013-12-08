package br.edu.ifpb.beans;

import br.edu.ifpb.entidades.Audio;
import br.edu.ifpb.entidades.Imagem;
import br.edu.ifpb.entidades.Multimidia;
import br.edu.ifpb.entidades.Texto;
import br.edu.ifpb.entidades.Video;
import java.util.List;

public interface MultimidiaSessionBeanLocal {

    public void inserirAudio(Audio audio);

    public void inserirImagem(Imagem imagem);

    public void inserirTexto(Texto texto);

    public void inserirVideo(Video video);

    public Texto buscarTexo(Texto id);

    public Imagem buscarImagem(Imagem id);

    public Video buscarVideo(Video id);

    public Audio buscarAudio(Audio id);

    public List<Texto> listarTexto();

    public List<Imagem> listarImagem();

    public List<Video> listarVideo();

    public List<Audio> listarAudio();

    public void removerTexo(Texto id);

    public void removerImagem(Imagem id);

    public void removerVideo(Video id);

    public void removerAudio(Audio id);

    public void atualizarTexo(Texto texto);

    public void atalizarImagem(Imagem imagem);

    public void atalizarVideo(Video video);

    public void atalizarAudio(Audio audio);

    public Texto buscaAvancadaTexo(String nome);

    public Imagem buscaAvancadaImagem(String nome);

    public Video buscaAvancadaVideo(String nome);

    public Audio buscaAvancadaAudio(String nome);

    public void removerMultimidia(Multimidia multimidia);

    public Multimidia buscarMultimidia(Multimidia multimidia);

    public List<Multimidia> listarMultimidia();

    public Multimidia localizarMultimidia(Integer id);

    public Multimidia buscaAvancadaMultimidia(String nome);
}
