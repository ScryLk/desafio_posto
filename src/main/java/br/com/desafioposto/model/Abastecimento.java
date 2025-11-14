package br.com.desafioposto.model;

import java.time.LocalDateTime;

public class Abastecimento {

    private Integer id;
    private Integer bombaId;
    private LocalDateTime dataAbastecimento;
    private Double litros;
    private Double valorTotal;
    private Double precoLitroPraticado;

    public Abastecimento() {
    }

    public Abastecimento(Integer id,
                         Integer bombaId,
                         LocalDateTime dataAbastecimento,
                         Double litros,
                         Double valorTotal,
                         Double precoLitroPraticado) {
        this.id = id;
        this.bombaId = bombaId;
        this.dataAbastecimento = dataAbastecimento;
        this.litros = litros;
        this.valorTotal = valorTotal;
        this.precoLitroPraticado = precoLitroPraticado;
    }

    public Abastecimento(Integer bombaId,
                         LocalDateTime dataAbastecimento,
                         Double litros,
                         Double valorTotal,
                         Double precoLitroPraticado) {
        this.bombaId = bombaId;
        this.dataAbastecimento = dataAbastecimento;
        this.litros = litros;
        this.valorTotal = valorTotal;
        this.precoLitroPraticado = precoLitroPraticado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBombaId() {
        return bombaId;
    }

    public void setBombaId(Integer bombaId) {
        this.bombaId = bombaId;
    }

    public LocalDateTime getDataAbastecimento() {
        return dataAbastecimento;
    }

    public void setDataAbastecimento(LocalDateTime dataAbastecimento) {
        this.dataAbastecimento = dataAbastecimento;
    }

    public Double getLitros() {
        return litros;
    }

    public void setLitros(Double litros) {
        this.litros = litros;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getPrecoLitroPraticado() {
        return precoLitroPraticado;
    }

    public void setPrecoLitroPraticado(Double precoLitroPraticado) {
        this.precoLitroPraticado = precoLitroPraticado;
    }

    @Override
    public String toString() {
        return "Abastecimento{" +
                "id=" + id +
                ", bombaId=" + bombaId +
                ", dataAbastecimento=" + dataAbastecimento +
                ", litros=" + litros +
                ", valorTotal=" + valorTotal +
                ", precoLitroPraticado=" + precoLitroPraticado +
                '}';
    }
}
