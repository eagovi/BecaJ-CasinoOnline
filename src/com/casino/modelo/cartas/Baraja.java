package com.casino.modelo.cartas;

import java.util.ArrayList;

public class Baraja {

	private ArrayList<String> mazoCorazones;
	private ArrayList<String> mazoRombos;
	private ArrayList<String> mazoPicas;
	private ArrayList<String> mazoTreboles;
	
	private String rutaCorazones;
	private String rutaRombos;
	private String rutaPicas;
	private String rutaTreboles;
	
	private static final Baraja instancia;
	
	static {
		instancia = new Baraja();
	}
	
	private Baraja() {
		mazoCorazones = new ArrayList<String>();
		mazoRombos = new ArrayList<String>();
		mazoPicas = new ArrayList<String>();
		mazoTreboles = new ArrayList<String>();
		
		rutaCorazones = "imagenes/juegos/cartas/corazones/";
		rutaRombos = "imagenes/juegos/cartas/rombos/";
		rutaPicas = "imagenes/juegos/cartas/picas/";
		rutaTreboles = "imagenes/juegos/cartas/treboles/";
		
		generar();
	}

	public static synchronized Baraja getInstancia() {
		return instancia;
	}
	public ArrayList<String> getMazo(int mazo) {
		switch (mazo) {
		case 0:
			return mazoCorazones;
		case 1:
			return mazoRombos;	
		case 2:
			return mazoPicas;
		case 3:
			return mazoTreboles;
		default:
			return mazoCorazones;
		}
	}
	
	public ArrayList<String> getMazoCorazones() {
		return mazoCorazones;
	}

	public void setMazoCorazones(ArrayList<String> mazoCorazones) {
		this.mazoCorazones = mazoCorazones;
	}

	public ArrayList<String> getMazoRombos() {
		return mazoRombos;
	}

	public void setMazoRombos(ArrayList<String> mazoRombos) {
		this.mazoRombos = mazoRombos;
	}

	public ArrayList<String> getMazoPicas() {
		return mazoPicas;
	}

	public void setMazoPicas(ArrayList<String> mazoPicas) {
		this.mazoPicas = mazoPicas;
	}

	public ArrayList<String> getMazoTreboles() {
		return mazoTreboles;
	}

	public void setMazoTreboles(ArrayList<String> mazoTreboles) {
		this.mazoTreboles = mazoTreboles;
	}
	
	public void generar() {
		generarMazo(TPinta.Corazones);
		generarMazo(TPinta.Rombos);
		generarMazo(TPinta.Picas);
		generarMazo(TPinta.Treboles);
	}
	
	public void generarMazo(TPinta pinta) {
		
		for(int i = 0; i < 13; i++) {
			if(pinta.equals(TPinta.Corazones)) {
				mazoCorazones.add(rutaCorazones+"carta"+i+".png");
			} else if(pinta.equals(TPinta.Rombos)){
				mazoRombos.add(rutaRombos+"carta"+i+".png");
			} else if(pinta.equals(TPinta.Picas)){
				mazoPicas.add(rutaPicas+"carta"+i+".png");
			} else if(pinta.equals(TPinta.Treboles)){
				mazoTreboles.add(rutaTreboles+"carta"+i+".png");
			}
		}
	}

	public int valor(int cartaCliente) {
		switch (cartaCliente) {
		case 0:
			return 10;
		case 10:
			return 10;
		case 11:
			return 10;
		case 12:
			return 10;
		default:
			return cartaCliente+1;
		}
	}
	
}
