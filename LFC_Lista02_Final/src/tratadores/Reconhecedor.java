package tratadores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Reconhecedor {

	public static void main(String[] args) throws IOException {

		// Faz as leituras das entradas e armazena os valores em uma lista.
		List<String> entrada = lerEntradas();

		// Extrai a gramática
		Gramatica gramatica = new Gramatica(entrada.get(0)); 
		//gramatica.imprimir();

		// Coloca as cadeias de teste em uma lista
		List<String> cadeias = new ArrayList<>();
		for (int i=1; i<entrada.size(); i++){
			cadeias.add(entrada.get(i));
		}

		// Cria os conjuntos Primeiro.
		Primeiro primeiro = new Primeiro(gramatica);
		//primeiro.imprimir();

		// Cria os conjuntos Sequencia.
		Sequencia sequencia = new Sequencia(primeiro, gramatica);
		//sequencia.imprimir();

		// Cria a tabelaM
		Tabela tabelaM = new Tabela(sequencia, primeiro, gramatica);

		// Lista com as respostas do processamento
		ArrayList<Integer> respostas = new ArrayList<Integer>();


		//Realizar os testes para cada cadeia
		for (String cadeia: cadeias){

			// Adiciona o cifrão à cadeia;
			cadeia = cadeia+"$";
			
			// Tratamento caso a cadeia seja vazia
			if(cadeia.equals(" $")){
				cadeia = "$";	
			}

			//Cria a pilha e inicializa
			Stack<String> pilha = new Stack<String>();
			pilha.add("$");
			pilha.add(gramatica.getVariavelInicial());

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
				//System.out.println("1: "+cadeia);
				respostas.add(1);
			} else {
				//System.out.println("0: "+cadeia);
				respostas.add(0);
			}
		}

		// Limpa a tela e escreve as saídas.
		escreverSaidas(respostas);
	}

	private static List<String> lerEntradas() throws IOException {
		// Criando o leitor de entradas a partir do terminal
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		// Lista que armazena os valores lidos no terminal
		List<String> entrada = new ArrayList<String>();
		String str;

		// Adiciona cada valor na Lista "entrada", caso leia-se um "enter" para-se o processo de leitura
		boolean continua = true;
		while(continua){
			str = input.readLine();
			entrada.add(str);
			if (str.equals("")){
				continua = false;
			}
		}

		// Eliminando a ultima posição, pois trata-se de um "enter"
		entrada.remove(entrada.size()-1);
		return entrada;
	}
	
	private static void escreverSaidas(List<Integer> r) {
		clearConsole();
		for (Integer i : r){
			System.out.println(""+i);
		}
	}

	private static void clearConsole() {
		try {
			String os = System.getProperty("os.name");
			if (os.contains("Windows")){
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (Exception exception) {
			//System.out.println("Não consigo limpar a sua tela para escrever a saída.");
		}
	}
}
