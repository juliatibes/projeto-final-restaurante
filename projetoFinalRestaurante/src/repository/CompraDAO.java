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

    public static void inputCompras(){
        if (CompraDAO.listaCompras.isEmpty()) {
            listaCompras.add(new Compra(1, LocalDate.now(), ProdutoDAO.listaProdutos.get(0), 5000.0, UnidadeMedidaEnum.GRAMA));
            listaCompras.add(new Compra(2, LocalDate.now(), ProdutoDAO.listaProdutos.get(1), 500.0, UnidadeMedidaEnum.GRAMA));
            listaCompras.add(new Compra(3, LocalDate.now(), ProdutoDAO.listaProdutos.get(2), 5.0, UnidadeMedidaEnum.UNIDADE));
            listaCompras.add(new Compra(4, LocalDate.now(), ProdutoDAO.listaProdutos.get(3), 200.0, UnidadeMedidaEnum.GRAMA));
            listaCompras.add(new Compra(5, LocalDate.now(), ProdutoDAO.listaProdutos.get(4), 5.0, UnidadeMedidaEnum.UNIDADE));
            listaCompras.add(new Compra(6, LocalDate.now(), ProdutoDAO.listaProdutos.get(5), 300.0, UnidadeMedidaEnum.GRAMA));
        }
    }



    static List<Compra> listaCompras = new ArrayList<>();


    public static void salvarNovaCompra(Compra produto) {
        listaCompras.add(produto);
    }
    public static List<Compra> buscarTodos() {
        return listaCompras;
    }

    public static Integer aiID(){
        Integer id = listaCompras.size() + 1;
        return id;
    }


    }

