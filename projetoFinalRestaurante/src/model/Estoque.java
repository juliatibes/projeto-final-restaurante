package model;

public class Estoque {

    private Produto produto;
    private Double quantidade;
    private UnidadeMedidaEnum unidadeMedida;

    public Estoque(Produto produto, Double quantidade, UnidadeMedidaEnum unidadeMedida) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public UnidadeMedidaEnum getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedidaEnum unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    @Override
    public String toString() {
        return "ProdutoEstoque{" +
                "produto=" + produto +
                ", quantidade=" + quantidade +
                ", unidadeMedida=" + unidadeMedida +
                '}';
    }
}
