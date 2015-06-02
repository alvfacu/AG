package mochila;

public class Elemento {
	
	int id;
	int volumen;
	int valor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVolumen() {
		return volumen;
	}
	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public Elemento(){
	
	}
	
	public Elemento(int ID, int vol, int val)
	{
		this.id = ID;
		this.volumen = vol;
		this.valor = val;
	}

}
