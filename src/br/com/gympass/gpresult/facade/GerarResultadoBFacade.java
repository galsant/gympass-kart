package br.com.gympass.gpresult.facade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.joda.time.DateTime;

import br.com.gympass.gpresult.LogTxt;
import br.com.gympass.gpresult.modelo.Corrida;
import br.com.gympass.gpresult.modelo.Piloto;
import br.com.gympass.gpresult.modelo.Resultado;
import br.com.gympass.gpresult.modelo.Volta;

public class GerarResultadoBFacade {
	
	public List<LogTxt> lerArquivoLogKart(File arquivo) throws ParseException {

		ArrayList<LogTxt> logTxt = new ArrayList<>();
		
		try {

			FileReader fileReader = new FileReader(arquivo);

			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String linha = "";

			while ((linha = bufferedReader.readLine()) != null) {

				if (!linha.contains("Hora")) {
					StringTokenizer tokenizer = new StringTokenizer(linha);

					int count = 0;

					LogTxt registroLog = new LogTxt();
					while (tokenizer.hasMoreTokens()) {

						String valor = tokenizer.nextToken();

						switch (count) {
						case 0:
							SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:mmm");
							registroLog.setHorario(valor);
							count++;
							break;
						case 1:
							registroLog.setCodigoPiloto(valor);
							count++;
							break;
						case 2:
							count++;
							break;
						case 3:
							registroLog.setNomePiloto(valor);
							count++;
							break;
						case 4:
							registroLog.setNumeroVolta(Integer.parseInt(valor));
							count++;
							break;
						case 5:
							SimpleDateFormat sdf2 = new SimpleDateFormat("mm:ss.SSS");
							registroLog.setTempoVolta(new DateTime(sdf2.parse(valor)));
							count++;
							break;
						case 6:
							SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss:mmm");
							valor = valor.replace(",", ".");
							registroLog.setVelocidadeMedia(Float.parseFloat(valor));
							logTxt.add(registroLog);
							count++;
							break;

						default:
							break;
						}
					}
				}
				
			}
			fileReader.close();
			bufferedReader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return logTxt;
	}
	
	public Corrida processaArquivoLog(List<LogTxt> arquivoLog) {
   
		Set<Piloto> pilotos = new HashSet<>();
        List<Volta> voltas = new ArrayList();
        
        Corrida corrida = new Corrida();
        
        Iterator iter = arquivoLog.iterator();
        	
        while(iter.hasNext()) {
        	
          	Piloto piloto = new Piloto();
        	Volta volta = new Volta();
        	
        	LogTxt logAux = (LogTxt) iter.next();
  
        	piloto.setId(logAux.getCodigoPiloto());
        	piloto.setNome(logAux.getNomePiloto());

        	pilotos.add(piloto);
        	
        	volta.setHorario(logAux.getHorario());
        	volta.setNumeroVolta(logAux.getNumeroVolta());
        	volta.setPiloto(piloto);
        	volta.setTempo(logAux.getTempoVolta());
        	volta.setVelocidadeMedia(logAux.getVelocidadeMedia());
        	
        	voltas.add(volta);
        	
        }
      
        corrida.setPilotos(pilotos);
        corrida.setVoltas(voltas);
        
        corrida.setMelhorVolta(recuperarMelhorVolta(voltas));
		
        return corrida;
	}

	public List<Resultado> processaResultadoFinal(Corrida corrida) throws ParseException {
		
		List<Resultado> classificacaoFinal = new ArrayList<>();
		
		for (Piloto piloto : corrida.getPilotos()) {
			
			Resultado resultadoPiloto = new Resultado();
			
			resultadoPiloto.setPiloto(piloto);
			
			SimpleDateFormat sdf2 = new SimpleDateFormat("mm:ss.SSS");
			DateTime tempoAcumulado = new DateTime(sdf2.parse("0:00.000"));
			double velocidadeAcumulada = 0;
			
			int voltasCompletadas = 0;
			
			for(Volta volta : corrida.getVoltas()) {
				if(piloto.equals(volta.getPiloto())) {
					voltasCompletadas++;
					
					velocidadeAcumulada+= volta.getVelocidadeMedia();
					
					tempoAcumulado = tempoAcumulado.plusMinutes(volta.getTempo().getMinuteOfHour())
									.plusSeconds(volta.getTempo().getSecondOfMinute())
									.plusMillis(volta.getTempo().getMillisOfSecond());
				}
				
			}
			resultadoPiloto.setVelocidadeMedia(velocidadeAcumulada/voltasCompletadas);
			resultadoPiloto.setTempoTotal(tempoAcumulado);
			resultadoPiloto.setVoltas(voltasCompletadas);
			
			classificacaoFinal.add(resultadoPiloto);
		}
		
		calcularDiferenca(classificacaoFinal);
//		calcularDiferenca(classificacaoFinal);
//		calcularMelhoresVoltas(corrida);
		
		return classificacaoFinal;
	}
	
	public Volta recuperarMelhorVolta(List<Volta> voltas) {
		Collections.sort(voltas);
		
		return voltas.get(0);
	}
	
	public double recuperarVelocidadeMedia() {
		return 0;
	}
	
	public List<Volta> recuperarMelhoresVoltas(Corrida corrida) {
		List<Volta> melhoresVoltas = new ArrayList<>();
		Collections.sort(corrida.getVoltas());
		for (Piloto piloto : corrida.getPilotos()) {
			for (Volta volta : corrida.getVoltas()) {
				if(piloto.equals(volta.getPiloto())) {				
					melhoresVoltas.add(volta);
					break;
				}
				
			}
		}
		Collections.sort(melhoresVoltas);
		
		return melhoresVoltas;
	}
	
	private void calcularDiferenca(List<Resultado> classificacao) {
	
		Collections.sort(classificacao);
		
		Iterator<Resultado> iterClassificacao = classificacao.iterator();
		
		DateTime tempoCampeao = classificacao.get(0).getTempoTotal();
		
		int contador = 1;
		
		while(iterClassificacao.hasNext()) {
			
			Resultado resultado = iterClassificacao.next();
			
			if(resultado.getVoltas() == 4) {
				resultado.setClassificacao(String.valueOf(contador));
				resultado.setCompletouProva(true);
			}
				
			if(contador != 1)
			resultado.setDiff(resultado.getTempoTotal()
										.minusMinutes(tempoCampeao.getMinuteOfHour())
										.minusSeconds(tempoCampeao.getSecondOfMinute())
										.minusMillis(tempoCampeao.getMillisOfSecond()));

			contador++;
			
		}
	}
	
}
