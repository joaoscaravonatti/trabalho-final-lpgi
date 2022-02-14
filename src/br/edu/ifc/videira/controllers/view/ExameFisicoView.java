package br.edu.ifc.videira.controllers.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import br.edu.ifc.videira.DAOs.ExameFisicoDao;
import br.edu.ifc.videira.beans.ExameFisico;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ExameFisicoView extends JFrame {

	private JPanel contentPane;
	public static JTextField tfId;
	private JTextField tfFormatoUnhas;
	private JTextField tfObs1;
	private JTextField tfObs2;
	private JTable table;
	List<Object> problemas = new ArrayList<Object>();
	private ExameFisicoDao efDao = new ExameFisicoDao();
	private ExameFisico ef = new ExameFisico();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExameFisicoView frame = new ExameFisicoView();
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
	public ExameFisicoView() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				setCampos();
			}
		});
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ExameFisicoView.class.getResource("/br/edu/ifc/videira/imgs/800x800.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 466);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ExameFisicoView.class.getResource("/br/edu/ifc/videira/imgs/200x200-t.png")));
		label.setBounds(12, 12, 330, 167);
		contentPane.add(label);

		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfId.setBounds(360, 12, 49, 21);
		contentPane.add(tfId);
		tfId.setColumns(10);

		JLabel lblFormatoDasUnhas = new JLabel("Formato das unhas");
		lblFormatoDasUnhas.setFont(new Font("Dialog", Font.BOLD, 14));
		lblFormatoDasUnhas.setBounds(360, 70, 161, 17);
		contentPane.add(lblFormatoDasUnhas);

		tfFormatoUnhas = new JTextField();
		tfFormatoUnhas.setEditable(false);
		tfFormatoUnhas.setBounds(527, 68, 135, 21);
		contentPane.add(tfFormatoUnhas);
		tfFormatoUnhas.setColumns(10);

		JLabel lblObservaes = new JLabel("Observações");
		lblObservaes.setFont(new Font("Dialog", Font.BOLD, 14));
		lblObservaes.setBounds(360, 121, 161, 17);
		contentPane.add(lblObservaes);

		tfObs1 = new JTextField();
		tfObs1.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfObs1.setEditable(false);
		tfObs1.setColumns(10);
		tfObs1.setBounds(488, 119, 245, 21);
		contentPane.add(tfObs1);

		tfObs2 = new JTextField();
		tfObs2.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfObs2.setEditable(false);
		tfObs2.setColumns(10);
		tfObs2.setBounds(488, 158, 245, 21);
		contentPane.add(tfObs2);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVoltar.setBounds(319, 369, 145, 27);
		contentPane.add(btnVoltar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(244, 215, 312, 116);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Problema f\u00EDsico"
			}
		));
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
	}

	public void setCampos() {
		try {
			ef = efDao.buscarExameFisico(Integer.parseInt(tfId.getText()));
			tfFormatoUnhas.setText(ef.getUnhas());
			tfObs1.setText(ef.getObs1());
			tfObs2.setText(ef.getObs2());

			problemas = efDao.buscarProblemas(Integer.parseInt(tfId.getText()));
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			for (int x = 0; x != problemas.size(); x++) {
				model.addRow((Object[]) problemas.get(x));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
