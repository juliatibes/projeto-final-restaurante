package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Venda {
    private static final AtomicInteger count = new AtomicInteger(0);
    private Integer id;
    private LocalDate dataVenda;
    private Integer numeroComanda;
    private FormaPagamentoEnum formaPagamento;
    private List<VendaPedido> listaVendaPedido = new ArrayList<>();

    public Venda(Integer numeroComanda, FormaPagamentoEnum formaPagamento) {
        this.id = count.incrementAndGet();
        this.dataVenda = LocalDate.now();
        this.numeroComanda = numeroComanda;
        this.formaPagamento = formaPagamento;
    }

    public void adicionarVendaPedido (VendaPedido vendaPedido){
        listaVendaPedido.add(vendaPedido);
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

    public FormaPagamentoEnum getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamentoEnum formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Integer getNumeroComanda() {
        return numeroComanda;
    }

    public void setNumeroComanda(Integer numeroComanda) {
        this.numeroComanda = numeroComanda;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public List<VendaPedido> getListaVendaPedido() {
        return listaVendaPedido;
    }

    public void setListaVendaPedido(List<VendaPedido> listaVendaPedido) {
        this.listaVendaPedido = listaVendaPedido;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", dataVenda=" + dataVenda +
                ", numeroComanda=" + numeroComanda +
                ", formaPagamento=" + formaPagamento +
                ", listaVendaPedido=" + listaVendaPedido +
                '}';
    }
}
