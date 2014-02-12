package testes;

import java.util.ArrayList;

import tratadores.Gramatica;
import tratadores.Primeiro;

public class testePrimeiro {

	public static void main(String[] args) {
		//Gramatica g = new Gramatica("S->(S),A->S");
		//Gramatica g = new Gramatica("  A->A  +T, A->T, T->TxF, T->F, T->E, F->(A), F->n");
		Gramatica g = new Gramatica("S->I#,I->Ti,i->+Ti,i->-Ti,i->E,T->Ft,t->*Ft,t->/Ft,t->E,F->c,F->(I)");
		
		Primeiro primeiro = new Primeiro(g);

		primeiro.imprimir();
		
		ArrayList<String> teste = new ArrayList<String>();
		//teste.add("xF");
		//teste.add("+T");
		teste.add("I#");
		teste.add("Ti");
		teste.add("(I)");
		teste.add("/Ft");
		teste.add("Ft");
		teste.add("+Ti");
		teste.add("*Ft");
		teste.add("-Ti");
		
		for (String a : teste){
			System.out.println(primeiro.retornaConjunto(a));
		}

		primeiro.imprimir();

	}

}
