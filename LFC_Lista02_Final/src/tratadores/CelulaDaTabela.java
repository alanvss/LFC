package tratadores;

public class CelulaDaTabela {
	private String variavel;
	private String terminal;
	private String campo;
	
	
	public CelulaDaTabela(String v, String t, String c){
		variavel = v;
		terminal = t;
		campo = c;
	}
	
	public String getVariavel() {
		return variavel;
	}
	
	public String getTerminal() {
		return terminal;
	}
	
	public String getCampo() {
		return campo;
	}
	
	public void setVariavel(String variavel) {
		this.variavel = variavel;
	}
	
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	public void imprimir(){
		System.out.println("Variável: "+getVariavel()+ "\tTerminal: "+getTerminal()+ "\tCampo: "+getCampo());
	}
}
