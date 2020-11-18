package ejercicio2;

import java.util.Scanner;

public class GestorContactos {
	
	private static GestorContactos instancia = null;
	
	public int x;
	private String matriz[][];
	
	private Ficheros fichero = new Ficheros();
	Scanner s = new Scanner(System.in);
	
	public GestorContactos() {
		matriz = new String[25][5]; //El valor de filas de esta matriz es el numero m�x de contactos que admite el programa
		x = fichero.leerFichero(matriz); //Con este se inicializa la x y la matriz
	}
		
	public static GestorContactos getGestorContactos() {
		if(instancia == null) {
			instancia = new GestorContactos();
		}
		return instancia;
	}
		
	// Operaciones p�blicas de la clase GestorContactos
	public boolean comprobarUsuario(String email)
	{
		for(int i=0;i<x;i++) {
			if(matriz[i][2].equals(email))
				return true;
		}
		
		return false;
		
	}
	
	public String imprimeNombre(String email) {
		
		int x=fichero.leerFichero(matriz);
		String output = null;
		
		for(int i=0; i<x;i++)
		{	
			if(matriz[i][2].equals(email))
			{
				output = matriz[i][0] + " " + matriz[i][1];
			}
		}
		
		return output;
	}
	
}