package repository;
import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    static List<Produto> listaProdutos = new ArrayList<>();

    public static void salvarListaProdutos(Produto produto) {
        listaProdutos.add(produto);
    }

    public static List<Produto> buscaTodos() {
        return listaProdutos;
    }
}
