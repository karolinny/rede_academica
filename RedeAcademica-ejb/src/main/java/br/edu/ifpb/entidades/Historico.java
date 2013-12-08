package br.edu.ifpb.entidades;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

@Entity
@NamedQuery(name = "historico.recuperar", query = "SELECT h from Historico h ")
public class Historico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(length = 80)
    private String status;
    @Column(length = 200)
    private String acao;
    @Column(length = 20)
    private String ip;
    @Column(length = 80, name = "usuario_login")
    private String login;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dataHistorivo;

    public Historico() {
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getAcao() {
        return acao;
    }

    public String getIp() {
        return ip;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDataHistorivo() {
        return dataHistorivo;
    }

    public void setDataHistorivo(Date dataHistorivo) {
        this.dataHistorivo = dataHistorivo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.id;
        hash = 71 * hash + (this.status != null ? this.status.hashCode() : 0);
        hash = 71 * hash + (this.acao != null ? this.acao.hashCode() : 0);
        hash = 71 * hash + (this.ip != null ? this.ip.hashCode() : 0);
        hash = 71 * hash + (this.login != null ? this.login.hashCode() : 0);
        hash = 71 * hash + (this.dataHistorivo != null ? this.dataHistorivo.hashCode() : 0);
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
        final Historico other = (Historico) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.status == null) ? (other.status != null) : !this.status.equals(other.status)) {
            return false;
        }
        if ((this.acao == null) ? (other.acao != null) : !this.acao.equals(other.acao)) {
            return false;
        }
        if ((this.ip == null) ? (other.ip != null) : !this.ip.equals(other.ip)) {
            return false;
        }
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        if (this.dataHistorivo != other.dataHistorivo && (this.dataHistorivo == null || !this.dataHistorivo.equals(other.dataHistorivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Historico{" + "id=" + id + ", status=" + status + ", acao=" + acao + ", ip=" + ip + ", login=" + login + ", dataHistorivo=" + dataHistorivo + '}';
    }
}
