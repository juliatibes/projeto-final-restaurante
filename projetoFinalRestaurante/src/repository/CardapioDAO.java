package repository;

import model.*;

import java.util.ArrayList;
import java.util.List;


public class CardapioDAO {
    static List<Cardapio> listaCardapio = new ArrayList<>();


    public static void salvarNovaReceita(Cardapio produto) { listaCardapio.add(produto);}

    public static List<Cardapio> buscarTodos() {return listaCardapio;}

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




}


