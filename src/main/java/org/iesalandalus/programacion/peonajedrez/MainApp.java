package org.iesalandalus.programacion.peonajedrez;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {
	
	static char columnaInicial='n';
	int opcionMovimiento=0;
	private static int opcionMenu=0;
	private static Peon peon=null; 
	private static Color color=null; 
	private static Direccion dir=null; 
	
	public static void main(String[] args) {
		
			
		System.out.println("Programa para aprender a colocar y mover un pe�n en el tablero de ajedrez");
		System.out.println("-------------------------------------------------------------------------");
		do {
			mostrarMenu();
			opcionMenu=elegirOpcion();
			ejecutarOpcion(opcionMenu);
			for (int i = 0; i < 5; ++i) System.out.println(); //c�digo de stackoverflow para simular un clear 'cls' o dejar espacios grandes en la pantalla
		}while(opcionMenu!=0);
	}
	
	
	private static void mostrarMenu() {
		System.out.println("");
		System.out.println("1.- Mostrar el menu de movimientos.");
		System.out.println("2.- Crear un peon por defecto.");
		System.out.println("3.- Crear un peon con color espec�fico en una posici�n por defecto.");
		System.out.println("4.- Crear un peon con color especifico en una columna determinada.");
		System.out.println("5.- Mover al pe�n.");
		System.out.println("6.- Mostrar al pe�n.");
		System.out.println("");
		System.out.println("0.- Salir.");
		System.out.println("");
	}
	
	private static int elegirOpcion() {
		int opcionMenu;
		do{
			System.out.println("Introduce la opci�n a realizar (0-6) para volver a visualizar el men� pulse 7");
			System.out.print("Opci�n: ");
			opcionMenu=org.iesalandalus.programacion.utilidades.Entrada.entero();	
			if (opcionMenu==7) {
				mostrarMenu();
			}
		}while (opcionMenu<0 || opcionMenu>6);
		return opcionMenu;
	}
	
	private static void ejecutarOpcion(int opcion) {
				
		switch(opcion) {
			case 1: //Mostrar el menu de movimientos.
				mostrarMenuMovimientos();
				break;
			case 2: //Crear un peon por defecto.
				crearPeonDefecto();
				break;
			case 3: //Crear un peon con color espec�fico en una posici�n por defecto.
				
				try {
					color=(elegirColor());
					crearPeonColor(color);
				}catch (NullPointerException e) {
					System.out.println(e.getMessage());
				} 
				break;
			case 4: //Crear un peon con color especifico en una columna determinada.
				try {
					color=(elegirColor());
					columnaInicial=elegirColumnaInicial();
					crearPeonColorColumna(color,columnaInicial);
				}catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				} 
				break;
			case 5: //Mover al pe�n.
					mover();
				break;
			case 6: //Mostrar al pe�n.
				mostrarPeon();
				break;
		}
	}
	
	
	private static void crearPeonDefecto() {
		peon=new Peon();
	}
		
	private static void crearPeonColor(Color color) {
		peon=new Peon(color);
	}
		
	private static void crearPeonColorColumna(Color color,char columna) {
		try {
		peon=new Peon(color,columna);
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
		} 
	}
	
	
	private static Color elegirColor() {
		int opcionColor;
		Color color = null;
		do {
			System.out.println("Introduzca el color para el peon");
			System.out.println("Dicho color solo puede ser BLANCO o NEGRO");
			System.out.println("Color BLANCO, pulse 1");
			System.out.println("Color NEGRO, pulse 2");
			System.out.print("Opcion: ");
			opcionColor=org.iesalandalus.programacion.utilidades.Entrada.entero();
			if (opcionColor==1){
				color=Color.BLANCO;
			}
			if (opcionColor==2) {
				color=Color.NEGRO;
			}
		}while(opcionColor!=1 && opcionColor!=2);
		return color;
	}
	
	private static char elegirColumnaInicial() {
		char columnaInicial;
		do {
			System.out.println("Introduzca la columna inicial para el peon");
			System.out.println("Dicho columna debe estar comprendida entre a-h");
			System.out.print("Opcion: ");
			columnaInicial=org.iesalandalus.programacion.utilidades.Entrada.caracter();
			
		}while(columnaInicial!='a' && columnaInicial!='b' && columnaInicial!='c' && columnaInicial!='d' && columnaInicial!='e' && columnaInicial!='f' && columnaInicial!='g' && columnaInicial!='h');
	return columnaInicial;
	}
	
	private static void mostrarMenuMovimientos() {
		System.out.println("Diferentes movimientos que podemos realizar: ");
		System.out.println("1. Avanzar pe�n un paso");
		System.out.println("2. Avanzar pe�n dos pasos");
		System.out.println("3. Avanzar pe�n hacia la izquierda");
		System.out.println("4. Avanzar pe�n hacia la derecha");
	}
	
	private static void mover() {
		int opcionMovimiento;
	
			mostrarMenuMovimientos();
			opcionMovimiento=elegirMovimiento();
			realizarMovimiento(opcionMovimiento);
		
	}
	
	private static int elegirMovimiento() {
		int opcionMovimiento;
		
		do
		{
			System.out.println("Introduce el n�mero del movimiento a realizar (0-4)");
			System.out.print("Movimiento n�= ");
			opcionMovimiento=org.iesalandalus.programacion.utilidades.Entrada.entero();
			
		}while (opcionMovimiento<0 || opcionMovimiento>4);
		
		return opcionMovimiento;
	}
	
		

	private static void realizarMovimiento(int opcionMovimiento) {	
		try {
			if (opcionMovimiento==1 || opcionMovimiento==2) {
				//le la opcionMovimiento al m�todo mover(int npasos) de la clase peon
				//dicho m�todo responder� de acorde si se le indica que el peon se mueve 1 o 2 pasos.
				
					peon.mover(opcionMovimiento);
				
			}
			if (opcionMovimiento==3) {
				//le mando la direcci�n al m�todo mover(Direccion dir) de la clase peon
					dir=Direccion.IZQUIERDA;
					peon.mover(dir);
				
			}
			if (opcionMovimiento==4) {
				//le mando la direcci�n al m�todo mover(Direccion dir) de la clase peon
				dir=Direccion.DERECHA;
				peon.mover(dir);
			}
		}catch (IllegalArgumentException | OperationNotSupportedException e) {
				System.out.println(e.getMessage());
		} 
	}
	
	private static void mostrarPeon() {
		if (peon!=null) {
		System.out.println("Peon:"+ peon);
		}
	}

		
	
}
		
	
	
