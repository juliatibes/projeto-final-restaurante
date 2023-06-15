package model;

import model.Receita;
import model.UnidadeMedidaEnum;


import java.time.LocalDate;

public class PlanejamentoProducao {
    private Integer id;
    private LocalDate diaPlanejado;
    private Receita receitaOfertaDia;
    private Produto bebidaOfertaDia;
    private Integer quantidadePlanejada;

    public PlanejamentoProducao(Integer id, Produto bebidaOfertaDia, Integer quantidadePlanejada) {
        this.id = id;
        this.diaPlanejado = LocalDate.now();
        this.bebidaOfertaDia = bebidaOfertaDia;
        this.quantidadePlanejada = quantidadePlanejada;
    }

    public PlanejamentoProducao(Produto bebidaOfertaDia, Integer quantidadePlanejada) {
        this.diaPlanejado = LocalDate.now();
        this.bebidaOfertaDia = bebidaOfertaDia;
        this.quantidadePlanejada = quantidadePlanejada;
    }

    public PlanejamentoProducao(Integer id, Receita receitaOfertaDia, Integer quantidadePlanejada) {
        this.id = id;
        this.diaPlanejado = LocalDate.now();
        this.receitaOfertaDia = receitaOfertaDia;
        this.quantidadePlanejada = quantidadePlanejada;
    }

    public PlanejamentoProducao(Receita receitaOfertaDia, Integer quantidadePlanejada) {
        this.diaPlanejado = LocalDate.now();
        this.receitaOfertaDia = receitaOfertaDia;
        this.quantidadePlanejada = quantidadePlanejada;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDiaPlanejado() {
        return diaPlanejado;
    }

    public void setDiaPlanejado(LocalDate diaPlanejado) {
        this.diaPlanejado = diaPlanejado;
    }

    public Receita getReceitaOfertaDia() {
        return receitaOfertaDia;
    }

    public void setReceitaOfertaDia(Receita receitaOfertaDia) {
        this.receitaOfertaDia = receitaOfertaDia;
    }

    public Produto getBebidaOfertaDia() {
        return bebidaOfertaDia;
    }

    public void setBebidaOfertaDia(Produto bebidaOfertaDia) {
        this.bebidaOfertaDia = bebidaOfertaDia;
    }

    public Integer getQuantidadePlanejada() {
        return quantidadePlanejada;
    }

    public void setQuantidadePlanejada(Integer quantidadePlanejada) {
        this.quantidadePlanejada = quantidadePlanejada;
    }

    @Override
    public String toString() {
        return "PlanejamentoProducao{" +
                "id=" + id +
                ", diaPlanejado=" + diaPlanejado +
                ", receitaOfertaDia=" + receitaOfertaDia +
                ", bebidaOfertaDia=" + bebidaOfertaDia +
                ", quantidadePlanejada=" + quantidadePlanejada +
                '}';
    }
}



