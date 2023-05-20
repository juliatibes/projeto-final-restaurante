package model;

import java.math.BigDecimal;

public class Cardapio {
    private Integer id;
    private Receita receita;
    private String descricao;
    private BigDecimal valorVenda;


    public Cardapio(Integer id, Receita receita, String descricao, BigDecimal valorVenda) {
        this.id = id;
        this.receita = receita;
        this.descricao = descricao;
        this.valorVenda = valorVenda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    @Override
    public String toString() {
        return "Cardapio{" +
                "id=" + id +
                ", receita=" + receita +
                ", descricao='" + descricao + '\'' +
                ", valorVenda=" + valorVenda +
                '}';
    }
}
