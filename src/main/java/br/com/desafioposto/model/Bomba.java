package br.com.desafioposto.model;

public class Bomba {

    private Integer id;
    private String nome;
    private Integer tipoCombustivelId;

    public Bomba() {
    }

    public Bomba(Integer id, String nome, Integer tipoCombustivelId) {
        this.id = id;
        this.nome = nome;
        this.tipoCombustivelId = tipoCombustivelId;
    }

    public Bomba(String nome, Integer tipoCombustivelId) {
        this.nome = nome;
        this.tipoCombustivelId = tipoCombustivelId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTipoCombustivelId() {
        return tipoCombustivelId;
    }

    public void setTipoCombustivelId(Integer tipoCombustivelId) {
        this.tipoCombustivelId = tipoCombustivelId;
    }

    @Override
    public String toString() {
        return "Bomba{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipoCombustivelId=" + tipoCombustivelId +
                '}';
    }
}
