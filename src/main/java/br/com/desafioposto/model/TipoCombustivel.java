/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.desafioposto.model;

/**
 *
 * @author lucas
 */
public class TipoCombustivel {
    private Integer id; 
    private String nome;
    private Double precoLitro;
    
    public TipoCombustivel() {
        
    }
    
    public TipoCombustivel(Integer id, String nome, Double precoLitro) {
        this.id = id; 
        this.nome = nome; 
        this.precoLitro = precoLitro;
    }
    
    public TipoCombustivel(String nome, Double precoLitro) {
        this.nome = nome; 
        this.precoLitro = precoLitro; 
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

    public Double getPrecoLitro() {
        return precoLitro;
    }

    public void setPrecoLitro(Double precoLitro) {
        this.precoLitro = precoLitro;
    }
    
    @Override
    public String toString() {
        return "TipoCombustivel{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", precoLitro=" + precoLitro +
                '}';
    }

    
}
