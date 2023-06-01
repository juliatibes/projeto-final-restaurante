package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Venda {
    private static final AtomicInteger count = new AtomicInteger(0);
    private Integer id;
    private LocalDate dataVenda;
    private Receita receita;
    private FormaPagamentoEnum formaPagamento;

    public Venda(Receita receita, FormaPagamentoEnum formaPagamento) {
        this.id = count.incrementAndGet();
        this.dataVenda = LocalDate.now();
        this.receita = receita;
        this.formaPagamento = formaPagamento;
    }

    public Integer getId() {
        return id;
    }

//    public void setId(Integer id) {
//        this.id = id;
//    }

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

    public FormaPagamentoEnum getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamentoEnum formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", dataVenda=" + dataVenda +
                ", receita=" + receita +
                ", formaPagamento=" + formaPagamento +
                '}';
    }
}
