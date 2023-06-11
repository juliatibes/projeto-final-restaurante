package repository;

import model.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ReceitaDAO {

    static List<Receita> listaReceita = new ArrayList<>();

    public ReceitaDAO() {
        inputReceita();
    }

    public static void inputReceita() {
        if (ReceitaDAO.listaReceita.isEmpty()) {
            Receita receita1 = new Receita(10, "Massa Carbonara", ReceitaClasseEnum.MASSA, BigDecimal.valueOf(25.00));
            receita1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(0), 200.0, UnidadeMedidaEnum.GRAMA));
            receita1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(1), 50.0, UnidadeMedidaEnum.GRAMA));
            receita1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(3), 30.0, UnidadeMedidaEnum.GRAMA));
            receita1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(4), 2.0, UnidadeMedidaEnum.UNIDADE));
            receita1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(5), 100.0, UnidadeMedidaEnum.MILILITRO));
            listaReceita.add(receita1);

            Receita receita2 = new Receita(11, "TesteEntrada", ReceitaClasseEnum.ENTRADA, BigDecimal.valueOf(15.00));
            receita2.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(0), 200.0, UnidadeMedidaEnum.GRAMA));
            listaReceita.add(receita2);

            Receita receita3 = new Receita(12, "TesteRisoto", ReceitaClasseEnum.RISOTO, BigDecimal.valueOf(15.00));
            receita3.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(0), 200.0, UnidadeMedidaEnum.GRAMA));
            listaReceita.add(receita3);

            Receita receita4 = new Receita(13, "TesteCarne", ReceitaClasseEnum.CARNE, BigDecimal.valueOf(50.00));
            receita4.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(0), 200.0, UnidadeMedidaEnum.GRAMA));
            listaReceita.add(receita4);

            Receita receita5 = new Receita(14, "TesteSobremesa", ReceitaClasseEnum.SOBREMESA, BigDecimal.valueOf(15.00));
            receita5.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(0), 200.0, UnidadeMedidaEnum.GRAMA));
            listaReceita.add(receita5);
        }

    }

    public static void salvarNovaReceita(Receita receita) {
        listaReceita.add(receita);
    }

    public static Integer editarReceitaId(Integer posicaoReceita, Integer id) {
        listaReceita.get(posicaoReceita).setId(id);
        return JOptionPane.showConfirmDialog(null, "O ID da(o) " + listaReceita.get(posicaoReceita).getNome() + " foi editado com sucesso!",
                "Editar Receita", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
    }

    public static Integer editarReceitaNome(Integer posicaoReceita, String nome) {
        String nomeReceita = listaReceita.get(posicaoReceita).getNome();
        listaReceita.get(posicaoReceita).setNome(nome);
        return JOptionPane.showConfirmDialog(null, "O nome da(o) " + nomeReceita + " foi editado com sucesso!",
                "Editar Receita", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
    }

    public static Integer editarReceitaClasse(Integer posicaoReceita, ReceitaClasseEnum receitaClasseEnum) {
        String nomeReceita = listaReceita.get(posicaoReceita).getNome();
        listaReceita.get(posicaoReceita).setReceitaClasse(receitaClasseEnum);
        return JOptionPane.showConfirmDialog(null, "A classe da(o) " + nomeReceita + " foi editada com sucesso!",
                "Editar Receita", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
    }

    public static Integer editarReceitaValorCusto(Integer posicaoReceita, BigDecimal valorCusto) {
        String nomeReceita = listaReceita.get(posicaoReceita).getNome();
        listaReceita.get(posicaoReceita).setValorCusto(valorCusto);
        listaReceita.get(posicaoReceita).setValorVenda(valorCusto.add(valorCusto.multiply(
                BigDecimal.valueOf(100).divide(BigDecimal.valueOf(100)))));
        return JOptionPane.showConfirmDialog(null, "O valor de custo da(o) " + nomeReceita + " foi editado com sucesso!",
                "Editar Receita", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
    }

    public static Integer editarReceitaIngrediente(Integer posicaoReceita, Integer posicaoIngrediente, Produto produto) {
        String nomeReceitaIngrediente = listaReceita.get(posicaoReceita).getListaIngredientes().get(posicaoIngrediente).getProduto().getNome();
        listaReceita.get(posicaoReceita).getListaIngredientes().get(posicaoIngrediente).setProduto(produto);

        return JOptionPane.showConfirmDialog(null, "O ingrediente foi editado com sucesso!",
                "Editar Receita Ingrediente", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
    }

    public static Integer editarReceitaIngredienteQtd(Integer posicaoReceita, Integer posicaoIngrediente, Double novaQuantidade) {
        String nomeReceitaIngrediente = listaReceita.get(posicaoReceita).getListaIngredientes().get(posicaoIngrediente).getProduto().getNome();
        listaReceita.get(posicaoReceita).getListaIngredientes().get(posicaoIngrediente).setQuantidade(novaQuantidade);

        return JOptionPane.showConfirmDialog(null, "A quantidade do(a) " +
                        nomeReceitaIngrediente + " foi editada com sucesso!",
                "Editar Receita Ingrediente", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
    }

    public static Integer editarReceitaIngredienteUM(Integer posicaoReceita, Integer posicaoIngrediente, UnidadeMedidaEnum unidadeMedidaEnum) {
        String nomeReceitaIngrediente = listaReceita.get(posicaoReceita).getListaIngredientes().get(posicaoIngrediente).getProduto().getNome();
        listaReceita.get(posicaoReceita).getListaIngredientes().get(posicaoIngrediente).setUnidadeMedida(unidadeMedidaEnum);

        return JOptionPane.showConfirmDialog(null, "A Unidade de medida do(a) " +
                        nomeReceitaIngrediente + " foi editada com sucesso!",
                "Editar Receita Ingrediente", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
    }

    public static List<Receita> buscaTodos() {
        return listaReceita;
    }

    public static Integer removerReceita(Integer posicaoReceita) {
        listaReceita.remove(posicaoReceita);
        return JOptionPane.showConfirmDialog(null, "Receita excluida com sucesso!",
                "Remover Receita", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null);
    }

    public static Integer buscaPosicaoReceita(String nome) {
        Integer posicaoReceita = 0;
        Integer posicaoReceitaFinal = 0;

        for (Receita receita : listaReceita) {
            if (receita.getNome().contains(nome)) {
                posicaoReceitaFinal = posicaoReceita;
            }
            posicaoReceita = +1;
        }
        return posicaoReceitaFinal;
    }

    public static Object[] findreceitasInArray() {
        List<Receita> receitas = buscaTodos();
        List<String> receitasNomes = new ArrayList<>();

        for (Receita receita : receitas) {
            receitasNomes.add(receita.getNome());
        }
        return receitasNomes.toArray();
    }

    public static Object[] findReceitasIngredientesInArray(String nomeReceita) {
        List<Receita> receitas = buscaTodos();
        List<String> receitasIngredientesNomes = new ArrayList<>();
        Integer x = 0;
        for (Receita receita : receitas) {
            if (receita.getNome().contains(nomeReceita)) {
                for (ReceitaIngrediente receitaIngrediente : receita.getListaIngredientes()) {

                    receitasIngredientesNomes.add(receita.getListaIngredientes().get(x).getProduto().getNome());
                    x = x + 1;
                }
                x = 0;
            }
        }
        return receitasIngredientesNomes.toArray();
    }

    public static Integer buscaPosicaoReceitaIngrediente(Integer posicaoReceita, String nomeIngrediente) {
        Integer posicaoIngrediente = 0;
        Integer posicaoIngredienteFinal = 0;
        for (Receita receita : listaReceita) {
            if (receita.getNome().equals(listaReceita.get(posicaoReceita).getNome())) {
                for (ReceitaIngrediente receitaIngrediente : receita.getListaIngredientes()) {
                    if (receitaIngrediente.getProduto().getNome().contains(nomeIngrediente)) {
                        posicaoIngredienteFinal = posicaoIngrediente;
                    }
                    posicaoIngrediente = posicaoIngrediente + 1;
                }
            }
        }
        return posicaoIngredienteFinal;
    }

    public static Integer aiID() {
        Integer id = listaReceita.size() + 1;
        return id;
    }

}

