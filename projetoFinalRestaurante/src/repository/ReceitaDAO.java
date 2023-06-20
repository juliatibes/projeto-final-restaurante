package repository;

import model.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ReceitaDAO implements InterfaceAutoIncrement{

    static List<Receita> listaReceita = new ArrayList<>();

    public ReceitaDAO() {
        inputReceita();
    }

    public static void inputReceita() {
        if (ReceitaDAO.listaReceita.isEmpty()) {
            Receita massa1 = new Receita(1, "Massa Carbonara", ReceitaClasseEnum.MASSA, BigDecimal.valueOf(21.0));
            massa1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(0), 200.0, UnidadeMedidaEnum.GRAMA)); // massa
            massa1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(1), 50.0, UnidadeMedidaEnum.GRAMA)); // bacon
            massa1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(3), 30.0, UnidadeMedidaEnum.GRAMA)); // cebolinha verde
            massa1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(4), 2.0, UnidadeMedidaEnum.UNIDADE)); // ovo
            massa1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(5), 100.0, UnidadeMedidaEnum.MILILITRO)); // creme de leite
            listaReceita.add(massa1);

            Receita massa2 = new Receita(7, "Gnocchi Quatro Queijos", ReceitaClasseEnum.MASSA, BigDecimal.valueOf(24));
            massa2.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(14), 200.0, UnidadeMedidaEnum.GRAMA)); // gnhochi
            massa2.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(15), 50.0, UnidadeMedidaEnum.GRAMA)); //mussarela
            massa2.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(16), 50.0, UnidadeMedidaEnum.GRAMA)); //parmesão
            massa2.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(17), 50.0, UnidadeMedidaEnum.UNIDADE)); //provolone
            massa2.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(18), 50.0, UnidadeMedidaEnum.UNIDADE)); //gorgonzola
            massa2.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(5), 100.0, UnidadeMedidaEnum.MILILITRO)); // creme de leite
            listaReceita.add(massa2);



            Receita entrada1 = new Receita(2, "Batata Frita", ReceitaClasseEnum.ENTRADA, BigDecimal.valueOf(10.00));
            entrada1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(12), 200.0, UnidadeMedidaEnum.GRAMA)); // batata
            listaReceita.add(entrada1);

            Receita entrada2 = new Receita(6, "Camarão Empanado Frito", ReceitaClasseEnum.ENTRADA, BigDecimal.valueOf(17.00));
            entrada2.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(13), 200.0, UnidadeMedidaEnum.GRAMA)); // camrao
            listaReceita.add(entrada2);


            Receita risoto1 = new Receita(3, "Risoto Pescatore", ReceitaClasseEnum.RISOTO, BigDecimal.valueOf(25.00));
            risoto1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(19), 200.0, UnidadeMedidaEnum.GRAMA)); // arroz
            risoto1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(20), 50.0, UnidadeMedidaEnum.GRAMA));  //Molho Tomate
            risoto1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(21), 50.0, UnidadeMedidaEnum.GRAMA));  //Tomate
            risoto1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(22), 50.0, UnidadeMedidaEnum.GRAMA));  //Lula
            risoto1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(23), 20.0, UnidadeMedidaEnum.GRAMA));  //Marisco
            risoto1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(24), 20.0, UnidadeMedidaEnum.GRAMA));  //Siri
            risoto1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(13), 50.0, UnidadeMedidaEnum.GRAMA));  //camarao
            listaReceita.add(risoto1);

            Receita carne1 = new Receita(4, "Picanha gorgonzola e massa", ReceitaClasseEnum.CARNE, BigDecimal.valueOf(30.0));
            carne1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(25), 250.0, UnidadeMedidaEnum.GRAMA)); // picanha
            carne1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(5), 50.0, UnidadeMedidaEnum.GRAMA)); // creme de leite
            carne1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(26), 20.0, UnidadeMedidaEnum.GRAMA)); // rucula
            carne1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(18), 50.0, UnidadeMedidaEnum.GRAMA)); // gorgonzola
            carne1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(0), 200.0, UnidadeMedidaEnum.GRAMA)); // massa
            carne1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(27), 50.0, UnidadeMedidaEnum.GRAMA)); // manteiga
            listaReceita.add(carne1);

            Receita sobremesa1 = new Receita(5, "Piadina de Nutella", ReceitaClasseEnum.SOBREMESA, BigDecimal.valueOf(10.0));
            sobremesa1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(28), 1.0, UnidadeMedidaEnum.GRAMA)); // piadina
            sobremesa1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(29), 100.0, UnidadeMedidaEnum.GRAMA)); // nutella
            sobremesa1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(30), 50.0, UnidadeMedidaEnum.GRAMA)); // sorvete creme
            listaReceita.add(sobremesa1);
        }

    }

    public static void salvarNovaReceita(Receita receita) {
        ReceitaDAO receitaDAO = new ReceitaDAO();
        receita.setId(receitaDAO.geraID());
        listaReceita.add(receita);
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
            posicaoReceita ++;
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

    @Override
    public Integer geraID() {
        Integer id = listaReceita.size() + 1;
        return id;
    }

    public static List<Receita> buscarPorNome(String nome) {
        List<Receita> receitasFiltradas = new ArrayList<>();
        for (Receita receita : listaReceita) {
            if (receita.getNome().contains(nome)) {
                receitasFiltradas.add(receita);
            }
        }
        return receitasFiltradas;
    }
}

