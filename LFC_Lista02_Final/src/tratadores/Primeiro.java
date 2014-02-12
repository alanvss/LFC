package tratadores;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Primeiro {
	// Estrutura de Dados de Primeiro.
	private HashMap<String, HashSet<String>> Primeiro = new HashMap<String, HashSet<String>>();
	private Gramatica gramatica;

	// Construtor que gera o Primeiro a partir da gram�tica
	public Primeiro(Gramatica g){

		gramatica = g;

		// Sei que Epsilon n�o � vari�vel, mas n�o tenho certeza se ele � terminal,
		//assim sendo preferi deixar ele de fora do conjunto dos terminais.
		HashSet<String> conjuntoEpsilon = new HashSet<String>();
		conjuntoEpsilon.add("E");
		Primeiro.put("E", conjuntoEpsilon);


		// 1� Caso: para cada terminal ele cria o Primeiro daquele terminal. 
		// ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- -----
		for(String terminal: g.getTerminais()){
			HashSet<String> conjuntoTerminal = new HashSet<String>();
			conjuntoTerminal.add(terminal);
			Primeiro.put(terminal, conjuntoTerminal);
		}


		// 2� Caso: para cada vari�vel ele cria o Primeiro daquela vari�vel.
		// ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- -----
		// Criando conjuntos vazios para cada Primeiro de cada vari�vel.
		for(String variavel: g.getVariaveis()){
			HashSet<String> conjuntoVariavel = new HashSet<String>();
			Primeiro.put(variavel, conjuntoVariavel);
		}
		
		
		boolean alteracao = true; // Sentinela que verifica se houve altera��o ou n�o nos conjuntos.
		List<String> listaProducoesVariavel; // Armazerna a lista de produ��o das vari�veis para o "for"
		int k;
		boolean continua;

		// Enquanto houver altera��es em algum dos conjuntos dos Primeiros 
		while(alteracao){
			alteracao = false;

			for(String variavel: g.getVariaveis()){
				listaProducoesVariavel = g.getListaProducoes(variavel);
				
				for(String producao : listaProducoesVariavel){
					int n = (getLadoDireito(producao)).length();

					k = 1;
					continua = true;
					while(continua && k<=n){
						// Seleciono Xk
						String xk = (getLadoDireito(producao)).charAt(k-1)+"";
						//System.out.println(xk);

						// Crio o conjunto_xk que representa: Primeiro(xk)-epsilon
						// Busco os elementos do conjunto xk em Primeiro, removo o epsilon
						//e preencho o conjunto criado,
						HashSet<String> conjunto_xK = new HashSet<String>();
						conjunto_xK.addAll(Primeiro.get(xk));
						conjunto_xK.remove("E");

						// Testar se a adi��o do conjunto_xk ao primeiro da vari�vel ocasiona altera��o.
						for(String elemento: conjunto_xK){
							if(!Primeiro.get(variavel).contains(elemento)){
								alteracao = true;
							}
						}
						// Primeiro(variavel A) := Primeiro(xk) - epsilon;
						
						Primeiro.get(variavel).addAll(conjunto_xK);
			

						// Testa se Primeiro(xk) contem ou nao Epsilon
						//HashSet<String> conjuntoTeste = new HashSet<String>();
						//conjuntoTeste.addAll(Primeiro.get(xk));
						//if(!conjuntoTeste.contains("E")){		// nao tem Epsilon
						if(!(Primeiro.get(xk).contains("E"))){		// nao tem Epsilon
							continua = false;
						}
						k++;
					}

					if(continua){
						// Testar se a adi��o de E ao primeiro da vari�vel ocasiona altera��o.
						if(!Primeiro.get(variavel).contains("E")){
							alteracao = true;
						}
						HashSet<String> temp = new HashSet<String>();
						temp.addAll(Primeiro.get(variavel));
						temp.add("E");
						
						Primeiro.put(variavel, temp);
						//Primeiro.get(variavel).add("E");//Corrigir n�o adicionei E
						
					}
				}
			}
		}


	}

	public HashSet<String> retornaConjunto(String cadeia){
		HashSet<String> retorno = new HashSet<String>();

		// Se � um s�mbolo da gram�tica ent�o j� est� calculado por causa do 
		if (gramatica.isTerminal(cadeia) || gramatica.isVariavel(cadeia) || cadeia.equals("E")){
			retorno = Primeiro.get(cadeia);
		} else {
			// 3� Caso: para uma cadeia w qualquer ele cria o Primeiro daquela cadeia.
			// ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- -----

			// Recebe uma cadeia w = X1X2...Xn
			String w = cadeia;

			String X1 = w.charAt(0)+"";

			// Cria o conjunto: Primeiro(X1) - {ep}
			HashSet<String> conjunto_X1 = new HashSet<String>();
			conjunto_X1.addAll(Primeiro.get(X1));
			conjunto_X1.remove("E");

			// Adiciono ao Primeiro
			Primeiro.put(w, conjunto_X1);

			// Verificacao: se Primeiro(X1) cont�m epsilon
			boolean vazio = false;
			if (Primeiro.get(X1).contains("E")){
				vazio = true;
			}

			// Armazenando o indice da ultima posi��o de w
			int n = w.length();

			for(int i=2-1;i<=(n-1);i++){
				if(!vazio) break;
				for(int m=1-1;m<=i-1-1;m++){
					if(!(Primeiro.get(w.charAt(m)+"").contains("E"))) vazio = false;
					if (vazio) {
						HashSet<String> conjunto_Xi = new HashSet<String>();
						conjunto_Xi.addAll(Primeiro.get(w.charAt(i)+""));
						conjunto_Xi.remove("E");
						conjunto_Xi.addAll(Primeiro.get(w));

						Primeiro.put(w, conjunto_Xi);
					}
				}
			}
			if (vazio) {
				HashSet<String> temp = new HashSet<String>();
				temp.add("E");
				temp.addAll(Primeiro.get(w));
				Primeiro.put(w, temp);
			}
		retorno = Primeiro.get(cadeia);
		}
		return retorno;
	}

	public String getLadoDireito (String p){
		String[] lado = p.split("->");
		return lado[1];
	}

	public void imprimir(){
		System.out.println("Primeiro:");
		System.out.println(Primeiro.toString());
	}
}
