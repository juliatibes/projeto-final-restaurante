package model;

public class VendaPedido {

    private Receita receita;
    private Produto produtoBebida;
    private Integer quantidade;

    public VendaPedido(Receita receita, Integer quantidade) {
        this.receita = receita;
        this.quantidade = quantidade;
    }

    public VendaPedido(Produto produtoBebida, Integer quantidade) {
        this.produtoBebida = produtoBebida;
        this.quantidade = quantidade;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public Produto getProdutoBebida() {
        return produtoBebida;
    }

    public void setProdutoBebida(Produto produtoBebida) {
        this.produtoBebida = produtoBebida;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "VendaPedido{" +
                "receita=" + receita +
                ", produtoBebida=" + produtoBebida +
                ", quantidade=" + quantidade +
                '}';
    }
}
