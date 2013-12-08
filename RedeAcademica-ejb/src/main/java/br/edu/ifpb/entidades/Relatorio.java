package br.edu.ifpb.entidades;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Relatorio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar dataRelatorio;
    @OneToOne
    private Multimidia midia;

    public Relatorio() {
    }

    public Integer getId() {
        return id;
    }

    public Multimidia getMidia() {
        return midia;
    }

    public void setMidia(Multimidia midia) {
        this.midia = midia;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getDataRelatorio() {
        return dataRelatorio;
    }

    public void setDataRelatorio(Calendar dataRelatorio) {
        this.dataRelatorio = dataRelatorio;
    }
}
