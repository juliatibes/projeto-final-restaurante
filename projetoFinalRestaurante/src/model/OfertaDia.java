package model;

import java.time.LocalDate;

public abstract class OfertaDia {

    private Integer id;
    private LocalDate dataOferta;
    private Double desconto;

    public OfertaDia(Integer id, Double desconto) {
        this.id = id;
        this.dataOferta = LocalDate.now();
        this.desconto = desconto;
    }

    public OfertaDia(Double desconto) {
        this.dataOferta = LocalDate.now();
        this.desconto = desconto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataOferta() {
        return dataOferta;
    }

    public void setDataOferta(LocalDate dataOferta) {
        this.dataOferta = dataOferta;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    @Override
    public String toString() {
        return "OfertaDia{" +
                "id=" + id +
                ", dataVenda=" + dataOferta +
                ", desconto=" + desconto +
                '}';
    }
}