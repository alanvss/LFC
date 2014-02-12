package arquivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LerArquivoTexto {
	private String nomeDoArquivo;
	private Scanner entrada;
		
	public LerArquivoTexto(String nomeDoArquivo) {
		super();
		this.nomeDoArquivo = nomeDoArquivo;
		abrirArquivo();
	}

	public void abrirArquivo(){
		try{
			entrada = new Scanner(new File(nomeDoArquivo));
		}catch ( FileNotFoundException fileNotFoundException )
		{
			System.err.println( "Erro ao abrir o arquivo." );
			System.exit( 1 );
		} 
	}

	public List<String> lerLinhasArquivo()
	{
		List<String> saida = new ArrayList<String>();
		String str;
		System.out.println("Fazendo a leitura: ");

		try
		{
			while ( entrada.hasNext() )
			{
				str =  entrada.next();
				saida.add(str);
			} 
		} 
		catch ( NoSuchElementException elementException )
		{
			System.err.println( "Problema do formato do arquivo." );
			entrada.close();
			System.exit( 1 );
		}
		catch ( IllegalStateException stateException )
		{
			System.err.println( "Erro de leitura no arquivo." );
			System.exit( 1 );
		}
		fecharArquivo();
		return saida;
	}


	public void fecharArquivo() {
		if ( entrada != null )
			entrada.close();
	} 
}