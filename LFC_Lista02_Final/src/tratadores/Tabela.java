package tratadores;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Tabela {
	
	List<CelulaDaTabela> Tabela = new ArrayList<CelulaDaTabela>();

	public Tabela(Sequencia s, Primeiro p, Gramatica g){
		
		//CelulaDaTabela celula = new CelulaDaTabela();
		HashSet<String> conjunto = new HashSet<String>();
		HashSet<String> conjuntoSequencia = new HashSet<String>();
		List<String> listaProducoesVariavel;
		for(String variavel: g.getVariaveis()){
			listaProducoesVariavel = g.getListaProducoes(variavel);
			//Para cada produção dessa variável...
			for(String producao: listaProducoesVariavel){
				conjunto.clear();
				//Armazena o conjunto de Primeiro(w)
				conjunto.addAll(p.retornaConjunto(getLadoDireito(producao)));
				for(String elemento: conjunto ){
					if(g.isTerminal(elemento)){
						CelulaDaTabela celula = new CelulaDaTabela(variavel, elemento, producao);
						Tabela.add(celula);
					}
				}
				if (conjunto.contains("E")){
					conjuntoSequencia.addAll(s.retornaConjunto(variavel));
					for (String elemento: conjuntoSequencia){
						CelulaDaTabela celula = new CelulaDaTabela(variavel, elemento, producao);
						Tabela.add(celula);
					}
				}
			}
		}
	}
	
	public String getLadoDireito (String p){
		String[] lado = p.split("->");
		return lado[1];
	}
	
	public void imprimir(){
		System.out.println("Tamanho da lista: "+Tabela.size());
		System.out.println("Imprimindo Tabela: ");
		for (CelulaDaTabela cel: Tabela){
			System.out.println("Variável: "+cel.getVariavel()+ "\tTerminal: "+cel.getTerminal()+ "\tCampo: "+cel.getCampo());
			
		}
	}
	
	public List<CelulaDaTabela> consulta(String variavel, String terminal){
		List<CelulaDaTabela> retorno = new ArrayList<CelulaDaTabela>();
		for (CelulaDaTabela celula: Tabela){
			if (variavel.equals(celula.getVariavel())){
				if (terminal.equals(celula.getTerminal())){
					CelulaDaTabela cel = new CelulaDaTabela(celula.getVariavel(), celula.getTerminal(), celula.getCampo());
					retorno.add(cel);	
				}
			}
		}
		return retorno; 
	}
}
