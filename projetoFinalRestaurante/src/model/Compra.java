package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Compra {

    private Integer id;
    private String dataCompra; //mudar para LOCALDATE perguntar para o teacher
    private String dataValidade; //mudar para LOCALDATE perguntar para o teacher JOPTION
    private String nomeProduto;
    private Integer quantidade;
    private UnidadeMedidaEnum unidadeMedida;
    private BigDecimal valorCompra;

    public Compra(Integer id, String dataCompra, String dataValidade, String nomeProduto,
                  Integer quantidade, UnidadeMedidaEnum unidadeMedida, BigDecimal valorCompra) {
        this.id = id;
        this.dataCompra = dataCompra;
        this.dataValidade = dataValidade;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
        this.valorCompra = valorCompra;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public UnidadeMedidaEnum getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedidaEnum unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }
}
