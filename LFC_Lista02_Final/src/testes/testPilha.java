package testes;

import java.util.Stack;

public class testPilha {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack<Integer> pilha = new Stack<Integer>();
		// Exemplo de gram�tica:
		// S->(S)S, S->E
		// S-> 0S1, E // nem todas ser�o adequadas. esperar o professor.
		
		//System.out.println(pilha.firstElement());
		pilha.add(5);
		pilha.add(6);
		System.out.println(pilha.remove(pilha.size()-1));
		pilha.add(7);
		System.out.println(pilha.remove(pilha.size()-1));
		System.out.println(pilha.remove(pilha.size()-1));
		
		
		// Ler a primeira linha e identificar as vari�veis e os terminais; Precisa n�o! Empilha e foda-se!
		// Guardar as produ��es em alguma estrutura de dados
		// Saber a vari�vel inicial,
		// Identificar na produ��o os tr�s casos: variav�is e terminais; ou puros... s� term ou s� vars.
		// S� tem terminal... adiciona ao conjunto
		
			
		

	}

}
