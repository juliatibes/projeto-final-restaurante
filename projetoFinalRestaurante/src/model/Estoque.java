package model;

import repository.CompraDAO;

import java.math.BigDecimal;

public class Estoque {
    private Integer id;
    private Produto Produto;
    private Double quantidade;
    private UnidadeMedidaEnum unidadeMedida;
    private BigDecimal valorCusto;
    private StatusEnum status;


}
