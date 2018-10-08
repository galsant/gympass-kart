package br.com.gympass.gpresult.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;

public class Corrida {

	private Set<Piloto> pilotos;
	private List<Volta> voltas;
	private List<Resultado> resultados;
	private List<Volta> melhoresVoltas;
	private Volta melhorVolta;

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

	public void setMelhoresVoltas(List<Volta> melhoresVoltas) {
		this.melhoresVoltas = melhoresVoltas;
	}

	public Volta getMelhorVolta() {
		return melhorVolta;
	}

	public void setMelhorVolta(Volta melhorVolta) {
		this.melhorVolta = melhorVolta;
	}

	public List<Volta> getMelhoresVoltas() {
		return melhoresVoltas;
	}

	public void getResultadoFinal() throws ParseException {
		List<Resultado> results = new ArrayList<>();
		for (Piloto piloto : pilotos) {
			Resultado result = new Resultado();
			result.setPiloto(piloto);
			SimpleDateFormat sdf2 = new SimpleDateFormat("mm:ss.SSS");
			
			DateTime tempoAcumulado = new DateTime(sdf2.parse("0:00.000"));
			int voltasDadas = 0;
			
			for(Volta volta : voltas) {
				if(piloto.equals(volta.getPiloto())) {
//					System.out.println(volta.getHorario() 
//		        			+ " " + volta.getPiloto().getId() 
//		        			+ " " + volta.getPiloto().getNome()
//		        			+ " " + volta.getNumeroVolta()
//		        			+ " " + volta.getTempo().toString("mm:ss.SSS")
//		        			+ " " + volta.getVelocidadeMedia());
					
					voltasDadas += 1;
					tempoAcumulado = tempoAcumulado.plusMinutes(volta.getTempo().getMinuteOfHour())
									.plusSeconds(volta.getTempo().getSecondOfMinute())
									.plusMillis(volta.getTempo().getMillisOfSecond());
				}
				
			}
			result.setTempoTotal(tempoAcumulado);
			result.setVoltas(voltasDadas);
		//	System.out.println(tempoAcumulado.toString("mm:ss.SSS"));
			results.add(result);
		}
		setResultados(results);
		
		Collections.sort(this.resultados);
		
		Iterator<Resultado> iterResults = this.resultados.iterator();
		
		int classif = 1;
		DateTime campeao = new DateTime();
		while(iterResults.hasNext()) {
			Resultado resultado = iterResults.next();
			if(classif == 1)
				campeao = resultado.getTempoTotal();
			
			System.out.println(classif + "º " + resultado.getPiloto().getId()
					+ " " + resultado.getPiloto().getNome()
					+ " " + resultado.getVoltas()
					+ " " + resultado.getTempoTotal().toString("mm:ss.SSS")
					+ " +" + resultado.getTempoTotal()
										.minusMinutes(campeao.getMinuteOfHour())
										.minusSeconds(campeao.getSecondOfMinute())
										.minusMillis(campeao.getMillisOfSecond()).toString("mm:ss.SSS"));
			classif++;

			
		}
		computarMelhoresVoltas();
		
		System.out.println("Melhor volta: " +this.melhorVolta.getPiloto().getNome()
				+ " " +this.melhorVolta.getNumeroVolta()
				+ " " +this.melhorVolta.getTempo().toString("mm:ss.SSS"));
		
		for (Volta volta : this.melhoresVoltas) {
			System.out.println("Melhores voltas: " + volta.getPiloto().getNome() + " " + volta.getNumeroVolta() + " " + volta.getTempo().toString("mm:ss.SSS"));
		}
	}
	
	

	public void computarMelhoresVoltas() {
		List<Volta> melhores = new ArrayList<>();
		Collections.sort(this.voltas);
		for (Piloto piloto : pilotos) {
			for (Volta volta : voltas) {
				if(piloto.equals(volta.getPiloto())) {				
					melhores.add(volta);
					break;
				}
				
			}
		}
		this.melhoresVoltas = melhores;
		computarMelhorVolta();
		
	}
	
	public void computarMelhorVolta() {
		Collections.sort(this.melhoresVoltas);
		
		this.melhorVolta = this.melhoresVoltas.get(0);
		
				
	}
	
	
	
}
