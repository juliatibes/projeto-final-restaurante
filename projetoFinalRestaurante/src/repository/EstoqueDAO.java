package repository;

import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {
    static List<Produto> listaProdutosEstoque = new ArrayList<>();

    public static void salvarProdutoEstoque(Produto produto) {
        listaProdutosEstoque.add(produto);
    }
}
