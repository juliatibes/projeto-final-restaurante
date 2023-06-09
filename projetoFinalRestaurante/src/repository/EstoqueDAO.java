package repository;

import model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDAO {
    static List<Estoque> listaProdutosEstoque = new ArrayList<>();

    public static List<Estoque> buscaTodos() {
            return listaProdutosEstoque;
    }

    public static void salvarProdutoEstoque(Produto produto, Double quantidade, UnidadeMedidaEnum unidadeMedidaEnum) {

        if (unidadeMedidaEnum == UnidadeMedidaEnum.KILO || unidadeMedidaEnum == UnidadeMedidaEnum.LITRO){
            quantidade = quantidade * 1000;
            if (unidadeMedidaEnum == UnidadeMedidaEnum.KILO){
                unidadeMedidaEnum = UnidadeMedidaEnum.GRAMA;
            }
            if (unidadeMedidaEnum == UnidadeMedidaEnum.LITRO){
                unidadeMedidaEnum = UnidadeMedidaEnum.MILILITRO;
            }
        }

        Double quantidadeAtual = 0.00;
        Estoque estoqueEncontrado = null;
        for (Estoque estoque : listaProdutosEstoque) {
            if (estoque.getProduto().getId() == produto.getId()) {
                quantidadeAtual = estoque.getQuantidade();
                estoqueEncontrado = estoque;
            }
        }

        if (estoqueEncontrado != null) {
            listaProdutosEstoque.remove(estoqueEncontrado);
        }

        listaProdutosEstoque.add(new Estoque(produto, quantidade + quantidadeAtual, unidadeMedidaEnum));
    }

    public static Integer verificaDisponibilidade (List<VendaPedido> listaItensCarinho){
        Double quantidadeCalculadaReceita = 0.00;
        List<Estoque> listaProdutosEstoqueVerificacao = new ArrayList<>();


        for (Estoque estoque : listaProdutosEstoque){
             listaProdutosEstoqueVerificacao.add(estoque);
        }


        for ( int z = 0; z < listaItensCarinho.size();z ++) {
            List<Estoque> guardaEstoque = new ArrayList<>();

            if (listaItensCarinho.get(z).getProdutoBebida() != null) {
                for (int y = 0; y < listaProdutosEstoqueVerificacao.size(); y++) {

                    if (listaProdutosEstoqueVerificacao.get(y).getProduto().equals(listaItensCarinho.get(z).getProdutoBebida())) {
                        if (listaProdutosEstoqueVerificacao.get(y).getQuantidade() < listaItensCarinho.get(z).getQuantidade()) {
                            return 1;
                        } else {
                            guardaEstoque.add(listaProdutosEstoqueVerificacao.get(y));

                            listaProdutosEstoqueVerificacao.remove(listaProdutosEstoqueVerificacao.get(y));

                            listaProdutosEstoqueVerificacao.add(new Estoque(guardaEstoque.get(0).getProduto(),
                                    guardaEstoque.get(0).getQuantidade() - listaItensCarinho.get(z).getQuantidade(),
                                    guardaEstoque.get(0).getUnidadeMedida()));
                            guardaEstoque.clear();
                            y = listaProdutosEstoqueVerificacao.size() -1;
                        }
                    }
                }
            }

            if (listaItensCarinho.get(z).getReceita() != null) {
              for (int x = 0; x < listaItensCarinho.get(z).getReceita().getListaIngredientes().size(); x++) {

                  quantidadeCalculadaReceita = listaItensCarinho.get(z).getReceita().getListaIngredientes().get(x).getQuantidade() *
                          listaItensCarinho.get(z).getQuantidade();

                  for (int y = 0; y < listaProdutosEstoqueVerificacao.size(); y++) {

                      if (listaProdutosEstoqueVerificacao.get(y).getProduto().equals
                              (listaItensCarinho.get(z).getReceita().getListaIngredientes().get(x).getProduto())) {
                          if (listaProdutosEstoqueVerificacao.get(y).getQuantidade() < quantidadeCalculadaReceita) {
                              return 1;
                          } else {
                              guardaEstoque.add(listaProdutosEstoqueVerificacao.get(y));

                              listaProdutosEstoqueVerificacao.remove(listaProdutosEstoqueVerificacao.get(y));
                              listaProdutosEstoqueVerificacao.add(new Estoque(guardaEstoque.get(0).getProduto(),
                                      guardaEstoque.get(0).getQuantidade() - quantidadeCalculadaReceita,
                                      guardaEstoque.get(0).getUnidadeMedida()));
                              guardaEstoque.clear();
                              y = listaProdutosEstoqueVerificacao.size() -1;
                          }
                      }
                  }
              }
          }
        }
        return 0;
    }

    public static Integer baixaVendaEstoque (Venda venda){
        Double quantidadeCalculadaReceita = 0.00;

        for (int x = 0 ; x < venda.getListaVendaPedido().size() ; x ++) {

            if (venda.getListaVendaPedido().get(x).getProdutoBebida() != null) {
                for (int y = 0; y < listaProdutosEstoque.size(); y++) {
                    if (listaProdutosEstoque.get(y).getProduto().equals(venda.getListaVendaPedido().get(x).getProdutoBebida())){
                        listaProdutosEstoque.get(y).setQuantidade
                                (listaProdutosEstoque.get(y).getQuantidade() - venda.getListaVendaPedido().get(x).getQuantidade());

                    }
                }
            }

            if (venda.getListaVendaPedido().get(x).getReceita() != null) {
                for (int z = 0; z < venda.getListaVendaPedido().get(x).getReceita().getListaIngredientes().size(); z++) {

                    quantidadeCalculadaReceita = venda.getListaVendaPedido().get(x).getReceita().getListaIngredientes().get(z).getQuantidade() *
                            venda.getListaVendaPedido().get(x).getQuantidade();

                    for (int y = 0; y < listaProdutosEstoque.size(); y++) {
                        if (listaProdutosEstoque.get(y).getProduto().equals
                                (venda.getListaVendaPedido().get(x).getReceita().getListaIngredientes().get(z).getProduto())) {

                            listaProdutosEstoque.get(y).setQuantidade
                                    (listaProdutosEstoque.get(y).getQuantidade() - quantidadeCalculadaReceita);
                        }
                    }
                }
            }
        }

        return JOptionPane.showConfirmDialog(null, "Venda concluída com sucesso!",
                "Venda", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
    }

    public static Object[] verificaEstoqueReceitaPlanejamento (Receita receita, Integer quantidadePlanejada){
        Integer contadorVerificacaoEstoque = 1;
        Double qtdTotalIngrediente = 0.0;
        List<String> listaIngredientesFaltanteNome = new ArrayList<>();
        List<ReceitaIngrediente> listaIngredientesFaltante = new ArrayList<>();

        for (int x = 0 ; x < receita.getListaIngredientes().size(); x ++){

            qtdTotalIngrediente = receita.getListaIngredientes().get(x).getQuantidade() * quantidadePlanejada;

            for (int y = 0; y < listaProdutosEstoque.size(); y ++){
                if (listaProdutosEstoque.get(y).getProduto().equals(receita.getListaIngredientes().get(x).getProduto())){
                    if (listaProdutosEstoque.get(y).getQuantidade() < qtdTotalIngrediente) {
                        listaIngredientesFaltante.add(new ReceitaIngrediente(listaProdutosEstoque.get(y).getProduto(),
                                qtdTotalIngrediente - listaProdutosEstoque.get(y).getQuantidade(), listaProdutosEstoque.get(y).getUnidadeMedida()));

                        contadorVerificacaoEstoque = 1;
                    }
                }
            }
        }
        listaIngredientesFaltanteNome.add("Lista de ingredientes que estão faltando:\n\n");
        listaIngredientesFaltanteNome.add("UNIDADE MEDIDA             QUANTIDADE            INGREDIENTE ");

        for (ReceitaIngrediente receitaIngrediente : listaIngredientesFaltante){
            listaIngredientesFaltanteNome.add("        "+receitaIngrediente.getUnidadeMedida() +
                    "                              " + receitaIngrediente.getQuantidade()+
                    "                    " + receitaIngrediente.getProduto().getNome());
        }

        if (contadorVerificacaoEstoque == 1){
            return listaIngredientesFaltanteNome.toArray();
        } else
            return null;
    }

    public static Object[] verificaEstoqueBebidaPlanejamento (Produto produto, Integer quantidadePlanejada){
        Integer contadorVerificacaoEstoque = 1;

        List<String> listaBebidaFaltanteNome = new ArrayList<>();
        List<Estoque> listaBebidaFaltante = new ArrayList<>();


            for (int y = 0; y < listaProdutosEstoque.size(); y ++){
                if (listaProdutosEstoque.get(y).getProduto().equals(produto)){
                    if (listaProdutosEstoque.get(y).getQuantidade() < quantidadePlanejada) {
                        listaBebidaFaltante.add(new Estoque(listaProdutosEstoque.get(y).getProduto(),
                                quantidadePlanejada - listaProdutosEstoque.get(y).getQuantidade(), listaProdutosEstoque.get(y).getUnidadeMedida()));

                        contadorVerificacaoEstoque = 1;
                    }
                }
            }

        listaBebidaFaltanteNome.add("Lista de produtos que estão faltando:\n\n");
        listaBebidaFaltanteNome.add("UNIDADE MEDIDA             QUANTIDADE            INGREDIENTE ");

        for (Estoque estoque : listaBebidaFaltante){
            listaBebidaFaltanteNome.add("        "+ estoque.getUnidadeMedida() +
                    "                              " + estoque.getQuantidade()+
                    "                    " + estoque.getProduto().getNome());
        }

        if (contadorVerificacaoEstoque == 1){
            return listaBebidaFaltanteNome.toArray();
        } else
            return null;
    }

    public static void removerProdutoEstoque (Produto produto){

        for (int x = 0 ; x < listaProdutosEstoque.size() ; x ++){
            if (listaProdutosEstoque.get(x).getProduto() == produto){
                listaProdutosEstoque.remove(listaProdutosEstoque.get(x));
            }
        }
    }
}
