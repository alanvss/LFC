package tratadores;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import arquivo.LerArquivoTexto;

public class Reconhecedor {



	public static void main(String[] args) {
		String pathDoArquivo = "D:\\Meus Documentos\\workspace_lfc\\";
		String nomeDoArquivo = "L2_automato_01.txt";
		LerArquivoTexto leitor = new LerArquivoTexto(pathDoArquivo+nomeDoArquivo);

		// Ler o arquivo passado como parâmetro e o coloca numa lista entrada.
		List<String> entrada = new ArrayList<>();
		entrada = leitor.lerLinhasArquivo();

		// Extrai a gramática
		Gramatica gramatica = new Gramatica(entrada.get(0)); 
		gramatica.imprimir();

		// Coloca as cadeias de teste em uma lista
		List<String> cadeias = new ArrayList<>();
		for (int i=1; i<entrada.size(); i++){
			cadeias.add(entrada.get(i));
		}

		// Cria os conjuntos Primeiro.
		Primeiro primeiro = new Primeiro(gramatica);
		primeiro.imprimir();
		
		// Cria os conjuntos Sequencia.
		Sequencia sequencia = new Sequencia(primeiro, gramatica);

		sequencia.imprimir();
		
		// Cria a tabelaM
		Tabela tabelaM = new Tabela(sequencia, primeiro, gramatica);


		//Realizar os testes para cada cadeia
		for (String cadeia: cadeias){

			// Adiciona o cifrão à cadeia;
			cadeia = cadeia+"$";
			
			System.out.println(" - - - - - - - - - - - - - - - -");
			System.out.println("Testando a cadeia: "+cadeia);

			//Cria a pilha e inicializa
			Stack<String> pilha = new Stack<String>();
			pilha.add("$");
			pilha.add(gramatica.getVariavelInicial());

			//String pilhaInicial = pilha.toString();

			int n = cadeia.length();

			boolean aceita = false;

			int i= 0;
			while (i<n){
				String simboloPilha = pilha.get(pilha.size()-1);
				String simboloCadeia = cadeia.charAt(i)+"";

				if (simboloPilha.equals(simboloCadeia)){
					//desempilho o terminal
					pilha.remove(pilha.size()-1);
					i++;
					//vou para o próximo item da cadeia ( ou seja para o fim do for)
				} else {
					List<CelulaDaTabela> celulas = tabelaM.consulta(simboloPilha, simboloCadeia);
					if (celulas.size() == 0) {
						break;
					} else{
						String celula = (celulas.get(0)).getCampo();
						String[] lado = celula.split("->");
						celula = lado[1];
						// Desempilho o que está no topo da pilha
						pilha.remove(pilha.size()-1);

						// Empilha o conteúdo da célula
						if (!celula.equals("E")){
							for (int j=celula.length()-1; j >= 0 ; j-- ){
								pilha.add(celula.charAt(j)+"");	
							}
						}
						
					}
				}
				if (simboloPilha.equals(simboloCadeia) && simboloPilha.equals("$")){
					aceita = true;
				}
				
			}
			//Voltei aqui para o for da cadeia.
			if (aceita){
				System.out.println("1");
			} else {
				System.out.println("0");
			}
		}
	}
}
