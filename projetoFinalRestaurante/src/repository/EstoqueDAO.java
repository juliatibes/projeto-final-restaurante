package repository;

import model.Produto;
import model.ProdutoEstoque;
import model.UnidadeMedidaEnum;

import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {
    static List<ProdutoEstoque> listaProdutosEstoque = new ArrayList<>();

    public static void salvarProdutoEstoque(Produto produto, Double quantidade, UnidadeMedidaEnum unidadeMedidaEnum) {
        Double quantidadeAtual = 0.00;
        ProdutoEstoque produtoEstoqueEncontrado = null;
        for (ProdutoEstoque produtoEstoque : listaProdutosEstoque) {
            if (produtoEstoque.getProduto().getId() == produto.getId()) {
                quantidadeAtual = produtoEstoque.getQuantidade();
                produtoEstoqueEncontrado = produtoEstoque;

            }
        }

        if (produtoEstoqueEncontrado != null) {
            listaProdutosEstoque.remove(produtoEstoqueEncontrado);
        }

        listaProdutosEstoque.add(new ProdutoEstoque(produto, quantidade + quantidadeAtual, unidadeMedidaEnum));
        System.out.println(listaProdutosEstoque);
    }
}
