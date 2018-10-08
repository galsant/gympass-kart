package br.com.gympass.gpresult.modelo;

import org.joda.time.DateTime;

public class Volta implements Comparable<Volta> {
	
	private int numeroVolta;
	private Piloto piloto;
	private double velocidadeMedia;
	private DateTime tempo;
	private String horario;
	
	public int getNumeroVolta() {
		return numeroVolta;
	}
	public void setNumeroVolta(int numeroVolta) {
		this.numeroVolta = numeroVolta;
	}
	public Piloto getPiloto() {
		return piloto;
	}
	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}
	public double getVelocidadeMedia() {
		return velocidadeMedia;
	}
	public void setVelocidadeMedia(double velocidadeMedia) {
		this.velocidadeMedia = velocidadeMedia;
	}
	public DateTime getTempo() {
		return tempo;
	}
	public void setTempo(DateTime tempo) {
		this.tempo = tempo;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	@Override
	public int compareTo(Volta o) {
		if(this.tempo.isBefore(o.getTempo())) {
			return -1;
		}
		if(this.tempo.isAfter(o.getTempo())) {
			return 1;
		}
		return 0;
	}
	
}
