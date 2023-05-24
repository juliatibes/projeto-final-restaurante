package repository;
import model.Produto;
import model.ProdutoEnum;
import model.UnidadeMedidaEnum;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Integer id;
    private String nome;
    private ProdutoEnum tipoProduto;

    static List<Produto> listaProdutos = new ArrayList<Produto>();

    public ProdutoDAO(Integer id, String nome, ProdutoEnum tipoProduto) {
        this.id = id;
        this.nome = nome;
        this.tipoProduto = tipoProduto;
    }

    public static void inputDados(){
        if (ProdutoDAO.listaProdutos.isEmpty()) {
            listaProdutos.add(new Produto(1, "Arroz", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(2, "Feijão", ProdutoEnum.INGREDIENTE));
            listaProdutos.add(new Produto(3, "Coca-cola 2L", ProdutoEnum.BEBIBA));
        }
    }

    public static void salvarListaProdutos(Produto produto) {
        listaProdutos.add(produto);
    }

    public static Integer removerProduto(Produto produto){
        listaProdutos.remove(produto);
        return JOptionPane.showConfirmDialog(null,"Produto excluido com sucesso!",
                "Remover Produto",JOptionPane.DEFAULT_OPTION);
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

