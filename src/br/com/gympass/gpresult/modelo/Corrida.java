package br.com.gympass.gpresult.modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Corrida {

	private Set<Piloto> pilotos;
	private List<Volta> voltas;
	private List<Resultado> resultados;

	public Set<Piloto> getPilotos() {
		return pilotos;
	}

	public void setPilotos(Set<Piloto> pilotos) {
		this.pilotos = pilotos;
	}

	public List<Volta> getVoltas() {
		return voltas;
	}

	public void setVoltas(List<Volta> voltas) {
		this.voltas = voltas;
	}
	
	public List<Resultado> getResultados() {
		return resultados;
	}

	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}

	public Volta getMelhorVolta() {
		Collections.sort(voltas);
		
		return voltas.get(0);
	}

	public List<Volta> getMelhoresVoltas() {
		List<Volta> melhoresVoltas = new ArrayList<>();
		Collections.sort(this.voltas);
		for (Piloto piloto : this.pilotos) {
			for (Volta volta : this.voltas) {
				if(piloto.equals(volta.getPiloto())) {				
					melhoresVoltas.add(volta);
					break;
				}
				
			}
		}
		Collections.sort(melhoresVoltas);
		return melhoresVoltas;
	}
	
}
