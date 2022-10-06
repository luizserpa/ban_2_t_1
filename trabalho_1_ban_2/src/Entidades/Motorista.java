package Entidades;

import java.util.Calendar;
import java.util.Date;

public class Motorista {
    private Long codMot;
    private String nome;
    private String cnh;
    private Integer dataDesat;

    public Motorista() {
        this.codMot = 0L;
        this.nome = "";
        this.cnh = "";
        this.dataDesat = 0;
    }

    public Long getCodMot() {
        return codMot;
    }

    public void setCodMot(Long codMot) {
        this.codMot = codMot;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Integer getDataDesat() {
        return dataDesat;
    }

    public void setDataDesat(Integer dataDesat) {
        this.dataDesat = dataDesat;
    }
}
