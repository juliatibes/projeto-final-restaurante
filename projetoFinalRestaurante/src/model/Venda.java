package model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Venda {
    private Integer id;
    private LocalDate dataVenda;
    private Integer numeroComanda;
    private FormaPagamentoEnum formaPagamento;
    private List<VendaPedido> listaVendaPedido = new ArrayList<>();

    public Venda(Integer id, Integer numeroComanda, FormaPagamentoEnum formaPagamento) {
        this.id = id;
        this.dataVenda = LocalDate.now();
        this.numeroComanda = numeroComanda;
        this.formaPagamento = formaPagamento;
    }

    public BigDecimal calculaValorListaVenda(Venda venda){

        BigDecimal valorVendaReceitaTotal = BigDecimal.ZERO;
        BigDecimal valorVendaBebidaTotal = BigDecimal.ZERO;

        for (int x = 0;x < venda.getListaVendaPedido().size();x++){

            if (venda.getListaVendaPedido().get(x).getReceita()!=null){
                valorVendaReceitaTotal = valorVendaReceitaTotal.add(venda.getListaVendaPedido().get(x).getReceita().getValorVenda()
                        .multiply(BigDecimal.valueOf( venda.getListaVendaPedido().get(x).getQuantidade())));
            }
            if (venda.getListaVendaPedido().get(x).getProdutoBebida()!=null){
                valorVendaBebidaTotal = valorVendaBebidaTotal.add(venda.getListaVendaPedido().get(x).getProdutoBebida().getValorVendaProduto()
                        .multiply(BigDecimal.valueOf( venda.getListaVendaPedido().get(x).getQuantidade())));
            }
        }
        return valorVendaBebidaTotal.add(valorVendaReceitaTotal);
    }

    public void adicionarVendaPedido (VendaPedido vendaPedido){
        listaVendaPedido.add(vendaPedido);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public LocalDate getDataVenda() {
//        return dataVenda;
//    }

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
