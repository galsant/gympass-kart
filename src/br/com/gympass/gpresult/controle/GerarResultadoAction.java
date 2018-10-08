package br.com.gympass.gpresult.controle;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import br.com.gympass.gpresult.LogTxt;
import br.com.gympass.gpresult.facade.GerarResultadoBFacade;
import br.com.gympass.gpresult.modelo.Corrida;
import br.com.gympass.gpresult.modelo.Resultado;
import br.com.gympass.gpresult.modelo.Volta;

public class GerarResultadoAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private File upload;
	
	private List<Resultado> resultadoFinal;
	private List<Volta> melhoresVoltas;
	private List<Resultado> velocidadeMedia;
	private Corrida corrida;
	
	private GerarResultadoBFacade facade = new GerarResultadoBFacade();
	
	public String execute() {
		
		List<LogTxt> arquivoLog = new ArrayList<>();
		
		try {
			 arquivoLog = facade.lerArquivoLogKart(upload);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.addActionError("Erro ao ler arquivo.");
			return "input";

		}
		
		corrida = facade.processaArquivoLog(arquivoLog);
		
		try {
			resultadoFinal = facade.processaResultadoFinal(corrida);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.addActionError("Erro tratar tempo da volta.");
			return "input";
		}
		
		melhoresVoltas = corrida.getMelhoresVoltas();

		return "success";
	}
	
	public void validateExecute() {
		if (upload == null) {
			this.addActionError("Arquivo não selecionado ou inexistente.");
			return;
		}
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public List<Resultado> getResultadoFinal() {
		return resultadoFinal;
	}

	public void setResultadoFinal(List<Resultado> resultadoFinal) {
		this.resultadoFinal = resultadoFinal;
	}

	public List<Volta> getMelhoresVoltas() {
		return melhoresVoltas;
	}

	public void setMelhoresVoltas(List<Volta> melhoresVoltas) {
		this.melhoresVoltas = melhoresVoltas;
	}

	public List<Resultado> getVelocidadeMedia() {
		return velocidadeMedia;
	}

	public void setVelocidadeMedia(List<Resultado> velocidadeMedia) {
		this.velocidadeMedia = velocidadeMedia;
	}

	public Corrida getCorrida() {
		return corrida;
	}

	public void setCorrida(Corrida corrida) {
		this.corrida = corrida;
	}
	
}
