package br.edu.ifc.videira.beans;

public class Tratamento {
	private int idTratamento;
	private int idPaciente;
	private int idProcedimento;
	private int idAnamnese;
	private String observacao;

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getIdAnamnese() {
		return idAnamnese;
	}

	public void setIdAnamnese(int idAnamnese) {
		this.idAnamnese = idAnamnese;
	}

	public int getIdTratamento() {
		return idTratamento;
	}

	public void setIdTratamento(int idTratamento) {
		this.idTratamento = idTratamento;
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public int getIdProcedimento() {
		return idProcedimento;
	}

	public void setIdProcedimento(int idProcedimento) {
		this.idProcedimento = idProcedimento;
	}

}
