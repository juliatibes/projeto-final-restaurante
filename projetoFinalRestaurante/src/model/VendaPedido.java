package model;

public class VendaPedido {

    private Receita receita;
    private Produto produtoBebida;
    private Integer quantidade;
    private String observacao;

    public VendaPedido(Receita receita, Integer quantidade, String observacao) {
        this.receita = receita;
        this.quantidade = quantidade;
        this.observacao = observacao;
    }

    public VendaPedido(Produto produtoBebida, Integer quantidade, String observacao) {
        this.produtoBebida = produtoBebida;
        this.quantidade = quantidade;
        this.observacao = observacao;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "VendaPedido{" +
                "receita=" + receita +
                ", produtoBebida=" + produtoBebida +
                ", quantidade=" + quantidade +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
