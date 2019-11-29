package org.iesalandalus.programacion.peonajedrez;

import javax.naming.OperationNotSupportedException;

public class Peon {
	private Color color;
	private Posicion posicion;
	
	public Peon() {
		setColor(Color.NEGRO);
		setPosicion(new Posicion(7,'d'));
	}
	public Peon(Color color) {
		setColor(color);
		if (color.equals(Color.BLANCO)){
			setPosicion(new Posicion(2,'d'));
		}
		if (color.equals(Color.NEGRO)){
			setPosicion(new Posicion(7,'d'));
		}
	}
	
	public Peon(Color color,char columna) {
	/*
		if (color.equals(Color.BLANCO) && posicion.getFila()!=2){
			throw new NullPointerException("ERROR: No se puede crear un peon blanco en una fila distinta a la fila 2.");
		}
		if (color.equals(Color.NEGRO) && posicion.getFila()!=7){
			throw new NullPointerException("ERROR: No se puede crear un peon negro en una fila distinta a la fila 7.");
		}*/
		if (columna!='a' && columna!='b' && columna!='c' && columna!='d' && columna!='e' && columna!='f' && columna!='g' && columna!='h') {
			throw new IllegalArgumentException("ERROR: Columna no válida.");
		}
		setColor(color);
		if (color.equals(Color.BLANCO)){
			setPosicion(new Posicion(2,columna));
		}
		if (color.equals(Color.NEGRO)){
			setPosicion(new Posicion(7,columna));
		}
		
		//El m�todo set posicion en concreto el set columna se encargar� de validad si la columana existe.
	}
	
	public void mover(int npasos) throws OperationNotSupportedException {
		if (npasos<1 || npasos>2) {
			throw new OperationNotSupportedException("ERROR: El peón sólo se puede mover 1 o 2 pasos.");
		}
		if (npasos==2) {
			if(color.equals(Color.BLANCO)){
				if(posicion.getFila()==2){
					setPosicion(new Posicion(posicion.getFila()+npasos,posicion.getColumna() ));
				}else{
					throw new OperationNotSupportedException("ERROR: El peón sólo se puede mover 2 pasos cuando se encuentra en la casilla inicial.");
				}
			}
			if(color.equals(Color.NEGRO)){
				if(posicion.getFila()==7){
					setPosicion(new Posicion(posicion.getFila()-npasos,posicion.getColumna() ));
				}else{
					throw new OperationNotSupportedException("ERROR: El peón sólo se puede mover 2 pasos cuando se encuentra en la casilla inicial.");
				}
			}
		}
		if(npasos==1) {
			if(color.equals(Color.BLANCO)){ //del control de la fila valida ya se encarga setFila
				if(posicion.getFila()>=2 && posicion.getFila()<=7){	
					setPosicion(new Posicion(posicion.getFila()+npasos,posicion.getColumna() ));
				}else {
					throw new OperationNotSupportedException("ERROR: Movimiento no válido.");
				}
			}
			
			if(color.equals(Color.NEGRO)){
				if(posicion.getFila()>=2 && posicion.getFila()<=7){
					setPosicion(new Posicion(posicion.getFila()-npasos,posicion.getColumna() ));
				}else {
					throw new OperationNotSupportedException("ERROR: Movimiento no válido.");
				}
			}
		}
	}
		
		
	public void mover(Direccion dir) throws OperationNotSupportedException{
		/*Para respetar la terminolog�a del ejercicio con respecto a las columnas he tenido
		 * que crear una estructura switch para establecer la operaci�n suma y resta de columna respectivamente
		 * .Esto se podr�a haber evitado si las columnas fuesen n�meros de forma que solo bastar�a 
		 * modifica posicion.getColumna()+1 o posicion.getColumna()-1
		 */
		if(dir==null) {
			throw new NullPointerException("ERROR: Mover el peón en una dirección nula no está permitido.");
		}
		//iniciamos las variables locales a 'z' que es un valor de columna no v�lido
		char sumaPosicionColumna='n';
		char restaPosicionColumna='n';
		boolean columnaValida=true;
		switch(posicion.getColumna()) {
			case 'a': 
				sumaPosicionColumna='b';
				columnaValida=false;//no v�lida
				break;
			case 'b': 
				sumaPosicionColumna='c';
				restaPosicionColumna='a';
				break;
			case 'c': 
				sumaPosicionColumna='d';
				restaPosicionColumna='b';
				break;
			case 'd': 
				sumaPosicionColumna='e';
				restaPosicionColumna='c';
				break;
			case 'e': 
				sumaPosicionColumna='f';
				restaPosicionColumna='d';
				break;
			case 'f': 
				sumaPosicionColumna='g';
				restaPosicionColumna='e';
				break;
			case 'g': 
				sumaPosicionColumna='h';
				restaPosicionColumna='f';
				break;
			case 'h': 
				columnaValida=false; //no v�lida
				restaPosicionColumna='g';
				break;
		}
		
		
		if(color.equals(Color.BLANCO)) { //el m�todo setFila y setColumna comprueban que dichas filas y columnas existen y son v�lidas
			if(dir.equals(Direccion.DERECHA)) {
				if(columnaValida==true) {
					setPosicion(new Posicion(posicion.getFila()+1,sumaPosicionColumna));
				}else {
					throw new OperationNotSupportedException("ERROR: Movimiento no válido.");
				}
			}
			if(dir.equals(Direccion.IZQUIERDA)) {
				if(columnaValida==true){
					setPosicion(new Posicion(posicion.getFila()+1,restaPosicionColumna));
				}else {
					throw new OperationNotSupportedException("ERROR: Movimiento no válido.");
				}
			}
		}
		
		if(color.equals(Color.NEGRO)) {
			if(dir.equals(Direccion.DERECHA)){
					if(columnaValida==true) {
						setPosicion(new Posicion(posicion.getFila()-1,sumaPosicionColumna));
					}else {
						throw new OperationNotSupportedException("ERROR: Movimiento no válido.");
					}
			}
			if(dir.equals(Direccion.IZQUIERDA)){
				if(columnaValida==true) {
					setPosicion(new Posicion(posicion.getFila()-1,restaPosicionColumna));
				}else {
					throw new OperationNotSupportedException("ERROR: Movimiento no válido.");
				}
			}
		}
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		if (color==null) {
			throw new NullPointerException("ERROR: No se puede asignar un color nulo.");
		}
		if ((color!=Color.NEGRO)&&(color!=Color.BLANCO)){
			throw new NullPointerException("ERROR: Los colores permitidos son BLANCO o NEGRO.");
		}
		this.color = color;
	}
	
	public Posicion getPosicion() {
		return posicion;
	}
	
	public void setPosicion(Posicion posicion) {
		if (posicion==null) {
			throw new NullPointerException("ERROR: No se puede establecer una posicion nula.");
		}
		this.posicion = new Posicion(posicion);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((posicion == null) ? 0 : posicion.hashCode());
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
		Peon other = (Peon) obj;
		if (color != other.color)
			return false;
		if (posicion == null) {
			if (other.posicion != null)
				return false;
		} else if (!posicion.equals(other.posicion))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "fila=" + posicion.getFila() + ", columna=" + posicion.getColumna() + ", color=" + color;
	}
	
}
