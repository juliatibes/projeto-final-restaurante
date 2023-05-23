package repository;

import model.Compra;
import model.Produto;
import model.UnidadeMedidaEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class CompraDAO {

    private Integer id;
    private LocalDate dataCompra;
    private Produto produto;
    private Double quantidade;
    private UnidadeMedidaEnum unidadeMedida;


    public CompraDAO(Integer id, LocalDate dataCompra, Produto produto, Double quantidade, UnidadeMedidaEnum unidadeMedida) {
        this.id = id;
        this.dataCompra = dataCompra;
        this.produto = produto;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
    }

    public static void inputirdados(){
        if (CompraDAO.listaCompras.isEmpty()) {
            CompraDAO compraDAO = new CompraDAO(10, LocalDate.now(), ProdutoDAO.listaProdutos.get(0), 5.0, UnidadeMedidaEnum.UNIDADE);
        }
    }



    static List<Compra> listaCompras = new ArrayList<>();


    public static void salvarNovaCompra(Compra produto) {
        listaCompras.add(produto);
    }
    public static List<Compra> listarCompras() {
        return listaCompras;
    }

    public static Integer aiID(){
        Integer id = listaCompras.size() + 1;
        return id;
    }


    }

