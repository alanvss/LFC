package testes;

import java.util.ArrayList;
import java.util.List;

import tratadores.CelulaDaTabela;
import tratadores.Gramatica;
import tratadores.Primeiro;
import tratadores.Sequencia;
import tratadores.Tabela;

public class testeTabela {

	public static void main(String[] args) {
		//Gramatica g = new Gramatica("A->A+T, A->T, T->TxF, T->F, T->E, F->(A), F->n"); //Não é LL1
		//Gramatica g = new Gramatica("I->aBa, I->BAc, I->ABc, A->aA, A->E, B->ba, B->c"); //Não é LL1
		
		Gramatica g = new Gramatica("S->(S)S,S->E");
		
		Primeiro primeiro = new Primeiro(g);
	
		Sequencia sequencia  = new Sequencia(primeiro, g);
		
		Tabela tabela = new Tabela(sequencia, primeiro, g);
		
		tabela.imprimir();

		List<CelulaDaTabela> lista = new ArrayList<CelulaDaTabela>();
		lista = tabela.consulta("T", "(");
		
		System.out.println("Imprimindo a consulta: ");
		for (CelulaDaTabela cel: lista){
			cel.imprimir();
		}
	}

}
