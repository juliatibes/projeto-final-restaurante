package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private Integer id;
    private LocalDate dataVenda;
    private Receita receita;
    private UnidadeMedidaEnum unidadeMedida;

    public Venda(Integer id, LocalDate dataVenda, Receita receita, UnidadeMedidaEnum unidadeMedida) {
        this.id = id;
        this.dataVenda = LocalDate.now();
        this.receita = receita;
        this.unidadeMedida = unidadeMedida;
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

//    public void setDataVenda(LocalDate dataVenda) {
//        this.dataVenda = dataVenda;
//    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public UnidadeMedidaEnum getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedidaEnum unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", dataVenda=" + dataVenda +
                ", receita=" + receita +
                ", unidadeMedida=" + unidadeMedida +
                '}';
    }
}
