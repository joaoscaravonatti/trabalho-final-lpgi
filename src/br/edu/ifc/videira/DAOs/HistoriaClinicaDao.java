package br.edu.ifc.videira.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.edu.ifc.videira.beans.ExameFisico;
import br.edu.ifc.videira.beans.HistoriaClinica;
import br.edu.ifc.videira.beans.Paciente;
import br.edu.ifc.videira.beans.Problema;
import br.edu.ifc.videira.utils.Conexao;

public class HistoriaClinicaDao {

	public void cadastrarHistoriaClinica(HistoriaClinica hc, ArrayList<Integer> problemas)
			throws SQLException, Exception {
		try {
			String sql = "INSERT INTO historiaClinica (cirurgia,medicamento,alergia,dor) VALUES (?,?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			int contador = 1;
			sqlPrep.setString(contador++, hc.getCirurgia());
			sqlPrep.setString(contador++, hc.getMedicamento());
			sqlPrep.setString(contador++, hc.getAlergia());
			sqlPrep.setString(contador++, hc.getDor());
			sqlPrep.execute();
			int idHistoria = this.retornaIdHistoria();
			String sql2 = "INSERT INTO historia_problema (idHistoria,idProblemaClinico) VALUES (?,?)";
			java.sql.PreparedStatement sqlPrep2 = Conexao.conectar().prepareStatement(sql2);
			for (Integer problema : problemas) {
				contador = 1;
				sqlPrep2.setInt(contador++, idHistoria);
				sqlPrep2.setInt(contador++, problema);
				sqlPrep2.execute();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public HistoriaClinica buscarHistoriaClinica(int idAnamnese) throws SQLException, Exception {
		HistoriaClinica hc = new HistoriaClinica();
		try {
			String sql = "SELECT historiaClinica.cirurgia,historiaClinica.medicamento,historiaClinica.alergia,historiaClinica.dor\r\n"
					+ "FROM historiaClinica,anamnese\r\n" + "WHERE historiaClinica.id = anamnese.idHistoriaClinica\r\n"
					+ "AND anamnese.id = " + idAnamnese;
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				hc.setAlergia(rs.getString("alergia"));
				hc.setCirurgia(rs.getString("cirurgia"));
				hc.setDor(rs.getString("dor"));
				hc.setMedicamento(rs.getString("medicamento"));
			}
			state.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return hc;
	}

	public List<Object> buscarProblemas(int idAnamnese) throws SQLException, Exception {
		List<Object> problemas = new ArrayList<Object>();
		try {
			String sql = "SELECT problemaClinico.nome FROM problemaClinico,anamnese,historia_problema,historiaClinica\r\n"
					+ "WHERE anamnese.idHistoriaClinica = historiaClinica.id\r\n"
					+ "AND historiaClinica.id = historia_problema.idHistoria\r\n"
					+ "AND problemaClinico.id = historia_problema.idProblemaClinico\r\n" + "AND anamnese.id = "
					+ idAnamnese;
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				Object[] linha = { rs.getString(1) };
				problemas.add(linha);
			}
			state.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return problemas;
	}

	public int retornaIdHistoria() throws Exception {
		try {
			String sql = "SELECT MAX(id) AS id FROM historiaClinica";
			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			ResultSet rs = sqlPrep.executeQuery();
			if (rs.next()) {
				return rs.getInt("id");
			} else {
				return 1;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			return 1;
		}
	}

//	private int retornaIdAnamnese() throws Exception {
//		try {
//			String sql = "SELECT MAX(id) AS id FROM anamnese";
//			PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
//			ResultSet rs = sqlPrep.executeQuery();
//			if (rs.next()) {
//				return rs.getInt("id");
//			} else {
//				return 1;
//			}
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, e.getMessage());
//			return 1;
//		}
//	}
}