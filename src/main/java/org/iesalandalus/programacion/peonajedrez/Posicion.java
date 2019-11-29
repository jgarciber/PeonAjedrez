package org.iesalandalus.programacion.peonajedrez;

public class Posicion {
	//Declaración de atributos
	private int fila;
	private char columna;
	
	public Posicion (int fila,char columna) {
		setFila(fila);
		setColumna(columna);
	}
	
	public Posicion (Posicion posicion) {
		if (posicion==null) {
			throw new NullPointerException("ERROR: No es posible copiar una posición nula.");
		}
		setFila(posicion.getFila());
		setColumna(posicion.getColumna());
	}
	
	public int getFila() {
		return fila;
	}
	public void setFila(int fila) {
		if (fila<1 || fila>8) {
			throw new IllegalArgumentException("ERROR: Fila no válida.");
		}
		this.fila = fila;
	}
	public char getColumna() {
		return columna;
	}
	public void setColumna(char columna) {
		if (columna!='a' && columna!='b' && columna!='c' && columna!='d' && columna!='e' && columna!='f' && columna!='g' && columna!='h') {
			throw new IllegalArgumentException("ERROR: Columna no válida.");
		}
		this.columna = columna;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + columna;
		result = prime * result + fila;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicion other = (Posicion) obj;
		if (columna != other.columna)
			return false;
		if (fila != other.fila)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "fila=" + fila + ", columna=" + columna;
	}
	
}
