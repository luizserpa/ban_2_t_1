package Entidades;

public class Modelo {
    private Long codModelo;
    private Long codMarca;
    private String nome;
    private Character combustivel;
    private Double motor;
    private Integer dataDesat;

    public Modelo() {
        this.codModelo = 0L;
        this.codMarca = 0L;
        this.nome = "";
        this.combustivel = ' ';
        this.motor = 0.0;
        this.dataDesat = 0;
    }

    public Long getCodModelo() {
        return codModelo;
    }

    public void setCodModelo(Long codModelo) {
        this.codModelo = codModelo;
    }

    public Long getCodMarca() {
        return codMarca;
    }

    public void setCodMarca(Long codMarca) {
        this.codMarca = codMarca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Character getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(Character combustivel) {
        this.combustivel = combustivel;
    }

    public Double getMotor() {
        return motor;
    }

    public void setMotor(Double motor) {
        this.motor = motor;
    }

    public Integer getDataDesat() {
        return dataDesat;
    }

    public void setDataDesat(Integer dataDesat) {
        this.dataDesat = dataDesat;
    }
}
