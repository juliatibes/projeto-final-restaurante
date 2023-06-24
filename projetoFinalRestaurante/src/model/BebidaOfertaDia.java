package model;

import java.time.LocalDate;

public class
BebidaOfertaDia extends OfertaDia{

    private Produto produto;

    public BebidaOfertaDia(Integer id, LocalDate dataVenda, Double desconto, Produto produto) {
        super(id, desconto);
        this.produto = produto;
    }

    public BebidaOfertaDia(Double desconto, Produto produto) {
        super(desconto);
        this.produto = produto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "BebidaOfertaDia{" +
                "produto=" + produto +
                '}';
    }
}
