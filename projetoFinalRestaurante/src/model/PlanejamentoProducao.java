package model;

import model.Receita;
import model.UnidadeMedidaEnum;

import java.time.LocalDate;

public class PlanejamentoProducao {
    private Integer id;
    private LocalDate diaPlanejado;
    private Receita receita;
    private Double quantidadePlanejada;
    private UnidadeMedidaEnum unidadeMedida;



    public PlanejamentoProducao(Integer id, Receita receita, Double quantidadePlanejada,
                                UnidadeMedidaEnum unidadeMedida, LocalDate diaPlanejado) {
        this.id = id;
        this.receita = receita;
        this.quantidadePlanejada = quantidadePlanejada;
        this.unidadeMedida = unidadeMedida;
        this.diaPlanejado = diaPlanejado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public Double getQuantidadePlanejada() {
        return quantidadePlanejada;
    }

    public void setQuantidadePlanejada(Double quantidadePlanejada) {
        this.quantidadePlanejada = quantidadePlanejada;
    }

    public UnidadeMedidaEnum getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedidaEnum unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public LocalDate getDiaPlanejado() {
        return diaPlanejado;
    }

    public void setDiaPlanejado(LocalDate diaPlanejado) {
        this.diaPlanejado = diaPlanejado;
    }

    @Override
    public String toString() {
        return "PlanejamentoProducao{" +
                "id=" + id +
                ", diaPlanejado=" + diaPlanejado +
                ", receita=" + receita +
                ", quantidadePlanejada=" + quantidadePlanejada +
                ", unidadeMedida=" + unidadeMedida +
                '}';
    }
}
