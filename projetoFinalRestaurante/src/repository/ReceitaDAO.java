package repository;

import model.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ReceitaDAO {
    static List<Receita> listaReceita = new ArrayList<>();


    public static void inputReceita(){
       Receita receita1 = new Receita(10,"Massa Carbonara",ReceitaClasseEnum.MASSA);
       receita1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(0),200.0,UnidadeMedidaEnum.GRAMA ));
       receita1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(1),50.0,UnidadeMedidaEnum.GRAMA ));
        receita1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(3),30.0,UnidadeMedidaEnum.GRAMA ));
        receita1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(4),2.0,UnidadeMedidaEnum.UNIDADE ));
        receita1.adicionarIngrediente(new ReceitaIngrediente(ProdutoDAO.listaProdutos.get(5),100.0,UnidadeMedidaEnum.MILILITRO ));
       listaReceita.add(receita1);

    }

    public static void salvarNovaReceita(Receita receita) {
        listaReceita.add(receita);}

    public  static List<Receita> buscaTodos() {
        return listaReceita;
    }

    public static Integer removerReceita(Receita receita){
        listaReceita.remove(receita);
        return JOptionPane.showConfirmDialog(null,"Receita excluida com sucesso!",
                "Remover Receita",JOptionPane.DEFAULT_OPTION,JOptionPane.DEFAULT_OPTION,null);
    }

    public static List<Receita> buscarPorNome(String nome) {
        List<Receita> receitasFiltradas = new ArrayList<>();
        for (Receita receita : listaReceita) {
            if (receita.getNome().contains(nome))  {
                receitasFiltradas.add(receita);
            }
        }
        return receitasFiltradas;
    }

    public static Object[] findreceitasInArray() {
        List<Receita> receitas = buscaTodos();
        List<String> receitasNomes = new ArrayList<>();

        for (Receita receita : receitas) {
            receitasNomes.add(receita.getNome());
        }
        return receitasNomes.toArray();
    }


    }

