package repository;

import model.Receita;

import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO {
    static List<Receita> listaReceita = new ArrayList<>();


    public static void salvarNovaReceita(Receita produto) { listaReceita.add(produto);}

        public static List<Receita> listarCompras() {return listaReceita;}
    }

