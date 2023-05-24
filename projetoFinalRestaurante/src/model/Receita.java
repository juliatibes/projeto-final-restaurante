package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Receita {

    private Integer id;
    private String nome;
    private List<ReceitaIngrediente> listaIngredientes =  new ArrayList<>();
    private BigDecimal valorCusto;
    private BigDecimal valorVenda;

    public Receita(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public void adicionarIngrediente (ReceitaIngrediente ingrediente){
        listaIngredientes.add(ingrediente);
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ReceitaIngrediente> getListaIngredientes() {
        return listaIngredientes;
    }

    public void setListaIngredientes(List<ReceitaIngrediente> listaIngredientes) {
        this.listaIngredientes = listaIngredientes;
    }

    public BigDecimal getValorCusto() {
        return valorCusto;
    }

    public void setValorCusto(BigDecimal valorCusto) {
        this.valorCusto = valorCusto;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    @Override
    public String toString() {
        return "Receita{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", listaIngredientes=" + listaIngredientes +
                ", valorCusto=" + valorCusto +
                ", valorVenda=" + valorVenda +
                '}';
    }
}
