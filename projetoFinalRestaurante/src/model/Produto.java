package model;

import java.math.BigDecimal;

public class Produto {
    private Integer id;
    private String nome;
    private BigDecimal valorUnitario;

    public Produto(Integer id, String nome, BigDecimal valorUnitario) {
        this.id = id;
        this.nome = nome;
        this.valorUnitario = valorUnitario;
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

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}

