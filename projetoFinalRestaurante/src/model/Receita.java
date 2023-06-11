package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Receita {

    private Integer id;
    private String nome;
    private List<ReceitaIngrediente> listaIngredientes =  new ArrayList<>();
    private ReceitaClasseEnum receitaClasse;
    private BigDecimal valorCusto;
    private BigDecimal valorVenda;
    private BigDecimal lucroVenda;


    public Receita(Integer id, String nome, ReceitaClasseEnum receitaClasse,BigDecimal valorCusto) {
        this.id = id;
        this.nome = nome;
        this.receitaClasse = receitaClasse;
        this.valorCusto = valorCusto;
        this.valorVenda = calculaValorVenda(valorCusto);
    }

    public void adicionarIngrediente (ReceitaIngrediente ingrediente){
        listaIngredientes.add(ingrediente);
    }

    public BigDecimal calculaValorVenda(BigDecimal valorCusto){
       valorVenda = valorCusto.add(valorCusto.multiply(BigDecimal.valueOf(100).divide(BigDecimal.valueOf(100))));
        return valorVenda;
    }

    public BigDecimal calculaLucro(){
        lucroVenda = this.valorVenda.subtract(this.valorCusto);
        return lucroVenda;
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

    public ReceitaClasseEnum getReceitaClasse() {
        return receitaClasse;
    }

    public void setReceitaClasse(ReceitaClasseEnum receitaClasse) {
        this.receitaClasse = receitaClasse;
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

    public BigDecimal getLucroVenda() { return lucroVenda; }

    public void setLucroVenda(BigDecimal lucroVenda) { this.lucroVenda = lucroVenda;}

    @Override
    public String toString() {
        return "Receita{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", listaIngredientes=" + listaIngredientes +
                ", receitaClasse=" + receitaClasse +
                ", valorCusto=" + valorCusto +
                ", valorVenda=" + valorVenda +
                '}';
    }
}
