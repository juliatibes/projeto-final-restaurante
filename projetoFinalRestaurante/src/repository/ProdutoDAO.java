package repository;

import model.Produto;
import model.ProdutoEnum;
import model.ProdutoEstoque;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    static List<Produto> listaProdutos = new ArrayList<>();

    public static void inputProdutos() {
        if (ProdutoDAO.listaProdutos.isEmpty()) {
            listaProdutos.add(new Produto(1, "Massa caseira zézé", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(2, "Bacon", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(3, "Coca-cola", ProdutoEnum.BEBIDA, BigDecimal.valueOf(3)));
            listaProdutos.add(new Produto(4, "Cebolinha verde", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(5, "Ovo", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(6, "Creme de leite", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(7, "Coca-zero", ProdutoEnum.BEBIDA, BigDecimal.valueOf(3)));
            listaProdutos.add(new Produto(8, "Água com gás", ProdutoEnum.BEBIDA, BigDecimal.valueOf(2)));
            listaProdutos.add(new Produto(9, "Água sem gás", ProdutoEnum.BEBIDA, BigDecimal.valueOf(2)));
            listaProdutos.add(new Produto(10, "Suco de Uva", ProdutoEnum.BEBIDA, BigDecimal.valueOf(4)));
            listaProdutos.add(new Produto(11, "Heineken", ProdutoEnum.BEBIDA, BigDecimal.valueOf(8)));
            listaProdutos.add(new Produto(12, "Original", ProdutoEnum.BEBIDA, BigDecimal.valueOf(7)));
            listaProdutos.add(new Produto(13, "Batata", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(14, "Camarão", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(15, "Gnocchi caseiro zézé", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(16, "Queijo Muçarela", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(17, "Queijo Parmesão", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(18, "Queijo Provolone", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(19, "Queijo Gorgonzola", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(20, "Arroz arbóreo caseiro zézé", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(21, "Molho de tomate", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(22, "Tomate", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(23, "Lula", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(24, "Marisco", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(25, "Siri", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(26, "Picanha Friboi", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(27, "Rúcula", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(28, "Manteiga", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(29, "Piadina caseira zezé", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(30, "Nutella", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(31, "Sovete Kibom Creme", ProdutoEnum.INGREDIENTE));

        }
    }

    public static void salvarListaProdutos(Produto produto) {
        listaProdutos.add(produto);
    }

    public static Integer removerProduto(Produto produto) {
        listaProdutos.remove(produto);
        return JOptionPane.showConfirmDialog(null, "Produto excluido com sucesso!",
                "Remover Produto", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
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

    public static Object[] findprodutosInArrayIngrediente() {
        List<Produto> produtos = buscaTodos();
        List<String> produtosNomes = new ArrayList<>();

        for (Produto produto : produtos) {
            if (produto.getTipoProduto() == ProdutoEnum.INGREDIENTE) {
                produtosNomes.add(produto.getNome());
            }
        }
        return produtosNomes.toArray();
    }

    public static Integer aiID() {
        Integer id = listaProdutos.size() + 1;
        return id;
    }
}

