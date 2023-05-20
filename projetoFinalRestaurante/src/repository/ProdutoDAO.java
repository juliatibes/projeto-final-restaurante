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
        public static List<Produto> buscarPorNome(String nome) {
            List<Produto> produtosFiltradas = new ArrayList<>();
            for (Produto produto : listaProdutos) {
                if (produto.getNome().contains(nome)) {
                    produtosFiltradas.add(produto);
                }
            }
            return produtosFiltradas;
        }

    public static Object[] findprodutosInArray() {
        List<Produto> produtos = buscaTodos();
        List<String> produtosNomes = new ArrayList<>();

        for (Produto produto : produtos) {
            produtosNomes.add(produto.getNome());
        }
        return produtosNomes.toArray();
    }



    }

