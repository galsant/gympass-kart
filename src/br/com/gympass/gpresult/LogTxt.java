package br.com.gympass.gpresult;

import org.joda.time.DateTime;

public class LogTxt {
	
	private String horario;
	private String codigoPiloto;
	private String nomePiloto;
	private int numeroVolta;
	private DateTime tempoVolta;
	private double velocidadeMedia;
	
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getCodigoPiloto() {
		return codigoPiloto;
	}
	public void setCodigoPiloto(String codigoPiloto) {
		this.codigoPiloto = codigoPiloto;
	}
	public String getNomePiloto() {
		return nomePiloto;
	}
	public void setNomePiloto(String nomePiloto) {
		this.nomePiloto = nomePiloto;
	}
	public int getNumeroVolta() {
		return numeroVolta;
	}
	public void setNumeroVolta(int numeroVolta) {
		this.numeroVolta = numeroVolta;
	}
	public DateTime getTempoVolta() {
		return tempoVolta;
	}
	public void setTempoVolta(DateTime tempoVolta) {
		this.tempoVolta = tempoVolta;
	}
	public double getVelocidadeMedia() {
		return velocidadeMedia;
	}
	public void setVelocidadeMedia(double velocidadeMedia) {
		this.velocidadeMedia = velocidadeMedia;
	}
}
