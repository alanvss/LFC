package tratadores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Gramatica {
	private List<String> variaveis = new ArrayList<String>();
	private List<String> terminais = new ArrayList<String>();
	private List<String> producoes = new ArrayList<String>();

	public Gramatica(String entrada){
		// Extrai as produ��es da Gram�tica
		String[] producoes = entrada.replaceAll(" ", "").split(",");
		this.producoes = Arrays.asList(producoes);

		// Extrai as vari�veis da Gram�tica
		for(String p: producoes){
			String[] lado = p.split("->");
			if(!variaveis.contains(lado[0]))
				variaveis.add(lado[0]);
		}

		String simbolo = "";
		// Extrai os terminais da Gram�tica
		for(String p: producoes){
			String[] lado = p.split("->");
			for (int i=0; i<lado[1].length(); i++){
				simbolo = ""+lado[1].charAt(i);
				if(!variaveis.contains(simbolo) && !simbolo.equals("E") && !terminais.contains(simbolo))
					terminais.add(""+lado[1].charAt(i));
			}
		}
	}

	public List<String> getVariaveis() {
		return variaveis;
	}

	public void setVariaveis(List<String> variaveis) {
		this.variaveis = variaveis;
	}

	public List<String> getTerminais() {
		return terminais;
	}

	public void setTerminais(List<String> terminais) {
		this.terminais = terminais;
	}

	public List<String> getProducoes() {
		return producoes;
	}

	public void setProducoes(List<String> producoes) {
		this.producoes = producoes;
	}

	public boolean isVariavel (String simbolo){	
		return variaveis.contains(simbolo);
	}

	public boolean isTerminal (String simbolo){	
		return terminais.contains(simbolo);
	}

	public void imprimir(){
		System.out.println("Informa��es da gram�tica: ");
		System.out.println("As produ��es s�o: ");
		System.out.println("   "+producoes.toString());
		System.out.println("As vari�veis s�o: ");
		System.out.println("   "+variaveis.toString());
		System.out.println("Os terminais s�o: ");
		System.out.println("   "+terminais.toString());
	}

	// Dada uma vari�vel, ele retorna a sua lista de produ��es
	public List<String> getListaProducoes(String v) {
		List<String> ListaProducoes = new ArrayList<String>();
		for (String prod: producoes){
			if (v.equals(prod.charAt(0)+"")){
				ListaProducoes.add(prod);
			}
		}
		return ListaProducoes;
	}
	
	// Dada uma lista de produ��es, ele retorna uma lista com o lado direito delas
	public List<String> getParteDireitaListaProducoes(List<String> lista) {
		List<String> parteDireitaListaProducoes = new ArrayList<String>();
		String[] lado;
		for (String prod: lista){
			lado = prod.split("->");
			parteDireitaListaProducoes.add(lado[1]);
		}
		return parteDireitaListaProducoes;
	}
	
	// Dada uma vari�vel, ele retorna uma lista com o lado direito delas
	public List<String> getParteDireitaListaProducoes(String v) {
		List<String> lista = new ArrayList<String>();
		for (String prod: producoes){
			if (v.equals(prod.charAt(0)+"")){
				lista.add(prod);
			}
		}
		List<String> parteDireitaListaProducoes = new ArrayList<String>();
		String[] lado;
		for (String prod: lista){
			lado = prod.split("->");
			parteDireitaListaProducoes.add(lado[1]);
		}
		return parteDireitaListaProducoes;
	}

	// Retorna a vari�vel inicial da gram�tica
	public String getVariavelInicial(){
		return variaveis.get(0);
	}


}
