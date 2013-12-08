package br.edu.ifpb.beans;

import br.edu.ifpb.entidades.Audio;
import br.edu.ifpb.entidades.Imagem;
import br.edu.ifpb.entidades.Multimidia;
import br.edu.ifpb.entidades.Texto;
import br.edu.ifpb.entidades.Video;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Local(MultimidiaSessionBeanLocal.class)
public class MultimidiaSessionBean implements MultimidiaSessionBeanLocal {

    @PersistenceContext(unitName = "RedeAcademica")
    private EntityManager entityManagerMult;

    public EntityManager getEntityManagerMult() {
        return entityManagerMult;
    }

    public void inserirMultimidia(Multimidia multimidia) {
        entityManagerMult.persist(multimidia);
    }

    @Override
    public void inserirAudio(Audio audio) {
        entityManagerMult.persist(audio);
    }

    @Override
    public void inserirImagem(Imagem imagem) {
        entityManagerMult.persist(imagem);
    }

    @Override
    public void inserirTexto(Texto texto) {
        entityManagerMult.persist(texto);
    }

    @Override
    public void inserirVideo(Video video) {
        entityManagerMult.persist(video);
    }

    @Override
    public Texto buscarTexo(Texto id) {
        return entityManagerMult.find(Texto.class, id);
    }

    @Override
    public Imagem buscarImagem(Imagem id) {
        return entityManagerMult.find(Imagem.class, id);
    }

    @Override
    public Video buscarVideo(Video id) {
        return entityManagerMult.find(Video.class, id);
    }

    @Override
    public Audio buscarAudio(Audio id) {
        return entityManagerMult.find(Audio.class, id);
    }

    @Override
    public List<Texto> listarTexto() {
        Query query = entityManagerMult.createNamedQuery("listarTexto");
        List<Texto> textos = query.getResultList();
        return textos;
    }

    @Override
    public List<Imagem> listarImagem() {
        Query query = entityManagerMult.createNamedQuery("listarImagem");
        List<Imagem> imagens = query.getResultList();
        return imagens;
    }

    @Override
    public List<Video> listarVideo() {
        Query query = entityManagerMult.createNamedQuery("listarVideo");
        List<Video> videos = query.getResultList();
        return videos;
    }

    @Override
    public List<Audio> listarAudio() {
        Query query = entityManagerMult.createNamedQuery("listarAudio");
        List<Audio> audios = query.getResultList();
        return audios;
    }

    @Override
    public void removerTexo(Texto id) {
        entityManagerMult.remove(id);
    }

    @Override
    public void removerImagem(Imagem id) {
        entityManagerMult.remove(id);
    }

    @Override
    public void removerVideo(Video id) {
        entityManagerMult.remove(id);
    }

    @Override
    public void removerAudio(Audio id) {
        entityManagerMult.remove(id);
    }

    @Override
    public void atualizarTexo(Texto texto) {
        entityManagerMult.find(Texto.class, texto);
        entityManagerMult.merge(texto);
    }

    @Override
    public void atalizarImagem(Imagem imagem) {
        entityManagerMult.find(Imagem.class, imagem);
        entityManagerMult.merge(imagem);
    }

    @Override
    public void atalizarVideo(Video video) {
        entityManagerMult.find(Video.class, video);
        entityManagerMult.merge(video);
    }

    @Override
    public void atalizarAudio(Audio audio) {
        entityManagerMult.find(Audio.class, audio);
        entityManagerMult.merge(audio);
    }

    @Override
    public Texto buscaAvancadaTexo(String nome) {
        Query query = entityManagerMult.createNamedQuery("busacaAvancadaTexto");
        query.setParameter("parametroNome", "%" + nome + "%");
        return (Texto) query.getResultList();
    }

    @Override
    public Imagem buscaAvancadaImagem(String nome) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Video buscaAvancadaVideo(String nome) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Audio buscaAvancadaAudio(String nome) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removerMultimidia(Multimidia multimidia) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Multimidia buscarMultimidia(Multimidia multimidia) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Multimidia> listarMultimidia() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Multimidia localizarMultimidia(Integer id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Multimidia buscaAvancadaMultimidia(String nome) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
