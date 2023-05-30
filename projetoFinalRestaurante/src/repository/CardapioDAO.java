package repository;

import model.Cardapio;
import model.Produto;

import java.util.ArrayList;
import java.util.List;


public class CardapioDAO {
    static List<Cardapio> listaCardapio = new ArrayList<>();


    public static void salvarNovaReceita(Cardapio produto) { listaCardapio.add(produto);}

    public static List<Cardapio> buscarTodos() {return listaCardapio;}

    public static Object[] findcardapiosInArray() {
        List<Produto> produtos = ProdutoDAO.buscaTodos();
        List<String> bebidasNomes = new ArrayList<>();

        for (Produto produto : produtos) {
            bebidasNomes.add(produto.getNome());
        }
        return bebidasNomes.toArray();
    }
}
