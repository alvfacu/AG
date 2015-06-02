package mochila;

public class Posibilidad {
	
	String posib;
	int valor;
	int volumen;
	
	public String getPosib() {
		return posib;
	}
	
	public void setPosib(String posib) {
		this.posib = posib;
	}
	
	public int getValor() {
		return valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public int getVolumen() {
		return volumen;
	}
	
	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}
	
	public Posibilidad(){ }
	
	public Posibilidad(String p, int vol, int val)
	{
		this.posib = p;
		this.valor = val;
		this.volumen = vol;
	}

}
