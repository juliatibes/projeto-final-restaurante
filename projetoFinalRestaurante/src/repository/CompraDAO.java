package repository;

import model.Compra;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {

    static List<Compra> listaCompras = new ArrayList<>();
    static List<Compra> listaComprasRelatorio = new ArrayList<>();

    public static void AdicionarNovaCompra(Compra produto) {
        listaCompras.add(produto);
        listaComprasRelatorio.add(produto); // passivel de alteração para apenas uma lista ( comparar datas)
    }
    public static List<Compra> listarCompras() {
        return listaCompras;
    }
    public static void Cancelar(){}

    public static void FinalizarCompra(){

    }
}
