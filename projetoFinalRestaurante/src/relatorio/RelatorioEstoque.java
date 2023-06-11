package relatorio;


import model.ProdutoEstoque;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class RelatorioEstoque extends AbstractTableModel {

    private static final long serialVersionUID = 1L;

    public static final int INDEX_PRODUTO = 0;
    public static final int INDEX_QUANTIDADE = 1;
    public static final int INDEX_UNIDADEMEDIDA = 2;
    public static final int INDEX_ESCONDIDO = 3;


    protected String[] nomeColunas;
    protected Vector<ProdutoEstoque> vetorDados;

    public RelatorioEstoque(String[] columnNames, Vector<ProdutoEstoque> vetorDados) {
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
        ProdutoEstoque registroPessoa = (ProdutoEstoque) vetorDados.get(linha);
        switch (coluna) {
            case INDEX_PRODUTO:
                return registroPessoa.getProduto().getNome();
            case INDEX_QUANTIDADE:
                return registroPessoa.getQuantidade();
            case INDEX_UNIDADEMEDIDA:
                return registroPessoa.getUnidadeMedida();
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
