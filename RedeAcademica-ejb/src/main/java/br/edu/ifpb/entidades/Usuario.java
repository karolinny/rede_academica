package br.edu.ifpb.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;

@Entity
@NamedQueries({
    @NamedQuery(name = "usuario.recuperarTodos", query = "SELECT u from Usuario u "),
    @NamedQuery(name = "usuario.recuperar", query = "SELECT u from Usuario u where u.login = :login and u.senha = :senha"),
    @NamedQuery(name = "usuario.recuperaPeloLogin", query = "SELECT u FROM Usuario u WHERE u.login = :login")})
@SequenceGenerator(name = "seq_gen", sequenceName = "usuario_seq", initialValue = 1, allocationSize = 20)
public class Usuario implements Serializable {

    public enum Tipo {

        DIRETOR, COORDENADOR, COLABORADOR
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    private Integer id;
    @Column(unique = true, length = 20)
    private String login;
    @Column(length = 64)
    private String senha;
    @Column(length = 25)
    private String nome;
    @Column(length = 30)
    private String email;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataCadastro = new Date();
    @Column(length = 10)
    private String matricula;
    @Enumerated(EnumType.STRING)
    private Usuario.Tipo tipo;
    @Column
    private String foto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Usuario.Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Usuario.Tipo tipo) {
        this.tipo = tipo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 37 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 37 * hash + (this.senha != null ? this.senha.hashCode() : 0);
        hash = 37 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 37 * hash + (this.email != null ? this.email.hashCode() : 0);
        hash = 37 * hash + (this.dataCadastro != null ? this.dataCadastro.hashCode() : 0);
        hash = 37 * hash + (this.matricula != null ? this.matricula.hashCode() : 0);
        hash = 37 * hash + (this.tipo != null ? this.tipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        if ((this.senha == null) ? (other.senha != null) : !this.senha.equals(other.senha)) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }
        if ((this.email == null) ? (other.email != null) : !this.email.equals(other.email)) {
            return false;
        }
        if (this.dataCadastro != other.dataCadastro && (this.dataCadastro == null || !this.dataCadastro.equals(other.dataCadastro))) {
            return false;
        }
        if ((this.matricula == null) ? (other.matricula != null) : !this.matricula.equals(other.matricula)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", login=" + login + ", senha=" + senha + ", nome=" + nome + ", email=" + email + ", dataCadastro=" + dataCadastro + ", matricula=" + matricula + ", tipo=" + tipo + '}';
    }
}