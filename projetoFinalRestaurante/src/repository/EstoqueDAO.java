package repository;

import model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {
    static List<ProdutoEstoque> listaProdutosEstoque = new ArrayList<>();

    public static List<ProdutoEstoque> buscaTodos() {
            return listaProdutosEstoque;
    }

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

    public static Integer verificaDisponibilidade (List<VendaPedido> listaReceitaCarinho){
        Double quantidadeCalculada = 0.00;
        List<ProdutoEstoque> listaProdutosEstoqueVerificacao = new ArrayList<>();

        for (ProdutoEstoque produtoEstoque : listaProdutosEstoque){
             listaProdutosEstoqueVerificacao.add(produtoEstoque);
        }

        for ( int z = 0; z < listaReceitaCarinho.size();z ++) {
            for (int x = 0; x < listaReceitaCarinho.get(z).getReceita().getListaIngredientes().size(); x++) {

                quantidadeCalculada = listaReceitaCarinho.get(z).getReceita().getListaIngredientes().get(x).getQuantidade() *
                        listaReceitaCarinho.get(z).getQuantidade();

                for (int y = 0; y < listaProdutosEstoqueVerificacao.size(); y++) {
                    if (listaProdutosEstoqueVerificacao.get(y).getProduto().equals
                            (listaReceitaCarinho.get(z).getReceita().getListaIngredientes().get(x).getProduto())){
                        if (listaProdutosEstoqueVerificacao.get(y).getQuantidade() < quantidadeCalculada) {
                            return 1;
                        } else {
                            List <ProdutoEstoque> guardaProdutoEstoque  = new ArrayList<>();
                            guardaProdutoEstoque.add(listaProdutosEstoqueVerificacao.get(y));

                            listaProdutosEstoqueVerificacao.remove(listaProdutosEstoqueVerificacao.get(y));

                            listaProdutosEstoqueVerificacao.add(new ProdutoEstoque(guardaProdutoEstoque.get(0).getProduto(),
                                                            guardaProdutoEstoque.get(0).getQuantidade() - quantidadeCalculada,
                                                                            guardaProdutoEstoque.get(0).getUnidadeMedida()));
                        }
                    }
                }
            }
        }
        return 0;
    }
}
