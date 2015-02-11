package com.casino.modelo.cartas;

public class Carta {

	private int valor;
	private TPinta pinta;
	private String imagen;
	
	public Carta(int valor, TPinta pinta, String imagen) {
		super();
		this.valor = valor;
		this.pinta = pinta;
		this.imagen = imagen;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public TPinta getPinta() {
		return pinta;
	}

	public void setPinta(TPinta pinta) {
		this.pinta = pinta;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
}
