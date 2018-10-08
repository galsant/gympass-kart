package br.com.gympass.gpresult;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.joda.time.DateTime;

import br.com.gympass.gpresult.modelo.Corrida;
import br.com.gympass.gpresult.modelo.Piloto;
import br.com.gympass.gpresult.modelo.Volta;

public class LeitorLog {
	
	public static void main(String[] args) throws ParseException {
		
		File arq = new File("C:\\Users\\Gusta\\Desktop\\logGP.txt");
		
		 try {
		        //Indicamos o arquivo que será lido
		        FileReader fileReader = new FileReader(arq);

		        //Criamos o objeto bufferReader que nos
		        // oferece o método de leitura readLine()
		        BufferedReader bufferedReader = new BufferedReader(fileReader);

		        //String que irá receber cada linha do arquivo
		        String linha = "";

		        //Fazemos um loop linha a linha no arquivo,
		        // enquanto ele seja diferente de null.
		        //O método readLine() devolve a linha na
		        // posicao do loop para a variavel linha.
		
		        ArrayList<LogTxt> texto = new ArrayList<>();
		        while ( ( linha = bufferedReader.readLine() ) != null) {
		        	
		        	if(!linha.contains("Hora")) {
		        	StringTokenizer tokenizer = new StringTokenizer(linha);
		        		
		        		int aux = 0;
		        		
		        		LogTxt log = new LogTxt();
				    	while(tokenizer.hasMoreTokens()) {
				    		
				    		String valor = tokenizer.nextToken();

				    		
				    		switch (aux) {
							case 0:
								SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:mmm");
								log.setHorario(valor);
								aux++;
								break;
							case 1:				
								log.setCodigoPiloto(valor);
								aux++;
								break;
							case 2:
								aux++;
								break;
							case 3:
								log.setNomePiloto(valor);
								aux++;
								break;
							case 4:
								log.setNumeroVolta(Integer.parseInt(valor));
								aux++;
								break;
							case 5:
								SimpleDateFormat sdf2 = new SimpleDateFormat("mm:ss.SSS");
								log.setTempoVolta(new DateTime(sdf2.parse(valor)));
								aux++;
								break;
							case 6:
								SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss:mmm");
						//		log.setVelocidadeMedia(valor);
								texto.add(log);
								aux++;
								break;
								
							default:
								break;
							}	    				    	
				    	}
		        	}
			    }
		        
		        Set<Piloto> pilotos = new HashSet<>();
		        List<Volta> voltas = new ArrayList();
		        Corrida corrida = new Corrida();
		        
		        Iterator iter = texto.iterator();
		        	
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
		        
//		        Iterator pilots = pilotos.iterator();
//		        while(pilots.hasNext()) {
//		        	Piloto p = (Piloto) pilots.next();
//		        	System.out.println(p.getId() + "" + p.getNome());
//		        }


		        Iterator auxV = corrida.getVoltas().iterator();
//		        while(auxV.hasNext()) {
//		        	Volta volta = (Volta) auxV.next();
//		        	System.out.println(volta.getHorario() 
//		        			+ " " + volta.getPiloto().getId() 
//		        			+ " " + volta.getPiloto().getNome()
//		        			+ " " + volta.getNumeroVolta()
//		        			+ " " + volta.getTempo()
//		        			+ " " + volta.getVelocidadeMedia());
//	
//		        }
		        
		
		        corrida.getResultadoFinal();
		        

				//liberamos o fluxo dos objetos ou fechamos o arquivo
		        fileReader.close();
		        bufferedReader.close();
			} catch (IOException e) {
		    	e.printStackTrace();
		    }
	}

}
