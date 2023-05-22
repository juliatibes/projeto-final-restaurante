package model;

import java.math.BigDecimal;

public class Produto {
    private Integer id;
    private String nome;
    private ProdutoEnum tipoProduto;
    private BigDecimal valorUnitario;

    public Produto(Integer id, String nome, ProdutoEnum tipoProduto, BigDecimal valorUnitario) {
        this.id = id;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.valorUnitario = valorUnitario;
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

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipoProduto=" + tipoProduto +
                ", valorUnitario=" + valorUnitario +
                '}';
    }
}

