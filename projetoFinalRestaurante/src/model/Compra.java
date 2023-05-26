package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Compra {

    private Integer id;
    private LocalDate dataCompra;
    private Produto produto;
    private Double quantidade;
    private UnidadeMedidaEnum unidadeMedida;


    public Compra(Integer id, LocalDate dataCompra, Produto produto, Double quantidade, UnidadeMedidaEnum unidadeMedida) {
        this.id = id;
        this.dataCompra = dataCompra;
        this.produto = produto;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
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

    public Integer converterUnidadeMedida {
       return
               if (unidadeMedida = KILOGRAMA){
            unidadeMedida = unidadeMedida * 1000;
        if (unidadeMedida = RAMA ){
            unidadeMedida = unidadeMedida / 1000;
        }}
    }
    public double converterGramaaraKg(double grama){

        return 0.001*grama;

    }


    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", dataCompra=" + dataCompra +
                ", produto=" + produto +
                ", quantidade=" + quantidade +
                ", unidadeMedida=" + unidadeMedida +
                '}';
    }
}

