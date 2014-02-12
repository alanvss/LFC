package testes;

import java.util.ArrayList;
import java.util.List;

import arquivo.LerArquivoTexto;

public class testLerArquivoTexto {

	public static void main(String[] args) {
		String pathDoArquivo = "D:\\Meus Documentos\\workspace_lfc\\";
		String nomeDoArquivo = "L2_automato_01.txt";
		LerArquivoTexto leitor = new LerArquivoTexto(pathDoArquivo+nomeDoArquivo);
		
		List<String> entrada = new ArrayList<>();
		
		entrada = leitor.lerLinhasArquivo();
						
		for (String c: entrada){
			System.out.println(c);
		}
		
		String gramatica = entrada.get(0);
		
		System.out.println(gramatica);
		
	}

}
