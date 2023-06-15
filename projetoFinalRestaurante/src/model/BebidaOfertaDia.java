package model;

import java.time.LocalDate;

public class BebidaOfertaDia extends OfertaDia{

    private Produto produto;

    public BebidaOfertaDia(Integer id, LocalDate dataVenda, Double desconto, Produto produto) {
        super(id, dataVenda, desconto);
        this.produto = produto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
