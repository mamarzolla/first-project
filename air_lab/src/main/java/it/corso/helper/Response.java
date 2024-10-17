package it.corso.helper;

public class Response {
	
	private int codice;
	private String messaggio;
	public Response(int codice, String messaggio) {
		//super();
		this.codice = codice;
		this.messaggio = messaggio;
	}
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}
	public String getMessaggio() {
		return messaggio;
	}
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}
	
	

}
