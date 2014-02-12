package testes;

import java.util.Stack;

public class testPilha {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<Integer> pilha = new Stack<Integer>();
		// Exemplo de gramática:
		// S->(S)S, S->E
		// S-> 0S1, E // nem todas serão adequadas. esperar o professor.
		
		//System.out.println(pilha.firstElement());
		pilha.add(5);
		pilha.add(6);
		System.out.println(pilha.remove(pilha.size()-1));
		pilha.add(7);
		System.out.println(pilha.remove(pilha.size()-1));
		System.out.println(pilha.remove(pilha.size()-1));
		
		
		// Ler a primeira linha e identificar as variáveis e os terminais; Precisa não! Empilha e foda-se!
		// Guardar as produções em alguma estrutura de dados
		// Saber a variável inicial,
		// Identificar na produção os três casos: variavéis e terminais; ou puros... só term ou só vars.
		// Só tem terminal... adiciona ao conjunto
		
			
		

	}

}
