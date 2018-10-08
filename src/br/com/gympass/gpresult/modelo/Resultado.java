package br.com.gympass.gpresult.modelo;

import java.util.List;

import org.joda.time.DateTime;

public class Resultado implements Comparable {
	
	private Piloto piloto;
	private int voltas;
	private String classificacao;
	private DateTime tempoTotal;
	private double velocidadeMedia;
	private boolean completouProva;
	
	public DateTime getDiferenca() {
		return diferenca;
	}
	public void setDiferenca(DateTime diferenca) {
		this.diferenca = diferenca;
	}
	private DateTime diferenca;
	private List<Volta> melhoresVoltas;
	
	public Piloto getPiloto() {
		return piloto;
	}
	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}
	public int getVoltas() {
		return voltas;
	}
	public void setVoltas(int voltas) {
		this.voltas = voltas;
	}
	public String getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	public DateTime getTempoTotal() {
		return tempoTotal;
	}
	public void setTempoTotal(DateTime tempoTotal) {
		this.tempoTotal = tempoTotal;
	}
	
	public DateTime getDiff() {
		return diferenca;
	}
	public void setDiff(DateTime diferenca) {
		this.diferenca = diferenca;
	}
	
	public List<Volta> getMelhoresVoltas() {
		return melhoresVoltas;
	}
	public void setMelhoresVoltas(List<Volta> melhoresVoltas) {
		this.melhoresVoltas = melhoresVoltas;
	}
	
	public double getVelocidadeMedia() {
		return velocidadeMedia;
	}
	public void setVelocidadeMedia(double velocidadeMedia) {
		this.velocidadeMedia = velocidadeMedia;
	}
	
	public boolean isCompletouProva() {
		return completouProva;
	}
	public void setCompletouProva(boolean completouProva) {
		this.completouProva = completouProva;
	}
	@Override
	public int compareTo(Object o) {
		Resultado result = (Resultado) o;
		if(this.tempoTotal.isBefore(result.getTempoTotal())) {
			return -1;
		}
		if(this.tempoTotal.isAfter(result.getTempoTotal())) {
			return 1;
		}
		return 0;
	}
	
}
