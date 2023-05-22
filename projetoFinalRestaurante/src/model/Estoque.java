package model;

import repository.CompraDAO;

import java.math.BigDecimal;

public class Estoque {
    private Integer id;
    private Produto Produto;
    private Double quantidade;
    private UnidadeMedidaEnum unidadeMedida;
    private BigDecimal valorCusto;
    private StatusEnum status;

    public Estoque(Integer id, model.Produto produto, Double quantidade, UnidadeMedidaEnum unidadeMedida, BigDecimal valorCusto, StatusEnum status) {
        this.id = id;
        Produto = produto;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
        this.valorCusto = valorCusto;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public model.Produto getProduto() {
        return Produto;
    }

    public void setProduto(model.Produto produto) {
        Produto = produto;
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

    public BigDecimal getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(BigDecimal valorCusto) {
        this.valorCusto = valorCusto;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "id=" + id +
                ", Produto=" + Produto +
                ", quantidade=" + quantidade +
                ", unidadeMedida=" + unidadeMedida +
                ", valorCusto=" + valorCusto +
                ", status=" + status +
                '}';
    }
}
