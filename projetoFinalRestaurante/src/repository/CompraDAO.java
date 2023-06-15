package repository;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class CompraDAO implements InterfaceAutoIncrement {

    static List<Compra> listaCompras = new ArrayList<>();

    public static void inputCompras() {
        if (CompraDAO.listaCompras.isEmpty()) {
            listaCompras.add(new Compra(1, LocalDate.now(), ProdutoDAO.listaProdutos.get(0), 5000.0, UnidadeMedidaEnum.GRAMA));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(0),5000.0,UnidadeMedidaEnum.GRAMA); // massa zezé
            listaCompras.add(new Compra(2, LocalDate.now(), ProdutoDAO.listaProdutos.get(1), 500.0, UnidadeMedidaEnum.GRAMA));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(1),500.0,UnidadeMedidaEnum.GRAMA); // bacon
            listaCompras.add(new Compra(3, LocalDate.now(), ProdutoDAO.listaProdutos.get(2), 5.0, UnidadeMedidaEnum.UNIDADE));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(2),5.0,UnidadeMedidaEnum.UNIDADE); //coca cola
            listaCompras.add(new Compra(4, LocalDate.now(), ProdutoDAO.listaProdutos.get(3), 200.0, UnidadeMedidaEnum.GRAMA));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(3),200.0,UnidadeMedidaEnum.GRAMA); // cebolinha verde
            listaCompras.add(new Compra(5, LocalDate.now(), ProdutoDAO.listaProdutos.get(4), 5.0, UnidadeMedidaEnum.UNIDADE));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(4),5.0,UnidadeMedidaEnum.UNIDADE); // ovo
            listaCompras.add(new Compra(6, LocalDate.now(), ProdutoDAO.listaProdutos.get(5), 2.0, UnidadeMedidaEnum.LITRO));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(5),2.0,UnidadeMedidaEnum.LITRO); // creme de leite
            listaCompras.add(new Compra(7, LocalDate.now(), ProdutoDAO.listaProdutos.get(6), 30.0, UnidadeMedidaEnum.UNIDADE));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(6),30.0,UnidadeMedidaEnum.UNIDADE); // coca zero
            listaCompras.add(new Compra(8, LocalDate.now(), ProdutoDAO.listaProdutos.get(7), 50.0, UnidadeMedidaEnum.UNIDADE));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(7),50.0,UnidadeMedidaEnum.UNIDADE); // agua com gas
            listaCompras.add(new Compra(9, LocalDate.now(), ProdutoDAO.listaProdutos.get(8), 35.0, UnidadeMedidaEnum.UNIDADE));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(8),35.0,UnidadeMedidaEnum.UNIDADE); // agua sem gas
            listaCompras.add(new Compra(10, LocalDate.now(), ProdutoDAO.listaProdutos.get(9), 20.0, UnidadeMedidaEnum.UNIDADE));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(9),20.0,UnidadeMedidaEnum.UNIDADE); // suco de uva
            listaCompras.add(new Compra(11, LocalDate.now(), ProdutoDAO.listaProdutos.get(10), 24.0, UnidadeMedidaEnum.UNIDADE));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(10),24.0,UnidadeMedidaEnum.UNIDADE); //heineken
            listaCompras.add(new Compra(12, LocalDate.now(), ProdutoDAO.listaProdutos.get(11), 24.0, UnidadeMedidaEnum.UNIDADE));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(11),24.0,UnidadeMedidaEnum.UNIDADE); //original
            listaCompras.add(new Compra(13, LocalDate.now(), ProdutoDAO.listaProdutos.get(12), 2.0, UnidadeMedidaEnum.KILO));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(12),2.0,UnidadeMedidaEnum.KILO); //batata
            listaCompras.add(new Compra(14, LocalDate.now(), ProdutoDAO.listaProdutos.get(13), 5.0, UnidadeMedidaEnum.KILO));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(13),5.0,UnidadeMedidaEnum.KILO); // camarão
            listaCompras.add(new Compra(15, LocalDate.now(), ProdutoDAO.listaProdutos.get(14), 5.0, UnidadeMedidaEnum.KILO));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(14),5.0,UnidadeMedidaEnum.KILO); //gnhochi
            listaCompras.add(new Compra(16, LocalDate.now(), ProdutoDAO.listaProdutos.get(15), 2.0, UnidadeMedidaEnum.KILO));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(15),2.0,UnidadeMedidaEnum.KILO); // mussarela
            listaCompras.add(new Compra(17, LocalDate.now(), ProdutoDAO.listaProdutos.get(16), 2.0, UnidadeMedidaEnum.KILO));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(16),2.0,UnidadeMedidaEnum.KILO); //parmesão
            listaCompras.add(new Compra(18, LocalDate.now(), ProdutoDAO.listaProdutos.get(17), 2.0, UnidadeMedidaEnum.KILO));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(17),2.0,UnidadeMedidaEnum.KILO); //provolone
            listaCompras.add(new Compra(19, LocalDate.now(), ProdutoDAO.listaProdutos.get(18), 2.0, UnidadeMedidaEnum.KILO));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(18),2.0,UnidadeMedidaEnum.KILO); //gorgonzola
            listaCompras.add(new Compra(20, LocalDate.now(), ProdutoDAO.listaProdutos.get(19), 6.0, UnidadeMedidaEnum.KILO));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(19),6.0,UnidadeMedidaEnum.KILO); //arroz arboreo
            listaCompras.add(new Compra(21, LocalDate.now(), ProdutoDAO.listaProdutos.get(20), 800.0, UnidadeMedidaEnum.GRAMA));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(20),800.0,UnidadeMedidaEnum.GRAMA); // Molho Tomate
            listaCompras.add(new Compra(22, LocalDate.now(), ProdutoDAO.listaProdutos.get(21), 3.0, UnidadeMedidaEnum.KILO));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(21),3.0,UnidadeMedidaEnum.KILO); // Tomate
            listaCompras.add(new Compra(23, LocalDate.now(), ProdutoDAO.listaProdutos.get(22), 1.0, UnidadeMedidaEnum.KILO));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(22),1.0,UnidadeMedidaEnum.KILO); // Lula
            listaCompras.add(new Compra(24, LocalDate.now(), ProdutoDAO.listaProdutos.get(23), 1.0, UnidadeMedidaEnum.KILO));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(23),1.0,UnidadeMedidaEnum.KILO); //Marisco
            listaCompras.add(new Compra(25, LocalDate.now(), ProdutoDAO.listaProdutos.get(24), 1.0, UnidadeMedidaEnum.KILO));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(24),1.0,UnidadeMedidaEnum.KILO); // Siri
            listaCompras.add(new Compra(26, LocalDate.now(), ProdutoDAO.listaProdutos.get(25), 5.0, UnidadeMedidaEnum.KILO));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(25),5.0,UnidadeMedidaEnum.KILO); // picanha
            listaCompras.add(new Compra(27, LocalDate.now(), ProdutoDAO.listaProdutos.get(26), 200.0, UnidadeMedidaEnum.GRAMA));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(26),200.0,UnidadeMedidaEnum.GRAMA); // rucula
            listaCompras.add(new Compra(28, LocalDate.now(), ProdutoDAO.listaProdutos.get(27), 1.0, UnidadeMedidaEnum.KILO));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(27),1.0,UnidadeMedidaEnum.KILO); // manteiga
            listaCompras.add(new Compra(29, LocalDate.now(), ProdutoDAO.listaProdutos.get(28), 20.0, UnidadeMedidaEnum.UNIDADE));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(28),20.0,UnidadeMedidaEnum.UNIDADE); // piadina
            listaCompras.add(new Compra(30, LocalDate.now(), ProdutoDAO.listaProdutos.get(29), 2.0, UnidadeMedidaEnum.KILO));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(29),2.0,UnidadeMedidaEnum.KILO); // Nutella
            listaCompras.add(new Compra(31, LocalDate.now(), ProdutoDAO.listaProdutos.get(30), 3.0, UnidadeMedidaEnum.LITRO));
            EstoqueDAO.salvarProdutoEstoque(ProdutoDAO.listaProdutos.get(30),3.0,UnidadeMedidaEnum.KILO); // Sorvete creme


        }
    }

    public static void salvarNovaCompra(Compra compra) {
        CompraDAO compraDAO = new CompraDAO();
        compra.setId(compraDAO.geraID());
        listaCompras.add(compra);
        EstoqueDAO.salvarProdutoEstoque(compra.getProduto(), compra.getQuantidade(), compra.getUnidadeMedida());
        System.out.println(CompraDAO.buscarTodos());
    }

    public static List<Compra> buscarTodos() {
        inputCompras();
        return listaCompras;
    }

//    public static Integer aiID() {
//        Integer id = listaCompras.size() + 1;
//        return id;
//    }
//

    @Override
    public Integer geraID() {
        Integer id = listaCompras.size() + 1;
        return id;
    }
}

