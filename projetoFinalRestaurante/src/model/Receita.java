package model;

import java.math.BigDecimal;

public class Receita {
    private Produto produto;
    private String descricao;
    private BigDecimal valorReceita;
    private BigDecimal valorCusto;


    public Receita(Produto produto, String descricao, BigDecimal valorReceita, BigDecimal valorCusto) {
        this.produto = produto;
        this.descricao = descricao;
        this.valorReceita = valorReceita;
        this.valorCusto = valorCusto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        descricao = descricao;
    }

    public BigDecimal getValorReceita() {
        return valorReceita;
    }

    public void setValorReceita(BigDecimal valorReceita) {
        this.valorReceita = valorReceita;
    }

    public BigDecimal getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(BigDecimal valorCusto) {
        this.valorCusto = valorCusto;
    }

    @Override
    public String toString() {
        return "Receita{" +
                "produto=" + produto +
                ", descricao='" + descricao + '\'' +
                ", valorReceita=" + valorReceita +
                ", valorCusto=" + valorCusto +
                '}';
    }
}
