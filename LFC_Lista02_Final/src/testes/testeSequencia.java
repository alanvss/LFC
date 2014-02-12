package testes;

import tratadores.Gramatica;
import tratadores.Primeiro;
import tratadores.Sequencia;

public class testeSequencia {

	public static void main(String[] args) {
		//Gramatica g = new Gramatica("A->A+T, A->T, T->TxF, T->F, T->E, F->(A), F->n");
		Gramatica g = new Gramatica("S->I#,I->Ti,i->+Ti,i->-Ti,i->E,T->Ft,t->*Ft,t->/Ft,t->E,F->c,F->(I)");
		Primeiro primeiro = new Primeiro(g);
	
		Sequencia sequencia  = new Sequencia(primeiro, g);
		
		sequencia.imprimir();

	}

}
