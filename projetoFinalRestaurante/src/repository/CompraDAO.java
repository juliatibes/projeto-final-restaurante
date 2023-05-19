package repository;

import model.Compra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {

    static List<Compra> listaCompras = new ArrayList<>();


    public static void salvarNovaCompra(Compra produto) {
        listaCompras.add(produto);
    }
    public static List<Compra> listarCompras() {
        return listaCompras;
    }
    public static void Cancelar(){}

    public static void FinalizarCompra(){

    }
}
