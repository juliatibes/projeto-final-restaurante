import model.Receita;
import relatorio.RelatorioReceita;

import javax.swing.*;
import javax.swing.table.TableColumn;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Vector;


public class RelatorioReceitaForm extends JPanel {

    private static final long serialVersionUID = 1L;

    public static final String[] nomeColunas =
            {"Nome", "Custo", "Valor de venda", "Lucro obtido", ""};

    protected JTable table;
    protected JScrollPane scroller;
    protected RelatorioReceita tabela;

    public RelatorioReceitaForm(Vector<Receita> vetorDados) {
        iniciarComponentes(vetorDados);
    }


    public void iniciarComponentes(Vector<Receita> vetorDados) {
        tabela = new RelatorioReceita(nomeColunas, vetorDados);
        table = new JTable();
        table.setModel(tabela);
        table.setSurrendersFocusOnKeystroke(true);
        scroller = new javax.swing.JScrollPane(table);
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));

        TableColumn colunaEscondida = table.getColumnModel().getColumn(RelatorioReceita.INDEX_ESCONDIDO);
        colunaEscondida.setMinWidth(2);
        colunaEscondida.setPreferredWidth(2);
        colunaEscondida.setMaxWidth(2);
        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);
    }

        public static void emitirRelatorio (List < Receita > receitas) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                JFrame frame = new JFrame("Relatorio de receitas");

                frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent evt) {
                        frame.setVisible(false);
                        Main.chamaMenuRelatorios();
                    }
                });
                Vector<Receita> vetorDados = new Vector<Receita>();
                for (Receita receita : receitas) {
                    vetorDados.add(receita);
                }

                frame.getContentPane().add(new RelatorioReceitaForm(vetorDados));
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }