package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Produto {
    private Integer id;
    private String nome;
    private ProdutoEnum tipoProduto;
    private BigDecimal valorCustoProduto;
    private BigDecimal valorVendaProduto;

    public Produto(Integer id, String nome, ProdutoEnum tipoProduto, BigDecimal produtoBebidaValorCusto) {
        this.id = id;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.valorCustoProduto = produtoBebidaValorCusto;
        this.valorVendaProduto = calculaValorVenda(produtoBebidaValorCusto);
    }
    public Produto(String nome, ProdutoEnum tipoProduto, BigDecimal produtoBebidaValorCusto) {
        this.nome = nome;
        this.tipoProduto = tipoProduto;
        this.valorCustoProduto = produtoBebidaValorCusto;
        this.valorVendaProduto = calculaValorVenda(produtoBebidaValorCusto);
    }

    public Produto (Integer id, String nome, ProdutoEnum tipoProduto){
        this(id,nome,tipoProduto,null);
    }
    public Produto (String nome, ProdutoEnum tipoProduto){
        this(nome,tipoProduto,null);
    }

    public BigDecimal calculaValorVenda(BigDecimal valorCusto){
        if (valorCusto!=null) {
            valorVendaProduto = valorCusto.add(valorCusto.multiply(BigDecimal.valueOf(100).divide(BigDecimal.valueOf(100))));
        }
            return valorVendaProduto;

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

    public BigDecimal getValorCustoProduto() {
        return valorCustoProduto;
    }

    public void setValorCustoProduto(BigDecimal valorCustoProduto) {
        this.valorCustoProduto = valorCustoProduto;
    }

    public BigDecimal getValorVendaProduto() {
        return valorVendaProduto;
    }

    public void setValorVendaProduto(BigDecimal valorVendaProduto) {
        this.valorVendaProduto = valorVendaProduto;
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
                ", produtoBebidaValorCusto=" + valorCustoProduto +
                ", produtoBebidaValorVenda=" + valorVendaProduto +
                '}';
    }
}

