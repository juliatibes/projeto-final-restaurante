package model;

import javax.annotation.Generated;
import java.math.BigDecimal;
import java.util.Objects;

public class Produto {
    private Integer id;
    private String nome;
    private ProdutoEnum tipoProduto;


    public Produto(Integer id, String nome, ProdutoEnum tipoProduto) {
        this.id = id;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
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

    public ProdutoEnum getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(ProdutoEnum tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipoProduto=" + tipoProduto +
                '}';
    }
}

