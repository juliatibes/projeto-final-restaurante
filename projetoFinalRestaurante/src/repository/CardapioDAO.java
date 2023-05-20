package repository;

import model.Cardapio;

import java.util.ArrayList;
import java.util.List;


public class CardapioDAO {
    static List<Cardapio> listaCardapio = new ArrayList<>();


    public static void salvarNovaReceita(Cardapio produto) { listaCardapio.add(produto);}

    public static List<Cardapio> listarCompras() {return listaCardapio;}
}
