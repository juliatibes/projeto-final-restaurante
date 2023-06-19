import model.Produto;
import relatorio.RelatorioBebida;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;

public class RelatorioBebidaForm extends JPanel {

    private static final long serialVersionUID = 1L;

    public static final String[] nomeColunas =
            {"Nome", "Custo (R$)", "Valor de venda (R$)", "Lucro obtido (R$)", ""};

    protected JTable table;
    protected JScrollPane scroller;
    protected RelatorioBebida tabela;

    public RelatorioBebidaForm(Vector<Produto> vetorDados) {
        iniciarComponentes(vetorDados);
    }


    public void iniciarComponentes(Vector<Produto> vetorDados) {
        tabela = new RelatorioBebida(nomeColunas, vetorDados);
        table = new JTable();
        table.setModel(tabela);
        table.setSurrendersFocusOnKeystroke(true);
        scroller = new javax.swing.JScrollPane(table);
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));

        TableColumn colunaEscondida = table.getColumnModel().getColumn(RelatorioBebida.INDEX_ESCONDIDO);
        colunaEscondida.setMinWidth(2);
        colunaEscondida.setPreferredWidth(2);
        colunaEscondida.setMaxWidth(2);
        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);
    }

    public static void emitirRelatorio (List<Produto> produtos) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            JFrame frame = new JFrame("Relatório de bebidas");

            frame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent evt) {
                    frame.setVisible(false);
                    Main.chamaMenuRelatorios();
                }
            });
            Vector<Produto> vetorDados = new Vector<Produto>();
            for (Produto produto : produtos) {
                vetorDados.add(produto);
            }

            frame.getContentPane().add(new RelatorioBebidaForm(vetorDados));
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}