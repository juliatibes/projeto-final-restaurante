package model;

import java.time.LocalDate;

public  class ReceitaOfertaDia extends OfertaDia {

    private Receita receita;


    public ReceitaOfertaDia(Integer id, Double desconto, Receita receita) {
        super(id,desconto);
        this.receita = receita;
    }

    public ReceitaOfertaDia(Double desconto, Receita receita) {
        super(desconto);
        this.receita = receita;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }


    @Override
    public String toString() {
        return "ReceitaOfertaDia{" +
                "receita=" + receita +
                '}';
    }
}
