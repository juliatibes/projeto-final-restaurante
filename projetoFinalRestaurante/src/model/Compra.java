package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Compra {

    private Integer id;
    private String dataCompra; //mudar para LOCALDATE perguntar para o teacher
    private Produto produto;
    private Double quantidade;
    private UnidadeMedidaEnum unidadeMedida;
    private BigDecimal valorCompra;

}

