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

public class ExameFisicoDao {

	public void cadastrarExameFisico(ExameFisico ef, ArrayList<Integer> problemas) throws SQLException, Exception {
		try {
			String sql = "INSERT INTO exameFisico (unhas,observacao1,observacao2) VALUES (?,?,?)";
			java.sql.PreparedStatement sqlPrep = Conexao.conectar().prepareStatement(sql);
			int contador = 1;
			sqlPrep.setString(contador++, ef.getUnhas());
			sqlPrep.setString(contador++, ef.getObs1());
			sqlPrep.setString(contador++, ef.getObs2());
			sqlPrep.execute();
			int idExame = this.retornaIdExame();
			String sql2 = "INSERT INTO exame_problema (idExame,idProblemaFisico) VALUES (?,?)";
			java.sql.PreparedStatement sqlPrep2 = Conexao.conectar().prepareStatement(sql2);
			for (Integer problema : problemas) {
				contador = 1;
				sqlPrep2.setInt(contador++, idExame);
				sqlPrep2.setInt(contador++, problema);
				sqlPrep2.execute();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public ExameFisico buscarExameFisico(int idAnamnese) throws SQLException, Exception {
		ExameFisico ef = new ExameFisico();
		try {
			String sql = "SELECT exameFisico.unhas,exameFisico.observacao1,exameFisico.observacao2\r\n"
					+ "FROM exameFisico,anamnese\r\n" + "WHERE exameFisico.id = anamnese.idExameFisico\r\n"
					+ "AND anamnese.id = " + idAnamnese;
			java.sql.Statement state = Conexao.conectar().createStatement();
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				ef.setObs1(rs.getString("observacao1"));
				ef.setObs2(rs.getString("observacao2"));
				ef.setUnhas(rs.getString("unhas"));
			}
			state.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return ef;
	}

	public List<Object> buscarProblemas(int idAnamnese) throws SQLException, Exception {
		List<Object> problemas = new ArrayList<Object>();
		try {
			String sql = "SELECT problemaFisico.nome FROM problemaFisico,anamnese,exame_problema,exameFisico\r\n"
					+ "WHERE anamnese.idExameFisico = exameFisico.id\r\n"
					+ "AND exameFisico.id = exame_problema.idExame\r\n"
					+ "AND problemaFisico.id = exame_problema.idProblemaFisico\r\n" + "AND anamnese.id = " + idAnamnese;
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

	private int retornaIdExame() throws Exception {
		try {
			String sql = "SELECT MAX(id) AS id FROM exameFisico";
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

	private int retornaIdAnamnese() throws Exception {
		try {
			String sql = "SELECT MAX(id) AS id FROM anamnese";
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
}