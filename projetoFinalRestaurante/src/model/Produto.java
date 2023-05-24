package model;

import javax.annotation.Generated;
import java.math.BigDecimal;

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
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipoProduto=" + tipoProduto +
                '}';
    }
}

