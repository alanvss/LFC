package testes;

import tratadores.Gramatica;

public class testeGramatica {

	public static void main(String[] args) {
		//Gramatica g = new Gramatica("S->(S),E->S");
		Gramatica g = new Gramatica("S->A,  A->A+T, A->T, T->TxF, T->F, T->E, F->(A), F->n");
		
		g.imprimir();
		
		System.out.println(" ");
		System.out.println("Testando getListaProduções: ");
		System.out.println((g.getListaProducoes("A")));
		
	}

}
