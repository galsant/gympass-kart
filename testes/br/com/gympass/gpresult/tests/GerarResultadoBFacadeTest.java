package br.com.gympass.gpresult.tests;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.gympass.gpresult.LogTxt;
import br.com.gympass.gpresult.facade.GerarResultadoBFacade;
import br.com.gympass.gpresult.modelo.Corrida;
import br.com.gympass.gpresult.modelo.Resultado;

public class GerarResultadoBFacadeTest {

	File arquivo = new File("C:\\Users\\Gusta\\Desktop\\logGP.txt");
	
	GerarResultadoBFacade facade = new GerarResultadoBFacade();
	
	List<LogTxt> arquivoLog = new ArrayList<>();
	List<Resultado> resultados = new ArrayList<>();
	
	Corrida corrida  = new Corrida();
	
	@Test
	public void lerArquivoLogKartTest() {	
		try {
			arquivoLog = facade.lerArquivoLogKart(arquivo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Qtde linhas: " +arquivoLog.size());
		
		Assert.assertTrue(!arquivoLog.isEmpty());	
	}
	
	@Test
	public void processaArquivoLogTest() {
		try {
			arquivoLog = facade.lerArquivoLogKart(arquivo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		corrida = facade.processaArquivoLog(arquivoLog);
		
		System.out.println("Pilotos: " +corrida.getPilotos().size());
		System.out.println("Voltas: " +corrida.getVoltas().size());
		
		Assert.assertNotNull(corrida);
		
	}
	
	@Test
	public void processaResultadoFinalTest() {
		try {
			arquivoLog = facade.lerArquivoLogKart(arquivo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		corrida = facade.processaArquivoLog(arquivoLog);
		
		try {
			resultados = facade.processaResultadoFinal(corrida);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Resultado resultado : resultados) {
			System.out.println("Posicao:" +resultado.getClassificacao() 
					+ " " + resultado.getPiloto().getNome()
					+ " " + resultado.isCompletouProva());
			
		}
		
		Assert.assertNotNull(resultados);
		
		
	}
	
}
