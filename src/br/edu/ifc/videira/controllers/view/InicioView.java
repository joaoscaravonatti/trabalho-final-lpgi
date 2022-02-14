package br.edu.ifc.videira.controllers.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class InicioView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InicioView frame = new InicioView();
					frame.setLocationRelativeTo(null);
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
	public InicioView() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(InicioView.class.getResource("/br/edu/ifc/videira/imgs/800x800.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 890, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("");
		label.setBounds(180, 48, 508, 293);
		label.setIcon(new ImageIcon(InicioView.class.getResource("/br/edu/ifc/videira/imgs/500x500-t.png")));
		contentPane.add(label);

		JButton btnCadastrarPaciente = new JButton("Cadastrar paciente");
		btnCadastrarPaciente.setBounds(26, 394, 197, 45);
		btnCadastrarPaciente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroView cv;
				try {
					cv = new CadastroView();
					cv.setLocationRelativeTo(null);
					cv.setVisible(true);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCadastrarPaciente.setFont(new Font("Dialog", Font.BOLD, 15));
		contentPane.add(btnCadastrarPaciente);

		JButton btnIniciarTratamento = new JButton("Iniciar tratamento");
		btnIniciarTratamento.setBounds(235, 394, 197, 45);
		btnIniciarTratamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarTratamentoView ct = new CadastrarTratamentoView();
				ct.setLocationRelativeTo(null);
				ct.setVisible(true);
			}
		});
		btnIniciarTratamento.setFont(new Font("Dialog", Font.BOLD, 15));
		contentPane.add(btnIniciarTratamento);

		JButton btnTratamentoEmAndamento = new JButton("Tratamentos");
		btnTratamentoEmAndamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TratamentosView tsv = new TratamentosView();
				tsv.setLocationRelativeTo(null);
				tsv.setVisible(true);
			}
		});
		btnTratamentoEmAndamento.setFont(new Font("Dialog", Font.BOLD, 15));
		btnTratamentoEmAndamento.setBounds(444, 394, 197, 45);
		contentPane.add(btnTratamentoEmAndamento);

		JButton btnPacientes = new JButton("Pacientes");
		btnPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PacientesView pv = new PacientesView();
				pv.setLocationRelativeTo(null);
				pv.setVisible(true);
			}
		});
		btnPacientes.setFont(new Font("Dialog", Font.BOLD, 15));
		btnPacientes.setBounds(653, 394, 197, 45);
		contentPane.add(btnPacientes);
	}
}
