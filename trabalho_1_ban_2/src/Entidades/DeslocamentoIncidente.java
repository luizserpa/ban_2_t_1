package Entidades;

public class DeslocamentoIncidente {
    private Long codDes;
    private Long codVeiculo;
    private Long codMot;
    private String descricao;
    private String tipoInc;
    private String descricaoIncidente;
    private String motorista;
    private String veiculo;

    public DeslocamentoIncidente() {
        this.codDes = 0L;
        this.codVeiculo = 0L;
        this.codMot = 0L;
        this.descricao = "";
        this.tipoInc = "";
        this.descricaoIncidente = "";
        this.motorista = "";
        this.veiculo = "";
    }

    public String getMotorista() {
        return motorista;
    }

    public void setMotorista(String motorista) {
        this.motorista = motorista;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
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

    public String getTipoInc() {
        return tipoInc;
    }

    public void setTipoInc(String tipoInc) {
        this.tipoInc = tipoInc;
    }

    public String getDescricaoIncidente() {
        return descricaoIncidente;
    }

    public void setDescricaoIncidente(String descricaoIncidente) {
        this.descricaoIncidente = descricaoIncidente;
    }
}
