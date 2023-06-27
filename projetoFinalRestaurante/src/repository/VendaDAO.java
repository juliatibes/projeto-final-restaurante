package repository;

import model.*;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO implements InterfaceAutoIncrement{
    static List<Venda> listaVenda = new ArrayList<>();

//

    public static void salvarListaVenda(Venda venda) {
        VendaDAO vendaDAO = new VendaDAO();
        venda.setId(vendaDAO.geraID());
        listaVenda.add(venda);
    }

    public static List<Venda> buscarTodos() {
        return listaVenda;
    }

    @Override
    public Integer geraID() {
        Integer id = listaVenda.size() + 1;
        return id;
    }

    public static Object[] findprodutosInArrayProdutoBebida() {
        List<Produto> produtosBebida = ProdutoDAO.buscaTodos();
        List<String> produtosNomesBebida = new ArrayList<>();

        for (Produto produto : produtosBebida) {
            if (produto.getTipoProduto() == ProdutoEnum.BEBIDA) {
                produtosNomesBebida.add(produto.getNome());
            }
        }
        return produtosNomesBebida.toArray();
    }

    public static List<Produto> buscarPorNomeBebida(String nome) {
        List<Produto> produtosFiltradasBebida = new ArrayList<>();
        for (Produto produto : ProdutoDAO.listaProdutos) {
            if (produto.getNome().contains(nome)) {
                produtosFiltradasBebida.add(produto);
            }
        }
        return produtosFiltradasBebida;
    }
//BEBIDA É DIFERENTE


    //entrada
    public static Object[] findreceitaInArrayReceitaEntrada() {
        List<Receita> receitaEntrada = ReceitaDAO.buscaTodos();
        List<String> receitaNomesEntrada = new ArrayList<>();

        for (Receita receita : receitaEntrada) {
            if (receita.getReceitaClasse() == ReceitaClasseEnum.ENTRADA) {
                receitaNomesEntrada.add(receita.getNome());
            }
        }
        return receitaNomesEntrada.toArray();
    }

    public static List<Receita> buscarPorNomeReceita(String nome) {
        List<Receita> receitasFiltradasReceita = new ArrayList<>();
        for (Receita receita : ReceitaDAO.listaReceita) {
            if (receita.getNome().contains(nome)) {
                receitasFiltradasReceita.add(receita);
            }
        }
        return receitasFiltradasReceita;
    }



    //massa
    public static Object[] findreceitaInArrayReceitaMassa() {
        List<Receita> receitaMassa = ReceitaDAO.buscaTodos();
        List<String> receitaNomesMassa = new ArrayList<>();

        for (Receita receita : receitaMassa) {
            if (receita.getReceitaClasse() == ReceitaClasseEnum.MASSA) {
                receitaNomesMassa.add(receita.getNome());
            }
        }
        return receitaNomesMassa.toArray();
    }

    //risoto
    public static Object[] findreceitaInArrayReceitaRisoto() {
        List<Receita> receitaRisoto = ReceitaDAO.buscaTodos();
        List<String> receitaNomesRisoto = new ArrayList<>();

        for (Receita receita : receitaRisoto) {
            if (receita.getReceitaClasse() == ReceitaClasseEnum.RISOTO) {
                receitaNomesRisoto.add(receita.getNome());
            }
        }
        return receitaNomesRisoto.toArray();
    }

    //carne
    public static Object[] findreceitaInArrayReceitaCarne() {
        List<Receita> receitaCarne = ReceitaDAO.buscaTodos();
        List<String> receitaNomesCarne = new ArrayList<>();

        for (Receita receita : receitaCarne) {
            if (receita.getReceitaClasse() == ReceitaClasseEnum.CARNE) {
                receitaNomesCarne.add(receita.getNome());
            }
        }
        return receitaNomesCarne.toArray();
    }

    //sobremesa
    public static Object[] findreceitaInArrayReceitaSobremesa() {
        List<Receita> receitaSobremesa = ReceitaDAO.buscaTodos();
        List<String> receitaNomesSobremesa = new ArrayList<>();

        for (Receita receita : receitaSobremesa) {
            if (receita.getReceitaClasse() == ReceitaClasseEnum.SOBREMESA) {
                receitaNomesSobremesa.add(receita.getNome());
            }
        }
        return receitaNomesSobremesa.toArray();
    }

    public static Object[] findreceitaInArrayReceitaCarinho(List<VendaPedido> vendalista, VendaPedido vendaPedidoEscolhida,BigDecimal valorCarinho) {
        List<VendaPedido> vendaCarinho = vendalista;
        List<String> vendaCarinhoNomes = new ArrayList<>();

        vendaCarinhoNomes.add("QUANTIDADE                      DESCRIÇÃO");
        for (VendaPedido vendaPedido : vendaCarinho) {

            if (vendaPedido.getReceita() != null) {
                vendaCarinhoNomes.add("          "+vendaPedido.getQuantidade()+
                        "                           "+vendaPedido.getReceita().getNome()+
                        " (" + vendaPedido.getObservacao() + ")");
            }
            if (vendaPedido.getProdutoBebida() != null) {
                vendaCarinhoNomes.add("          "+vendaPedido.getQuantidade()+
                        "                           "+vendaPedido.getProdutoBebida().getNome()+
                        " (" + vendaPedido.getObservacao() + ")");
            }
        }
        if (vendaPedidoEscolhida != null) {

            if (vendaPedidoEscolhida.getReceita() != null) {
                vendaCarinhoNomes.add("          " + vendaPedidoEscolhida.getQuantidade()+
                        "                           " + vendaPedidoEscolhida.getReceita().getNome()+
                        " (" + vendaPedidoEscolhida.getObservacao() + ")");
                vendaCarinhoNomes.add("\nVALOR TOTAL: R$" + valorCarinho.add(BigDecimal.valueOf(vendaPedidoEscolhida.getQuantidade()).
                        multiply(vendaPedidoEscolhida.getReceita().getValorVenda())));
            }
            if (vendaPedidoEscolhida.getProdutoBebida() != null){
                vendaCarinhoNomes.add("          " + vendaPedidoEscolhida.getQuantidade() +
                        "                           "+ vendaPedidoEscolhida.getProdutoBebida().getNome()+
                        " (" + vendaPedidoEscolhida.getObservacao() + ")");
                vendaCarinhoNomes.add("\nVALOR TOTAL: R$" + valorCarinho.add(BigDecimal.valueOf(vendaPedidoEscolhida.getQuantidade()).
                        multiply(vendaPedidoEscolhida.getProdutoBebida().getValorVendaProduto())));
            }
        }
        else {
            vendaCarinhoNomes.add("\nVALOR TOTAL: R$" + valorCarinho);
        }
        return vendaCarinhoNomes.toArray();
    }

    public static Object[] geraListaOfertaDoDia(){
        List<ReceitaOfertaDia> listaOfertaReceita = ReceitaOfertaDoDIaDAO.buscarTodos();
        List<BebidaOfertaDia> listaOfertaBebida = BebidaOfertaDoDIaDAO.buscarTodos();
        List<String> listaOfertaDoDia = new ArrayList<>();

        listaOfertaDoDia.add("DESCONTO            DESCRIÇÃO");
        if (listaOfertaReceita != null) {
            for (ReceitaOfertaDia receitaOfertaDia : listaOfertaReceita) {
                listaOfertaDoDia.add("    "+receitaOfertaDia.getDesconto() + "%                  " + receitaOfertaDia.getReceita().getNome());
            }
        }
        if (listaOfertaBebida != null) {
            for (BebidaOfertaDia bebidaOfertaDia : listaOfertaBebida) {
                listaOfertaDoDia.add("    "+bebidaOfertaDia.getDesconto() + "%                  " + bebidaOfertaDia.getProduto().getNome());
            }
        }
        return listaOfertaDoDia.toArray();
    }
    // sao metodos pra relatorio

    public static BigDecimal totalVendas() {
        BigDecimal valorTotalVendaReceita = BigDecimal.ZERO;
        BigDecimal valorTotalVendaBebida = BigDecimal.ZERO;

        for (int posicaoVenda = 0; posicaoVenda < listaVenda.size(); posicaoVenda++) {

            for (int posicaoVendaPedidoR = 0; posicaoVendaPedidoR < listaVenda.get(posicaoVenda).getListaVendaPedido().size();
                 posicaoVendaPedidoR++) {

                if(listaVenda.get(posicaoVenda).getListaVendaPedido().get(posicaoVendaPedidoR).getProdutoBebida() != null) {

                    valorTotalVendaBebida = valorTotalVendaBebida.add
                            (listaVenda.get(posicaoVenda).getListaVendaPedido().get(posicaoVendaPedidoR).getProdutoBebida().getValorVendaProduto()
                                    .multiply(BigDecimal.valueOf(listaVenda.get(posicaoVenda).getListaVendaPedido().get(posicaoVendaPedidoR).getQuantidade())));
                }else{

              valorTotalVendaReceita = valorTotalVendaReceita.add
                   (listaVenda.get(posicaoVenda).getListaVendaPedido().get(posicaoVendaPedidoR).getReceita().getValorVenda()
                           .multiply(BigDecimal.valueOf(listaVenda.get(posicaoVenda).getListaVendaPedido().get(posicaoVendaPedidoR).getQuantidade())));
                }
            }
        }
        return valorTotalVendaReceita.add(valorTotalVendaBebida);
    }


    public static BigDecimal totalLucro() {
        BigDecimal valorTotalLucroReceita = BigDecimal.ZERO;
        BigDecimal valorTotalLucroBebida = BigDecimal.ZERO;

        for (int posicaoVenda = 0; posicaoVenda < listaVenda.size(); posicaoVenda++) {

            for (int posicaoVendaPedidoR = 0; posicaoVendaPedidoR < listaVenda.get(posicaoVenda).getListaVendaPedido().size();
                 posicaoVendaPedidoR++) {

                if(listaVenda.get(posicaoVenda).getListaVendaPedido().get(posicaoVendaPedidoR).getProdutoBebida() != null) {

                    valorTotalLucroBebida = valorTotalLucroBebida.add
                            (listaVenda.get(posicaoVenda).getListaVendaPedido().get(posicaoVendaPedidoR).getProdutoBebida().getValorCustoProduto()
                                    .multiply(BigDecimal.valueOf(listaVenda.get(posicaoVenda).getListaVendaPedido().get(posicaoVendaPedidoR).getQuantidade())));
                }else{

                    valorTotalLucroReceita = valorTotalLucroReceita.add
                            (listaVenda.get(posicaoVenda).getListaVendaPedido().get(posicaoVendaPedidoR).getReceita().getValorCusto()
                                    .multiply(BigDecimal.valueOf(listaVenda.get(posicaoVenda).getListaVendaPedido().get(posicaoVendaPedidoR).getQuantidade())));
                }
            }
        }
        return totalVendas().subtract(valorTotalLucroReceita.add(valorTotalLucroBebida));
    }


    public static Integer totalQuantidadeItens() {
        Integer quantidadeItemTotal = 0;

        for (int posicaoVenda = 0; posicaoVenda < listaVenda.size(); posicaoVenda++) {

            for (int posicaoVendaPedidoR = 0; posicaoVendaPedidoR < listaVenda.get(posicaoVenda).getListaVendaPedido().size();
                 posicaoVendaPedidoR++) {

                quantidadeItemTotal = quantidadeItemTotal +
                        listaVenda.get(posicaoVenda).getListaVendaPedido().get(posicaoVendaPedidoR).getQuantidade();
            }
        }
        return quantidadeItemTotal;

    }
}
