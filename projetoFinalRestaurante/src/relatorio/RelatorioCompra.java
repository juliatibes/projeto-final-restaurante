package relatorio;

import model.Compra;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class RelatorioCompra extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    public static final int INDEX_DATACOMPRA = 0;
    public static final int INDEX_PRODUTO = 1;
    public static final int INDEX_QUANTIDADE = 2;
    public static final int INDEX_UNIDADEMEDIDA = 3;
    public static final int INDEX_ESCONDIDO = 4;

    protected String[] nomeColunas;
    protected Vector<Compra> vetorDados;

    public RelatorioCompra(String[] columnNames, Vector<Compra> vetorDados) {
        this.nomeColunas = columnNames;
        this.vetorDados = vetorDados;
    }

    @Override
    public String getColumnName(int column) {
        return nomeColunas[column];
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        if (coluna == INDEX_ESCONDIDO) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Compra registroCompra = (Compra) vetorDados.get(linha);
        switch (coluna) {
            case INDEX_DATACOMPRA:
                return registroCompra.getDataCompra();
            case INDEX_PRODUTO:
                return registroCompra.getProduto().getNome();
            case INDEX_QUANTIDADE:
                return registroCompra.getQuantidade();
            case INDEX_UNIDADEMEDIDA:
                return registroCompra.getUnidadeMedida();
            default:
                return new Object();
        }
    }

    @Override
    public int getRowCount() {
        return vetorDados.size();
    }

    @Override
    public int getColumnCount() {
        return nomeColunas.length;
    }




}
