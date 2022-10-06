package Entidades;

public class Deslocamento {
    private Long codDes;
    private Long codVeiculo;
    private Long codMot;
    private String descricao;
    private String statusDes;

    public Deslocamento() {
        this.codDes = 0L;
        this.codVeiculo = 0L;
        this.codMot = 0L;
        this.descricao = "";
        this.statusDes = "";
    }

    public Long getCodDes() {
        return codDes;
    }

    public void setCodDes(Long codDes) {
        this.codDes = codDes;
    }

    public Long getCodVeiculo() {
        return codVeiculo;
    }

    public void setCodVeiculo(Long codVeiculo) {
        this.codVeiculo = codVeiculo;
    }

    public Long getCodMot() {
        return codMot;
    }

    public void setCodMot(Long codMot) {
        this.codMot = codMot;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatusDes() {
        return statusDes;
    }

    public void setStatusDes(String statusDes) {
        this.statusDes = statusDes;
    }
}
