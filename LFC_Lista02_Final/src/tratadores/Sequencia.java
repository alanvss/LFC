package tratadores;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;



public class Sequencia {
	// Estrutura de Dados de Sequencia.
	private HashMap<String, HashSet<String>> Sequencia = new HashMap<String, HashSet<String>>();

	//Construtor de Sequencia: 
	public Sequencia(Primeiro p, Gramatica g){

		// Sequencia(variável inicial) := {$};
		HashSet<String> conjuntoCifrao = new HashSet<String>();
		conjuntoCifrao.add("$");
		Sequencia.put(g.getVariavelInicial(), conjuntoCifrao);

		// Inicializa os conjuntos sequencia's com vazio.
		HashSet<String> conjuntoVazio = new HashSet<String>();
		for(String variavel: g.getVariaveis()){
			if(!variavel.equals(g.getVariavelInicial())){
				Sequencia.put(variavel, conjuntoVazio);
			}
		}
		
		boolean alteracao = true; // Sentinela que verifica se houve alteração ou não nos conjuntos.
		List<String> listaProducoesVariavel; // Armazerna a lista de produção das variáveis para o "for"
		while(alteracao){
			alteracao = false;

			// Para cada variável e...
			for(String variavel: g.getVariaveis()){
				listaProducoesVariavel = g.getListaProducoes(variavel);
				//Para cada produção dessa variável...
				for(String producao : listaProducoesVariavel){
					int n = (getLadoDireito(producao)).length();

					for(int i=1-1;i<=n-1;i++){
						String xi = (getLadoDireito(producao)).charAt(i)+"";
						// Se xi for variável da gramática
						if(g.isVariavel(xi)){
							String xi_mais_1 = "";
							if (i==(n-1)){
								xi_mais_1 = "E";
							} else {
								//xi_mais_1 = (getLadoDireito(producao)).charAt(i+1)+"";
								xi_mais_1 = (getLadoDireito(producao)).substring(i+1);
							}

							HashSet<String> conjunto_xi = new HashSet<String>();
							conjunto_xi.addAll(p.retornaConjunto(xi_mais_1));
							conjunto_xi.remove("E");
							
							//Testar se produziu alteração no conjunto
							for(String elemento: conjunto_xi){
								if(!Sequencia.get(xi).contains(elemento)){
									alteracao = true;
								}
							}
							
							conjunto_xi.addAll(Sequencia.get(xi));
							Sequencia.put(xi, conjunto_xi);

							
							// Testa se contem ou nao Epsilon
							if ((p.retornaConjunto(xi_mais_1)).contains("E")){
								HashSet<String> conjunto_variavel = new HashSet<String>();
								conjunto_variavel.addAll(Sequencia.get(variavel));
								
								
								//Testar se produziu alteração no conjunto
								for(String elemento: conjunto_variavel){
									if(!Sequencia.get(xi).contains(elemento)){
										alteracao = true;
									}
								}
								
								// Adiciona Sequencia(variavel) em Sequencia(xi_mais_1)
								Sequencia.get(xi).addAll(conjunto_variavel);
								
							}
						}
					}
				}
			}
		}
	}
	
	public HashSet<String> retornaConjunto(String w){
		HashSet<String> retorno = new HashSet<String>();
		
		if(Sequencia.containsKey(w)){
			retorno = Sequencia.get(w);
		} else {
			System.out.println("Não contém a sequencia dessa variável");
		}
		
		
		return retorno;
	}
	
	
	public String getLadoDireito (String p){
		String[] lado = p.split("->");
		return lado[1];
	}
	
	public void imprimir(){
		System.out.println("Sequencia:");
		System.out.println(Sequencia.toString());
	}
	
}

