package br.edu.ifc.videira.controllers.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.edu.ifc.videira.DAOs.HistoriaClinicaDao;
import br.edu.ifc.videira.beans.HistoriaClinica;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class HistoriaClinicaView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfCirurgia;
	private JTextField tfMedicamentos;
	private JTextField tfAlergia;
	private JTextField tfDor;
	public static JTextField tfId;
	private HistoriaClinicaDao hcDao = new HistoriaClinicaDao();
	private HistoriaClinica hc = new HistoriaClinica();
	List<Object> problemas = new ArrayList<Object>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistoriaClinicaView frame = new HistoriaClinicaView();
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
	public HistoriaClinicaView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(HistoriaClinicaView.class.getResource("/br/edu/ifc/videira/imgs/800x800.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				setCampos();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon(HistoriaClinicaView.class.getResource("/br/edu/ifc/videira/imgs/200x200-t.png")));
		lblNewLabel.setBounds(12, 0, 296, 182);
		contentPane.add(lblNewLabel);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVoltar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVoltar.setBounds(346, 360, 144, 27);
		contentPane.add(btnVoltar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(266, 210, 320, 116);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setFont(new Font("Dialog", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Problemas"
			}
		));
		scrollPane.setViewportView(table);

		JLabel lblCirurgiaEmMembros = new JLabel("Cirurgia em membros inferiores");
		lblCirurgiaEmMembros.setFont(new Font("Dialog", Font.BOLD, 14));
		lblCirurgiaEmMembros.setBounds(326, 55, 239, 17);
		contentPane.add(lblCirurgiaEmMembros);

		tfCirurgia = new JTextField();
		tfCirurgia.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfCirurgia.setEditable(false);
		tfCirurgia.setBounds(563, 53, 227, 21);
		contentPane.add(tfCirurgia);
		tfCirurgia.setColumns(10);

		JLabel lblMedicamentos = new JLabel("Medicamentos");
		lblMedicamentos.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMedicamentos.setBounds(326, 86, 191, 17);
		contentPane.add(lblMedicamentos);

		tfMedicamentos = new JTextField();
		tfMedicamentos.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfMedicamentos.setEditable(false);
		tfMedicamentos.setColumns(10);
		tfMedicamentos.setBounds(563, 84, 227, 21);
		contentPane.add(tfMedicamentos);

		JLabel lblAlergiaAMedicamentos = new JLabel("Alergia a medicamentos");
		lblAlergiaAMedicamentos.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAlergiaAMedicamentos.setBounds(326, 117, 191, 17);
		contentPane.add(lblAlergiaAMedicamentos);

		tfAlergia = new JTextField();
		tfAlergia.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfAlergia.setEditable(false);
		tfAlergia.setColumns(10);
		tfAlergia.setBounds(563, 115, 227, 21);
		contentPane.add(tfAlergia);

		JLabel lblSensibilidadeDor = new JLabel("Sensibilidade à dor");
		lblSensibilidadeDor.setFont(new Font("Dialog", Font.BOLD, 14));
		lblSensibilidadeDor.setBounds(326, 148, 191, 17);
		contentPane.add(lblSensibilidadeDor);

		tfDor = new JTextField();
		tfDor.setFont(new Font("Dialog", Font.PLAIN, 14));
		tfDor.setEditable(false);
		tfDor.setColumns(10);
		tfDor.setBounds(563, 146, 227, 21);
		contentPane.add(tfDor);

		tfId = new JTextField();
		tfId.setHorizontalAlignment(SwingConstants.CENTER);
		tfId.setEditable(false);
		tfId.setBounds(326, 12, 43, 21);
		contentPane.add(tfId);
		tfId.setColumns(10);
	}

	public void setCampos() {
		try {
			hc = hcDao.buscarHistoriaClinica(Integer.parseInt(tfId.getText()));
			tfAlergia.setText(hc.getAlergia());
			tfCirurgia.setText(hc.getCirurgia());
			tfDor.setText(hc.getDor());
			tfMedicamentos.setText(hc.getMedicamento());
			problemas = hcDao.buscarProblemas(Integer.parseInt(tfId.getText()));
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
