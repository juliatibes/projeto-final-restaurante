package model;

import java.time.LocalDate;

public  class ReceitaOfertaDia extends OfertaDia {

    private Receita receita;


    public ReceitaOfertaDia(Integer id, LocalDate dataVenda, Double desconto, Receita receita) {
        super(id, dataVenda, desconto);
        this.receita = receita;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }
}
