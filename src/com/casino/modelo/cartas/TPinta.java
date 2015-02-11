package com.casino.modelo.cartas;

public enum TPinta {
	Corazones, Rombos, Picas, Treboles;
	
	public TPinta get(int i) {
		
		switch (i) {
		case 0:
			return TPinta.Corazones;
		case 1:
			return TPinta.Rombos;	
		case 2:
			return TPinta.Picas;
		case 3:
			return TPinta.Treboles;
		default:
			return TPinta.Corazones;
		}
	}
}
