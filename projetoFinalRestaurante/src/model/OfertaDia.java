package model;

import java.time.LocalDate;

public abstract class OfertaDia {

    private Integer id;
    private LocalDate dataVenda;
    private Double desconto;

    public OfertaDia(Integer id, LocalDate dataVenda, Double desconto) {
        this.id = id;
        this.dataVenda = dataVenda;
        this.desconto = desconto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }


}