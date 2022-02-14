package br.edu.ifc.videira.controllers.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.edu.ifc.videira.DAOs.ProcedimentoDao;
import br.edu.ifc.videira.DAOs.TratamentoDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class TratamentoView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfPrecoTotal;
	public static JTextField tfIdTratamento;
	private TratamentoDao trDao = new TratamentoDao();
	private ProcedimentoDao pDao = new ProcedimentoDao();
	private List<Object> tratamentos = new ArrayList<Object>();
	private JTextField tfIdProcedimento;
	private JTextField tfPrecoProcedimento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TratamentoView frame = new TratamentoView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TratamentoView() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(TratamentoView.class.getResource("/br/edu/ifc/videira/imgs/500x500.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				atualizarTabela(Integer.parseInt(tfIdTratamento.getText()));
				setPreco();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 870, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TratamentoView.class.getResource("/br/edu/ifc/videira/imgs/200x200-t.png")));
		lblNewLabel.setBounds(10, 11, 295, 159);
		contentPane.add(lblNewLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(315, 11, 520, 159);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setCampos();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Procedimento", "Pre\u00E7o", "Data", "Observa\u00E7\u00E3o"
			}
		));
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);

		JLabel lblPreo = new JLabel("Pre\u00E7o total");
		lblPreo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPreo.setBounds(198, 260, 88, 17);
		contentPane.add(lblPreo);

		tfPrecoTotal = new JTextField();
		tfPrecoTotal.setEditable(false);
		tfPrecoTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfPrecoTotal.setBounds(304, 256, 64, 24);
		contentPane.add(tfPrecoTotal);
		tfPrecoTotal.setColumns(10);

		JButton btnFinalizarTratamento = new JButton("Finalizar tratamento");
		btnFinalizarTratamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					trDao.finalizarTratamento(Integer.parseInt(tfIdTratamento.getText()));
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		btnFinalizarTratamento.setFont(new Font("Dialog", Font.BOLD, 14));
		btnFinalizarTratamento.setBounds(462, 360, 193, 31);
		contentPane.add(btnFinalizarTratamento);

		tfIdTratamento = new JTextField();
		tfIdTratamento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tfIdTratamento.setEditable(false);
		tfIdTratamento.setColumns(10);
		tfIdTratamento.setBounds(304, 208, 64, 24);
		contentPane.add(tfIdTratamento);

		JLabel lblIdTratamento = new JLabel("ID Tratamento");
		lblIdTratamento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdTratamento.setBounds(187, 210, 107, 17);
		contentPane.add(lblIdTratamento);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVoltar.setBounds(249, 360, 169, 31);
		contentPane.add(btnVoltar);

		JLabel lblIdProcedimento = new JLabel("ID Procedimento");
		lblIdProcedimento.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblIdProcedimento.setBounds(456, 212, 131, 17);
		contentPane.add(lblIdProcedimento);

		tfIdProcedimento = new JTextField();
		tfIdProcedimento.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfIdProcedimento.setEditable(false);
		tfIdProcedimento.setColumns(10);
		tfIdProcedimento.setBounds(639, 208, 64, 24);
		contentPane.add(tfIdProcedimento);

		JLabel lblPreoDoProcedimento = new JLabel("Pre\u00E7o do procedimento");
		lblPreoDoProcedimento.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblPreoDoProcedimento.setBounds(456, 260, 162, 17);
		contentPane.add(lblPreoDoProcedimento);

		tfPrecoProcedimento = new JTextField();
		tfPrecoProcedimento.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfPrecoProcedimento.setEditable(false);
		tfPrecoProcedimento.setColumns(10);
		tfPrecoProcedimento.setBounds(639, 256, 64, 24);
		contentPane.add(tfPrecoProcedimento);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfIdProcedimento.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Selecione um procedimento");
				} else {
					int decisao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir?");
					if (decisao == JOptionPane.YES_OPTION) {
						try {
							pDao.excluirProcedimento(Integer.parseInt(tfIdProcedimento.getText()));
							atualizarTabela(Integer.parseInt(tfIdTratamento.getText()));
							setPreco();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}
					}
				}
			}
		});
		btnExcluir.setFont(new Font("Dialog", Font.PLAIN, 14));
		btnExcluir.setBounds(740, 208, 88, 27);
		contentPane.add(btnExcluir);
	}

	public void atualizarTabela(int idTratamento) {
		try {
			tratamentos = trDao.buscarTratamento(idTratamento);
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			for (int x = 0; x != tratamentos.size(); x++) {
				model.addRow((Object[]) tratamentos.get(x));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void setPreco() {
		double preco = 0;
		for (int i = 0; i < table.getRowCount(); i++) {
			preco += Double.parseDouble("" + table.getValueAt(i, 2));
		}
		tfPrecoTotal.setText("" + preco);
	}

	public void setCampos() {
		tfIdProcedimento.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)));
		tfPrecoProcedimento.setText(String.valueOf(table.getValueAt(table.getSelectedRow(), 2)));
	}
}
